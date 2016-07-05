package com.kibou.test.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.kibou.test.thrift.server.AsyncServiceServer;

import tutorial.HelloEcho;

/**
 * ʹ�� TFrameTransport �� ֱ��ʹ��TFrameTransport��server��,����ʹ��TNonblockingTransport��server��( {@link AsyncServiceServer} )����
 */
public class TFramedTransportTestServiceClient {
	public static void main(String[] args) {
		try (TTransport baseTransport = new TSocket("localhost", 9090);){
			baseTransport.open();

			TFramedTransport framedTransport = new TFramedTransport(baseTransport/*, maxLength*/);
			
			TProtocol protocol = new TBinaryProtocol(framedTransport);

			HelloEcho.Client client = new HelloEcho.Client(protocol);
			String sayHelloRet = client.sayHello("nyannyan");
			System.out.println(sayHelloRet);

		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
