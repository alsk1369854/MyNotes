package demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class TimeServer {
  public TimeServer() {
  }

  public void report() {
    try {
      ServerSocket ss = new ServerSocket(8886);
      Date date = Calendar.getInstance().getTime();
      Socket socket = ss.accept();
      PrintWriter out = new PrintWriter(
          socket.getOutputStream());
    out.println("echo from the time serve: " + date);
      out.flush();
      out.close();
      socket.close();
      ss.close();
    } catch (IOException e) {
      System.out.println("Socket Exception");
    }
  }

  public static void main(String[] args) {
	  System.out.println("hello");
    TimeServer tserver = new TimeServer();
    tserver.report();
  }
}
