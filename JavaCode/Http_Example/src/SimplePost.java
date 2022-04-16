

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class SimplePost {
  public static void main(String[] args)
      throws IOException {
//    String params = "cc=123";
    URL url = new URL("http://localhost:80/demo/Demo.htm");
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
    OutputStreamWriter out = new OutputStreamWriter(
        conn.getOutputStream());
//    out.write(params);
    
    out.flush();
    Reader in = new InputStreamReader(conn.getInputStream(), "UTF-8");
    int data = in.read();
    while (data != -1) {
      System.out.print((char) data);
      data = in.read();
    }
  }
}
