
public class Printer extends Thread{
	Target target;
	Printer(Target target){
		this.target = target;
	}
	
	public void run() {
		if(Main.MAX_SIZE != 0) {
			while(true) {
				print();
			}
		}
	}
	
	void print() {
		synchronized(target) {
			if(!Main.readyToPrint) {
				try {
					target.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			int index = 0;
			while(true) {
				System.out.println(target.target[index] + "              (Get number)");
				target.count--;
				index++;
				if(target.count == 0) {
					break;
				}
			}
			Main.readyToPrint = false;
			target.notify();
		}
	}
}
/*
21      (Put number)
90      (Put number)
93              (Get number)
71              (Get number)
87              (Get number)
21              (Get number)
90              (Get number)
81
*/