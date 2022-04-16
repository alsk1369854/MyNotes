package Algorithm_Lab_001;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteFile {
	public WriteFile(String location, String fileContent) {
		try {
			File file = new File(location);
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
