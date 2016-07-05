package com.kibou.test.support;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public interface Promise<T> /*extends Future<T>*/ {
	public T get() throws InterruptedException, ExecutionException;
	
	public T get(long time,TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}
