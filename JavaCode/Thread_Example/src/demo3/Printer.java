package demo3;

public class Printer extends Thread {
	Target target;

	Printer(Target target) {
		super();
		this.target = target;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			print();
		}
	}

	void print() {
		synchronized (target) {
			if (!Client.readyToPrint) {
				try {
					target.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(target.total);
			Client.readyToPrint = false;
			target.notify();
		}
	}

}
