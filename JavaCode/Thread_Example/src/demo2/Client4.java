//Example of wait/notify

package demo2;
public class Client4 {
	public static void main(String[] args) {
		TD1 obj = new TD1();
		obj.start();
		new TD2(obj).start();
	}
}

class TD1 extends Thread {
	int total;
	boolean readyToPrint;

	synchronized void sum() {
		if (readyToPrint) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		for (int i = 0; i <= 10; i++) {
			total += i;
		}
		}
		readyToPrint = true;
		notify();
	}

	synchronized void print() {
		if (!readyToPrint) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		System.out.println("Now printing the sum ...");
		System.out.println("sum = " + total);
		readyToPrint = false;
		notify();
	}

	public void run() {
		while (true)
			sum();
	}
}

class TD2 extends Thread {
	TD1 obj;

	TD2(TD1 obj) {
		this.obj = obj;
	}

	public void run() {
		while (true)
			obj.print();
	}

}