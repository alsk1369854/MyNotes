package demo1;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Tester {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("Wating for connection...");
		Socket socket = ss.accept();
		System.out.println("Connected by a client");
	    socket.close();
	    ss.close();
	}
}
