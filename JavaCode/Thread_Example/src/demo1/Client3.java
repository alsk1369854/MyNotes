package demo1;
//multiple threads running 

public class Client3 {
	public static void main(String[] args) {
		Thread1 t1 = new Thread1 ("T1");
		Thread1 t2 = new Thread1 ("T2");
		
		t1.start();
		t2.start();
		
		for (int i=0; i<10; i++)
			System.out.println("main : " + i);
		

	}

}

class Thread1 extends Thread{
	public void run(){
		for (int i=0; i<10; i++)
			System.out.println(getName()+ ": " + i);
	}
	
	Thread1(String name){
		super(name);
	}
}

