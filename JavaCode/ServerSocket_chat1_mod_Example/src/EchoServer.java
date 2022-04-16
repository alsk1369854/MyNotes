import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer extends Thread {
	public static void main(String [] args){
		new EchoServer().start();
	}
	
	public void run(){
		byte[] buf = new byte[1024];
		int n=0;
		ServerSocket svs;
		
		try {
			svs = new ServerSocket(3000);
			Socket s = svs.accept();
			InputStream in =s.getInputStream();
			OutputStream out = s.getOutputStream();
			for(int i =0; i<5; i++){
				n = in.read(buf);
				System.out.println("N: " + n);
				String message = new String(buf,0, n);
				message += " (Echo from Server)";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
				System.out.println("Server receive: " + message);
				out.write(message.getBytes());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}


