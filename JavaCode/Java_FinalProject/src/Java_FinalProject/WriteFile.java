package Java_FinalProject;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteFile {
	private File file;
	private String fileContent;
	public WriteFile(File file, String fileContent) {
		try {
			this.file = file;
			this.fileContent = fileContent;
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	public File getFile() {
		return file;
	}
	public String getFileContent() {
		return fileContent;
	}
}
