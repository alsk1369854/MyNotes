/*
本程式必須與 UdpClient.java 程式搭配執行，先執行本程式再執行 UdpClient。
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

public class UdpServer extends Thread {
	int port;

	private UdpServer(int port) {
		this.port = port;
	}

	public static void main(String args[]) throws Exception {
		new UdpServer(5000).start();
	}

	public void run() {
		int SIZE = 8192;
		DatagramSocket socket = null;
		byte buffer[] = new byte[SIZE];
		InetAddress clientIp = null;
		int clientPort = -1;
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String msg = new String(buffer, 0, packet.getLength());
		if (msg.equalsIgnoreCase("SUBSCRIBE")) {
			System.out.println("A client has subscribed");
			clientIp = packet.getAddress();
			clientPort = packet.getPort();
			sendPackets(socket, clientIp, clientPort);
		}
		msg = "FINISH";
		buffer = msg.getBytes();
		packet = new DatagramPacket(buffer, buffer.length, clientIp, clientPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}

	void sendPackets(DatagramSocket socket, InetAddress ip, int port) {
		byte[] buffer;
		for (int i = 0; i < 20; i++) {
			String msg = "Packet " + i;
			buffer = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
					ip, port);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}