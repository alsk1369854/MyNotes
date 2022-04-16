//synchronized example 2: lock the running thread object itself

package demo2;

public class Client3 {
	public static void main(String[] args) throws InterruptedException {
		ThreadB t = new ThreadB();
		t.start();
		synchronized (t) {
			System.out.println("Client B is waiting for t to complete ...");
			t.wait();
			System.out.println("Total is: " + t.total);
		}
	}
}

class ThreadB extends Thread {
	int total;

	//synchronized method (the same as synchronized block)
	
	synchronized public void run() {
			for (int i = 0; i <= 10; i++) {
				total += i;
				System.out.println("i = " + i);
			}
			notify();
	}
	

	//synchronized block 
	/*
	public void run() {
		synchronized (this) {
			for (int i = 0; i <= 10; i++) {

				total += i;
				System.out.println("i = " + i);
			}
			notify();
		}
	}
	*/
}
