//multiple threads running example (test with start () and run())  

package demo2;

public class Client5 {
	public static void main(String[] args) {
		ThreadE t1 = new ThreadE("T1");
		ThreadE t2 = new ThreadE("T2");
		t1.start();
		t2.run();

		for (int i = 0; i < 10; i++)
			System.out.println("current thread: "
					+ Thread.currentThread().getName() + ":\t" + i);
	}
}

class ThreadE extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println("current thread: "
					+ Thread.currentThread().getName() + ":\t" + i);
	}

	ThreadE(String name) {
		super(name);
	}
}
