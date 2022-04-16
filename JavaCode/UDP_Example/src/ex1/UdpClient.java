//本程式必須與 UdpServer.java 程式搭配執行，先執行 UdpServer 再執行本程式。
//本程式由 client 連續送出 10 個模擬  packets (Packet 0 ~ Packet 9) 給 server
package ex1;
import java.io.IOException;
import java.net.*;

public class UdpClient extends Thread {
    InetAddress ip;
    int port;
   
    private UdpClient(String host, int port){
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
   
    public void run(){
        DatagramSocket socket = null;
       try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
       
       DatagramPacket packet;
       
       byte[] buffer;
        for (int i=0; i<10; i++) {
        	String msg = "Packet " + i;  
        	buffer = msg.getBytes(); 
        	packet = new DatagramPacket(buffer, buffer.length, ip, port); 
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
        socket.close();
    }
 }
