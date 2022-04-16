package demo3;

class Adder extends Thread{
	Target target;
	Adder(Target target) {
		super();
		this.target = target;
	}
	
	public void run() {
		while (true) {
			sum();
		}
	}

	void sum(){
		synchronized (target) {
		if(Client.readyToPrint) {
			try {
				target.wait();
			} catch (InterruptedException e) {
				;
			}
		}
		target.total += 100;
		Client.readyToPrint = true;
		target.notify();		
	}
	}
}
