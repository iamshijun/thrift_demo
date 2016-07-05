package com.kibou.test.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TZlibTransport;

import tutorial.HelloEcho;

public class TZlibTransportTestHelloServiceClient {

	public static void main(String[] args) {
		//try-with-resource(closeable)
		try (TTransport baseTransport = new TSocket("localhost", 9090);){
			baseTransport.open();

			//注意server端 也需要使用同样的TTransport或对应的TTransportFactory
			TTransport transport = new TZlibTransport(baseTransport);

			TProtocol protocol = new TBinaryProtocol(transport);

			HelloEcho.Client client = new HelloEcho.Client(protocol);
			String sayHelloRet = client.sayHello("nyannyan");
			System.out.println(sayHelloRet);

		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
