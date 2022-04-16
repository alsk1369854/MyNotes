package demo1;
// simply use a runnable object with a thread name

public class Client2 {
	public static void main(String[] args) {

		Thread thread = new Thread(new MyThread1(), "T2");
		System.out.println(thread.getName());
		thread.start();
	}

}

class MyThread1 implements Runnable{

	@Override
	public void run() {
		System.out.println("using Runnable");
		
	}
	
} 