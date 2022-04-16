
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DisplayHeader {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:80/demo/Demo.html");
			URLConnection conn = url.openConnection();
			System.out.println("Object's Class name =>" + url.getClass().getName());
			System.out.println("Content Type:" + conn.getContentType());
			System.out.println("Content Length:" + conn.getContentLength());
			System.out.println("Date:"  + new Date(conn.getDate()));
			System.out.println("Last Modified:" + conn.getHeaderField("Last-Modified"));
			
			
			// All Mate Data
			System.out.println("\n### All Mate Data");
			Map<String, List<String>> map = conn.getHeaderFields();
			for(String key: map.keySet()) {
				for(String value: map.get(key)) {
					System.out.println(key + ": " + value);
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
    }
  }
}
