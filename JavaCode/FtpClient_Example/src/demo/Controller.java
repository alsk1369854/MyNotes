package demo;

import java.io.IOException;

import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

public class Controller {
	FileGui fg;
	FileSelector fs;
	FtpConsole ftpC;
	String cp;
	
	public Controller(FileGui fileGui, FileSelector fileSelector, FtpConsole ftpConsole, String clientPath) {
		fg = fileGui;
		fs = fileSelector;
		ftpC = ftpConsole;
		cp = clientPath;
		
		fg.ct = this;
		fs.ct = this;
		ftpC.ct = this;
	}
	
	void getListName(){
		fg.DLM.removeAllElements();
		for(int i=0; i<ftpC.files.length; i++) {
			String name = ftpC.files[i].getName();
			if (ftpC.files[i].getType() == FtpDirEntry.Type.DIR) name += "(dir)";
			fg.DLM.addElement(name);
		}
	}
	
	String getWorkingDir() {
		return ftpC.workingDir;
	}
	
	void changeDir(String dir){
		dir = dir.replace("(dir)", "");
		try {
			ftpC.changeDir(dir);
		} catch (FtpProtocolException | IOException e) {
			e.printStackTrace();
		}
	}
	
	void changeToParentDir() {
		try {
			ftpC.changeToParentDir();
		} catch (FtpProtocolException | IOException e) {
			e.printStackTrace();
		}
	}
	
	void getFile(String selectFile){
		ftpC.getFile(cp, selectFile);
	}

	void putFile() {
		ftpC.putFile(fs.select());
	}
}
