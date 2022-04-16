import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

public class test{
	public static void main(String[] args) {
		File file = new File ("data\\Account.dat");
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			String fileContent = "123 測試 ABC :|*";
			writer.write(fileContent);
			writer.close();
			
			InputStreamReader reade = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			System.out.println(reader.readLine());
			
		}catch(Exception e) {
			System.err.println(e);
		}
	}//end main
}//end class