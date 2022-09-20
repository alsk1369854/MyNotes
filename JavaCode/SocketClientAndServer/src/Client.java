import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends Thread{
	ClientGui gui;
	
	Client(ClientGui gui){
		this.gui = gui;
	}
	
	public void run() {
		int n;
		byte[] buf = new byte[1024];
		OutputStream out;
		InputStream in;
		
		try {
			out = gui.s.getOutputStream();
			in = gui.s.getInputStream();
			gui.taBoard.append("連線成功\n");
			while(true) {
				System.out.println("1");
				if(gui.sf.clientSendFlag == 1) {
					
					String sendMessage = gui.tfMessage.getText();
					gui.taBoard.append("Client: " + sendMessage + "\n");
					out.write(sendMessage.getBytes());
					gui.sf.clientSendFlag = 0;
					gui.tfMessage.setText("");
				}	
				if(gui.sf.serverSendFlag == 1) {
					String returnMessage;
					n = in.read(buf);
					returnMessage = new String(buf, 0, n);
					gui.taBoard.append("Server: " + returnMessage + "\n");
				}
			}
		} catch (SocketException e) {
			gui.sf.clientSendFlag = 0;
			gui.taBoard.append("Server 已斷開\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args) {
		ClientGui cg = new ClientGui();
		try {
			cg.s = new Socket("127.0.0.1", 3000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Client(cg).start();
	}
	*/

}
