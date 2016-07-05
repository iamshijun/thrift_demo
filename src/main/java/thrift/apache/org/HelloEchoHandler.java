package thrift.apache.org;
import org.apache.thrift.TException;

import tutorial.HelloEcho;

public class HelloEchoHandler implements HelloEcho.Iface {
	@Override
	public String sayHello(String name) throws TException {
		System.out.println("sayHello (name=" + name + ")");
		return "Hello " + name;
	}

	@Override
	public String slowSayHello(String name) throws TException {
		System.out.println("slowSayHello (name=" + name + ")");
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "H.....e.....l.....l.....o " + name;
	}
	
}
