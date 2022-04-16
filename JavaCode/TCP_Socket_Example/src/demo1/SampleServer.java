package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		Socket socket = ss.accept();
		for (int i=0; i<3; i++){
		OutputStream rawOut = socket.getOutputStream();
		InputStream rawIn = socket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(rawIn));
		PrintWriter out = new PrintWriter(rawOut);
		out.println("Dear Mr. Client, You have three rounds to talk with the server.");
		out.flush();
		System.out.println("Client:" + in.readLine());
		
		out.println("hello: " + i);
		out.flush();
		}
		socket.close();
		ss.close();
	}
}