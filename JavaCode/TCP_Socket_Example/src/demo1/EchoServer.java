package demo1;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public void echo() {
    try {
      ServerSocket ss = new ServerSocket(8885);
      Socket socket = ss.accept();
      
      OutputStream rawOut = socket.getOutputStream();
      InputStream rawIn = socket.getInputStream();
      PrintWriter out = new PrintWriter(rawOut);
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(rawIn));
      String data;
      while((data = in.readLine()) != null) {
       
      System.out.println("echo from server:" + data);
      
      out.println("back to client echo from server: " + data);
      out.flush();
      
      }
      
      in.close();
      out.close();
      socket.close();
      ss.close();
    } catch (IOException e) {
      System.out.println("Socket Exception!");
    }
  }

  public static void main(String[] args) {
    EchoServer eserver = new EchoServer();
    eserver.echo();
  }
}
