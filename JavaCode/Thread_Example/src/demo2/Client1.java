//Wrong example: Thread without a synchronized block

package demo2;

public class Client1{ 
	public static void main(String[] args) throws InterruptedException {
		ThreadC t = new ThreadC();
		t.start();
		System.out.println("Total is " + t.total);
	}
}

class ThreadC extends Thread {
	int total;

	public void run() {
		for (int i = 0; i <= 10; i++)
			total += i;
	}
}

