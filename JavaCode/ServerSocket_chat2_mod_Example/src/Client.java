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

	public void run() {
		OutputStream out;
		InputStream in;
		try {
			out = gui.s.getOutputStream();
			in = gui.s.getInputStream();
			int n;
			byte[] buf = new byte[1024];

			while (true) {
				System.out.print("1\n");
				if (gui.send_flag == 1) {
					String message = gui.tfMessage.getText();
					gui.taBoard.append("Client: " + message + "\n");
					out.write(message.getBytes());
					n = in.read(buf);
					String returnedMessage = new String(buf, 0, n);
					gui.taBoard.append("Server: " + returnedMessage + "\n");
					gui.send_flag = 0;
					gui.tfMessage.setText("");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
