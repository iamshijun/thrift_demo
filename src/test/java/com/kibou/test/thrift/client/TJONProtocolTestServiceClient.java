package com.kibou.test.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import tutorial.HelloEcho;

public class TJONProtocolTestServiceClient {

	public static void main(String[] args) {
		try (TTransport baseTransport = new TSocket("localhost", 9090);){
			baseTransport.open();

			//ע��server�� Ҳ��Ҫʹ��ͬ����TTransport���Ӧ��TTransportFactory
			TProtocol protocol = new TJSONProtocol(baseTransport);

			HelloEcho.Client client = new HelloEcho.Client(protocol);
//			String sayHelloRet = client.sayHello("nyannyan");
//			System.out.println(sayHelloRet);
			
			//cooperate with TProtocolRecvDisplay ���Կ������巢�͵���Ϣ
			client.send_sayHello("nyannyan");
			client.send_sayHello("shijun");
			client.send_sayHello("miru");
			client.send_slowSayHello("123");
			client.send_slowSayHello("123");
			
//			TProtocol protocol = new TBinaryProtocol(baseTransport);
//			HelloEcho.Client client = new HelloEcho.Client(protocol);
//			client.send_sayHello("yuko");
//			client.send_sayHello("misaki");

			baseTransport.close();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
