/*
本程式必須與 UdpServer.java 程式搭配執行，先執行 UdpServer 再執行本程式。
本系統執行步驟:
1. 由 client 送出 "SUBSCRIBE"請求給 server
2. server 收到 "SUBSCRIBE"請求後， 連續送出 N 個模擬 packets 給 client
3. client 到到 packet 後，即印出 packet 訊息。
4. server 送完這 N 個 packets後，送出 "FINISH" 訊息給 client。
5. client 收到 "FINISH" 訊息後，即結束通訊，並印出 FINISH 訊息。
 */

package ex2;

import java.io.IOException;
import java.net.*;

public class UdpClient extends Thread {
	InetAddress ip;
	int port;

	private UdpClient(String host, int port) {
		try {
			ip = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.port = port;
	}

	public static void main(String args[]) throws Exception {
		new UdpClient("localhost", 5000).start();
	}

	public void run() {
		int SIZE = 8192;
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		DatagramPacket packet;
		byte[] buffer;
		String msg = "SUBSCRIBE";
		buffer = msg.getBytes();
		packet = new DatagramPacket(buffer, buffer.length, ip, port);
		System.out.println("Client: subscribing ...");
		try {
			socket.send(packet);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			buffer = new byte[SIZE];
			packet = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msg = new String(buffer, 0, packet.getLength());
			if (msg.equals("FINISH")) {
				System.out.println("Client: Get packet from Server with message " + msg);
			
				break;
		   }
			System.out.println("Client: Get packet from Server with message " + msg);
		}
		socket.close();
	}
}
