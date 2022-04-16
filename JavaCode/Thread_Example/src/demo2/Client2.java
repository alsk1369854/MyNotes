//synchronized example 1: lock an irrelevant object
package demo2;

public class Client2 {
	public static Object obj = new Object();
	public static void main(String[] args) throws InterruptedException {
		ThreadA t = new ThreadA();
		t.start();
		synchronized (obj) {
		System.out.println("Client A is waiting for t to complete...");	
			obj.wait();
			System.out.println("Total is: " + t.total);
		}
	}
}

class ThreadA extends Thread {
	int total;

	@Override
	public void run() {
		synchronized (Client2.obj) {
			for (int i = 0; i <= 10; i++)
				total += i;
			Client2.obj.notify();
		}
	}
}
