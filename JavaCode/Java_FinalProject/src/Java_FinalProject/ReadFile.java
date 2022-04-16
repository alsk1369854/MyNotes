package Java_FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadFile {
	private File file;
	private String fileContent = "";
	public ReadFile(File file) {
		try {
			this.file = file;
			InputStreamReader reade = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				fileContent = fileContent.concat(line + "\n");
			}
			reader.close();
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	public File getFile() {
		return file;
	}
	public String toString() {
		return fileContent;
	}
}
