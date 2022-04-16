package demo1;

// A simplest example: using a single class which extends Thread

public class SimpleThread extends Thread {
	public static void main(String[] args) {
		System.out.println("hello");
		Thread thread = new SimpleThread("T1");
		thread.start();

	}

	public void run() {
		System.out.println(getName() + ": " + "thread is running");
	}
	
	SimpleThread(String name) {
		super(name);
	}
}
