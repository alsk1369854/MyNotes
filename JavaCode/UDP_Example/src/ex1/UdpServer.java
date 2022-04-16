
// 本程式必須與 UdpClient.java 程式搭配執行，先執行本程式再執行 UdpClient。
// 程式由 client 連續送出 10 個模擬  packets (Packet 0 ~ Packet 9) 給 server
package ex1;
import java.io.IOException;
import java.net.*;
 
public class UdpServer extends Thread{
	int port;
	private UdpServer(int port){
		this.port = port;
	}
	
    public static void main(String args[]) throws Exception {
    	new UdpServer(5000).start();
    }
    
    public void run(){
    	 int SIZE = 8192;
         byte buffer[] = new byte[SIZE]; 
         DatagramSocket socket = null;
         try {
				socket = new DatagramSocket(port);
			} catch (SocketException e) {
				e.printStackTrace();
			}
         for (int count = 0; count<10 ; count++) {
             DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
             		         
             try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}                                   
            String msg = new String(buffer, 0, packet.getLength());   
            System.out.println("Server: Receiving ... "+ msg);                                                         
         }
        socket.close();       
    }
}