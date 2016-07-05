package com.kibou.test.thrift.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import thrift.apache.org.CalculatorHandler;
import thrift.apache.org.HelloEchoHandler;
import tutorial.Calculator;
import tutorial.HelloEcho;

/**
 * server�˺�client�� ����Ҫע�����һ��server(һ��hostname:port)��Ͽ����ṩ���RPC���õĻ�
 * ʹ��TMultiplexedProcessor,����TMessage�е���ȡ������serviename�ҵ�ʵ�ʵ�Processor,
 * ��Ե�client���ڵ��õ�ʱ����Ҫ����servicename��server֪�����õ����ĸ�service(�ӿ���),��Ӧ��ʹ��
 * TMultiplexedProtocol-��װTTProtocol��ԭ�еĻ��������service metadata
 */
public class MultiplexedServiceServer {

	public static void main(String[] args) {
		try {

			Runnable simple = new Runnable() {
				public void run() {
					TServerTransport serverTransport;
					try {
						serverTransport = new TServerSocket(9090);
					} catch (TTransportException e) {
						throw new RuntimeException(e);
					}

					CalculatorHandler calculatorHandler = new CalculatorHandler();
					Calculator.Processor<CalculatorHandler> calculatorProcessor = new Calculator.Processor<>(
							calculatorHandler);

					HelloEchoHandler helloEchoHandler = new HelloEchoHandler();
					HelloEcho.Processor<HelloEchoHandler> helloEchoProcessor = new HelloEcho.Processor<>(
							helloEchoHandler);

					TMultiplexedProcessor multiplexedProcessor = new TMultiplexedProcessor();
					multiplexedProcessor.registerProcessor(Calculator.class.getSimpleName(),
							calculatorProcessor);
					multiplexedProcessor.registerProcessor(HelloEcho.class.getSimpleName(),
							helloEchoProcessor);

					TProtocolFactory protocolFactory = new TJSONProtocol.Factory()/*new TCompactProtocol.Factory()*/;
					//Args��Ĭ��in,out protocolFactory����TBinaryProtocol.Factory ���Կ��Բ�ָ��
					/*TServer server = new TSimpleServer(
							new Args(serverTransport)
									.processor(multiplexedProcessor)
									.protocolFactory(protocolFactory));*/

					// Use this for a multithreaded server
					 TServer server = new TThreadPoolServer(
							 new TThreadPoolServer.Args(serverTransport)
							 .processor(multiplexedProcessor)
							 .protocolFactory(protocolFactory));

					System.out.println("Starting the simple server...");
					server.serve();
				}
			};

			new Thread(simple).start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

}
