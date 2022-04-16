package demo1;
import java.io.IOException;
import java.net.ServerSocket;

public class PortScanner {
	public void scan(){

		int count = 0;
		for (int i=1; i< 65535; i++){
			try {
				ServerSocket ss = new ServerSocket(i);
			} catch (IOException e) {
				System.out.println("port "+ i + " Scanned");
				count ++;
			}
		}
		System.out.println("count: " + count);
	}

	public static void main(String[] args) {
		PortScanner pserver = new PortScanner();
		pserver.scan();
	}
}
