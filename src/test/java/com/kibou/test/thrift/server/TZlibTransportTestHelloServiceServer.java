package com.kibou.test.thrift.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TZlibTransport;

import thrift.apache.org.HelloEchoHandler;
// Generated code
import tutorial.HelloEcho;

public class TZlibTransportTestHelloServiceServer implements Runnable{

	public static void main(String[] args) {
		try {
			new Thread(new TZlibTransportTestHelloServiceServer()).start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public void run() {
		TServerTransport serverTransport;
		try {
			serverTransport = new TServerSocket(9090);
		} catch (TTransportException e) {
			throw new RuntimeException(e);
		}

		HelloEchoHandler helloEchoHandler = new HelloEchoHandler();
		HelloEcho.Processor<HelloEchoHandler> helloEchoProcessor = new HelloEcho.Processor<>(
				helloEchoHandler);
		
		TTransportFactory transportFactory = new TZlibTransport.Factory();
		
		TServer server = new TSimpleServer(
				new Args(serverTransport)
						.processor(helloEchoProcessor)
						.transportFactory(transportFactory)
				);

		System.out.println("Starting the simple server...");
		server.serve();
	}
}
