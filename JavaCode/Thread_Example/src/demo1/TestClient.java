package demo1;

//what if both a thread and a runnable object concurrently have their own run()? 

public class TestClient {
	public static void main(String[] args) {
      TestThread tt = new TestThread(new TestRunnable());
      tt.start();
	}
}

class TestThread extends Thread{
	TestThread(Runnable r){
		super(r);
	}
	public void run() {
		System.out.println("using Thread's run()");
	}
}

class TestRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println("using Runnable's run()");
		
	}
	
}