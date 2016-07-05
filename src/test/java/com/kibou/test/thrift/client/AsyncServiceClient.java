package com.kibou.test.thrift.client;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import tutorial.HelloEcho;
import tutorial.HelloEcho.AsyncClient.slowSayHello_call;

import com.kibou.support.MethodCallbackFuture;

public class AsyncServiceClient {

	public static void main(String[] args) throws IOException, TException {
		
		TAsyncClientManager clientManager = new TAsyncClientManager();
		TNonblockingTransport nonblockingTransport = new TNonblockingSocket("localhost", 9090);
		
		TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
		HelloEcho.AsyncClient asyncClient = new HelloEcho.AsyncClient(protocolFactory, clientManager, nonblockingTransport);
//		asyncClient.setTimeout(4000); //set timeout
		
		//AsyncMethodCallback
		MethodCallbackFuture<String,slowSayHello_call> slowSayHelloMethodCallback = new MethodCallbackFuture<>(slowSayHello_call.class);
		asyncClient.slowSayHello("shifeng", slowSayHelloMethodCallback);
		
		try {
			long start = System.currentTimeMillis();
			System.out.println("ready to invoke ");
			String ret = slowSayHelloMethodCallback.get();
			System.out.println("Ret = " + ret + ",Time elipsed : " + (System.currentTimeMillis() - start) + ", after invoke");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}
