package com.kibou.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncMethodCall;

import tutorial.HelloEcho.AsyncClient.sayHello_call;

public class MethodCallbackFuture<R,T extends TAsyncMethodCall<?>> 
				implements  AsyncMethodCallback<T> , Future<R> /*Promise<R>*/ {
	
	private R result;
	private Throwable cause;
	private volatile boolean isSet = false;

	private final Method getResultMethod;
	
	private final static ConcurrentHashMap<String,Method> getResultMethodCache = new ConcurrentHashMap<>();
	
	public MethodCallbackFuture(Class<T> clazz){
		this(clazz,"getResult");
	}
	public MethodCallbackFuture(Class<T> clazz,String methodName){
		Method method = getResultMethodCache.get(clazz.getName());
		if(method == null){
			try {
				method = clazz.getDeclaredMethod(methodName, new Class[0]);
			} catch (NoSuchMethodException | SecurityException e) {
				//e.printStackTrace();
				throw new IllegalArgumentException("getResult cannot be found at " + clazz);
			}
			getResultMethodCache.putIfAbsent(clazz.getName(), method);
		}
		getResultMethod = getResultMethodCache.get(clazz.getName());
	}
	
	
	public MethodCallbackFuture(){
		this("getResult");
	}
	@SuppressWarnings("unchecked")
	public MethodCallbackFuture(String getResultMethodName){
		assert getResultMethodName != null && getResultMethodName.length() > 0;
		
		Type _genericSuperClass = this.getClass().getGenericSuperclass();
		if(!(_genericSuperClass instanceof ParameterizedType)){
			 throw new RuntimeException("Missing type parameter.");
		}
		
		Class<R> getResultMethodRType = null;
		Class<T> tmacClassType = null;
		
		ParameterizedType spt = (ParameterizedType) _genericSuperClass;
		Type[] actualTypeArguments = spt.getActualTypeArguments();
		
		Type type0 = actualTypeArguments[0];
		if(type0 instanceof Class){
			getResultMethodRType = (Class<R>) type0;
		}else if(type0 instanceof ParameterizedType){
			getResultMethodRType = (Class<R>)((ParameterizedType)type0).getRawType();
		}
		
		Type type1 = actualTypeArguments[1];
		if(type1 instanceof Class){
			tmacClassType = (Class<T>) type1;
		}else if(type1 instanceof ParameterizedType){
			tmacClassType = (Class<T>)((ParameterizedType)type1).getRawType();
		}
		
		if(getResultMethodRType == null || tmacClassType == null)
			throw new RuntimeException("Missing type parameter. ");
		
		Method method = getResultMethodCache.get(tmacClassType.getName());
		if(method == null){
			try {
				method = tmacClassType.getDeclaredMethod(getResultMethodName, new Class[0]);
				if(!getResultMethodRType.isAssignableFrom(method.getReturnType()))
					throw new IllegalArgumentException(getResultMethodName + "'s return type is not  " + getResultMethodRType);
			} catch (NoSuchMethodException | SecurityException e) {
				//e.printStackTrace();
				throw new IllegalArgumentException(getResultMethodName + " cannot be found at " + tmacClassType);
			}
			getResultMethodCache.putIfAbsent(tmacClassType.getName(), method);
		}
		getResultMethod = getResultMethodCache.get(tmacClassType.getName());
		
		if(getResultMethod == null){
			throw new IllegalStateException("cannot found any getResultMethod in class");
		}
	}
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {return false;}//TODO
	@Override
	public boolean isCancelled() {	return false;}
	@Override
	public boolean isDone() {return isSet;}
	
	private void doWait(long millTimeout) throws InterruptedException{
		synchronized (this) {
			if(millTimeout <= 0)
				wait();
			else
				wait(millTimeout);
		}
	}
	
	private void doNotify(){
		synchronized (this) {
			notify();
		}
	}
	
	
	@Override
	public void onError(Exception exception) {
		this.cause = exception;
		this.isSet = true;// Store-Store memorybarrier (make sure while 'isSet'=true,'cause' must be set
		doNotify();
	}

	@Override
	public R get() throws InterruptedException,ExecutionException {
		while (true) {
			if (isSet){
				if(cause != null)
					throw new ExecutionException(cause);
				return result;
			}
			doWait(0);
		}
	}
	
	@Override
	public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		
		long total = TimeUnit.MILLISECONDS.convert(timeout, unit);
		long start = System.currentTimeMillis();
		while (true) {
			long left = total - (System.currentTimeMillis() - start);
			if(left < 0)
				throw new TimeoutException();
			if (isSet){
				if(cause != null)
					throw new ExecutionException(cause);
				return result;
			}
			doWait(left);
		}
	}
	
//	public void onComplete(R response){}
	@Override @SuppressWarnings("unchecked")
	public void onComplete(T response) {
		try {
			if(!Modifier.isPublic(getResultMethod.getModifiers())){// or 
				getResultMethod.setAccessible(true);
			}
			this.result = (R)getResultMethod.invoke(response, new Object[0]);
		/*} catch (IllegalAccessException |
		 * 			 IllegalArgumentException | 
		 * 				InvocationTargetException e) {
		 *  	e.printStackTrace();
		 */
		} catch (Exception e) {
			this.cause = e;
		}
		this.isSet = true;//Store-Store barrier
		doNotify();
	}
	
	public static void main(String[] args) {
		MethodCallbackFuture<String, sayHello_call> methodCallbackFuture = 
				new MethodCallbackFuture<String, sayHello_call>(){};
		System.out.println(methodCallbackFuture.getResultMethod);
	}
}