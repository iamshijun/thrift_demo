package com.kibou.test.thrift.client;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import tutorial.Calculator;
import tutorial.HelloEcho;
import tutorial.HelloEcho.Client.Factory;
import tutorial.InvalidOperation;
import tutorial.Operation;
import tutorial.Work;

public class MultiplexedServiceClient {
	
	public static void main(String[] args) throws TTransportException{
		TTransport tsocket = new TSocket("localhost", 9090);
		//new TNonblockingSocket("localhost", 9090); 
		tsocket.open();
		
		TProtocol baseProtocol; /*new TBinaryProtocol(tsocket)*/;
		baseProtocol = new TJSONProtocol(tsocket);
		
		TMultiplexedProtocol helloEchoMultiplexedProtocol = new TMultiplexedProtocol(baseProtocol, HelloEcho.class.getSimpleName());
		
		//HelloEcho.Client helloEchoClient = new HelloEcho.Client(helloEchoMultiplexedProtocol);
		Factory factory = new HelloEcho.Client.Factory();
		HelloEcho.Client helloEchoClient = factory.getClient(helloEchoMultiplexedProtocol);
		
		TMultiplexedProtocol calculatorMultiplexedProtocol = new TMultiplexedProtocol(baseProtocol, Calculator.class.getSimpleName());
		Calculator.Client caculatorClient = new Calculator.Client(calculatorMultiplexedProtocol);
		//ps: server端相对应的使用的是 TMultiplexedProcessor
		
		try {
			String echo = helloEchoClient.sayHello("shijun");
			System.out.println(echo);
		} catch (TException e) {
			e.printStackTrace();
		}
		
		try {
			Work work = new Work();
			work.num1 = 1989;
			work.num2 = 1994;
			work.op = Operation.SUBTRACT;
			int ret = caculatorClient.calculate(2, work);
			System.out.println(ret);
		} catch (InvalidOperation e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
		tsocket.close();
	}
}
