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
 * server端和client端 都需要注意如果一个server(一个hostname:port)组合可以提供多个RPC调用的话
 * 使用TMultiplexedProcessor,根据TMessage中的提取出来的serviename找到实际的Processor,
 * 相对的client端在调用的时候需要跟定servicename让server知道调用的是哪个service(接口类),对应的使用
 * TMultiplexedProtocol-包装TTProtocol在原有的基础上添加service metadata
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
					//Args中默认in,out protocolFactory都是TBinaryProtocol.Factory 所以可以不指定
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
