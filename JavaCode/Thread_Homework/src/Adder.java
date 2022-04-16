
public class Adder extends Thread{
	Target target;
	Adder(Target target){
		this.target = target;
	}
	
	public void run(){
		if(Main.MAX_SIZE != 0) {
			while(true) {
				add();
			}
		}
	}
	
	void add() {
		synchronized(target) {
			if(Main.readyToPrint) {
				try {
					target.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// random = (int)(Math.random()*(MAX-min+1)) + min;
			while(true) {
				int number = (int)(Math.random()*100);
				target.target[target.count] = number;
				System.out.println(number + "      (Put number)");
				target.count++;
				if(target.count == Main.MAX_SIZE) {
					break;
				}
			}
			Main.readyToPrint = true;
			target.notify();
		}
	}
}
