package com.kibou.test.thrift.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TProtocolRecvDisplay {
	public static void main(String[] args) throws IOException {
		new TProtocolRecvDisplay().serve();
	}
	
	private volatile boolean stopped_;
	
	private ExecutorService executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors() * 2,
			new ThreadFactory() {
				int count = 0;
				public Thread newThread(Runnable target) {
					return new Thread(target, "reader-pool-" + count++);
				}
			});
	
	public void serve() throws IOException{
		try(ServerSocket serverSocket = new ServerSocket(9090, 10)){
			while(!stopped_){
				Socket accept = serverSocket.accept();
				executorService.execute(new MessageReader(accept));
			}
		}
	}
	
	public void stop(){
		stopped_ = true;
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class MessageReader implements Runnable{
		private final Socket accepted;
		private int keepAliveTimeout = /*5000*/ 0;
		
		public MessageReader(Socket socket){
			this.accepted = socket; 
		}
		public void run() {
			try {
				accepted.setReceiveBufferSize(50);
				accepted.setSoTimeout(keepAliveTimeout);
				int count = 0;
				while(!stopped_){
					InputStream inputStream = accepted.getInputStream();
					
					byte[] buffer = new byte[256];
					int got = inputStream.read(buffer);
					if(got <= 0)
						break;
					System.out.print(count++ + " : ");
					System.out.println(new String(buffer,0,got,"utf-8"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
