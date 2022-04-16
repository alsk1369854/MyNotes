//example of using join()

package demo2;
public class Client6 {
	public static void main(String[] args) {
		ThreadE t1 = new ThreadE("T1");
		ThreadE t2 = new ThreadE("T2");
		ThreadE t3 = new ThreadE("T3");

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		t3.start();
		
		for (int i = 0; i < 10; i++)
			System.out.println("current thread: "
					+ Thread.currentThread().getName() + ", main procedure: " + i);
	}

}
