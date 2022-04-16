import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	ServerGui gui;
	Socket s;
	Server(ServerGui gui){
		this.gui = gui;
	}
	
	public void run() {
		gui.taBoard.append("開始聆聽於 " + gui.tfPort.getText() + "\n");
		
		byte[] buf = new byte[1024];
		int n = 0;
		s = null;

		try {
			s = gui.svs.accept();
			gui.taBoard.append("Client 已經連線 " + s.getRemoteSocketAddress() +"\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			while(true) {
				System.out.println("2");
				if(gui.sf.serverSendFlag == 1) {					
					String sendMessage = gui.tfMessage.getText();
					gui.taBoard.append("Server: " + sendMessage + "\n");
					out.write(sendMessage.getBytes());
					gui.sf.serverSendFlag = 0;
					gui.tfMessage.setText("");
				}
				if(gui.sf.clientSendFlag == 1) {
					String receivedMessage;
					n = in.read(buf);
					receivedMessage = new String(buf, 0, n);
					gui.taBoard.append("Client: " + receivedMessage + "\n");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			gui.svs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args) {
		ServerGui sg = new ServerGui();
		try {
			sg.svs = new ServerSocket(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Server(sg).start();
	}
	*/
}
