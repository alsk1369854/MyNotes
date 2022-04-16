

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client extends Thread {
	ClientGui gui;
	Client(ClientGui gui) {
		super();
		this.gui = gui;
	}
	
	public void run(){
		OutputStream out;
		InputStream in;
		try {
			
				out = gui.s.getOutputStream();
				in = gui.s.getInputStream();
				int n;
				byte[] buf = new byte[1024];
			
			for(int i=10; i<500; i+=100){
			
				String message = i+ "";
			    System.out.println(message);
			    gui.tfMessage.setText(message+"\n");
			    gui.taBoard.append("Client: " + message+"\n");
			    out.write(message.getBytes());
			     try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    n = in.read(buf);
				String returnedMessage = new String(buf,0, n);
				gui.taBoard.append("Server: " +returnedMessage+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
