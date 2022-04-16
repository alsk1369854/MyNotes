package demo1;
//simply use a Runnable object 

public class Client1 {
	public static void main(String[] args) {
		Thread t3 = new Thread(new Runnable3());
		//t3.start();//threading
		t3.run();//non-threading
		t3.run();//non-threading
		
				
	}
}

class Runnable3 implements Runnable{
	public void run() {
		System.out.println("Using Runnalbe's run method");
	}
}