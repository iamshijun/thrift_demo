package com.kibou.test.thrift.server;

import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

import thrift.apache.org.HelloEchoHandler;
import tutorial.HelloEcho;

public class AsyncServiceServer {
	public static void main(String[] args) {
		try {
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9090);
			HelloEcho.Processor<HelloEchoHandler> processor = new HelloEcho.Processor<>(
					new HelloEchoHandler());
			TServer server = new TNonblockingServer(
					new Args(serverTransport).processor(processor));
			System.out.println("Start aysnc server on port 9090 ...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
