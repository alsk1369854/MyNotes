package demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

public class FtpConsole {
	static FtpClient client;
	static FtpDirEntry[] files;
	
	static String[] listName;
	String workingDir;
	Controller ct;
	
	
	
	
	FtpConsole() throws FtpProtocolException,	IOException {
		//FtpConsole console = new FtpConsole();
		client = FtpClient.create("localhost");
		client.login("abc", "abc".toCharArray());
		client.setBinaryType();
		System.out.println("User connected");
		files = lsFiles();
		getFiles();
		workingDir = client.getWorkingDirectory();
		System.out.println("Working Directory = " + workingDir);
		System.out.println("list files and directories ...\n");
		

		/* changeDirrectory
		client.changeDirectory("Dir1");
		workingDir = client.getWorkingDirectory();
		System.out.println("Working Directory = " + workingDir);
		files = lsFiles();
		System.out.println("list files and directories ...");
		getFiles();
		*/
		
		/* changeToParentDirectory
		System.out.println("return to paraent directory ...");
		client.changeToParentDirectory();
		workingDir = client.getWorkingDirectory();
		System.out.println("Working Directory = " + workingDir);
		files = lsFiles();
		getFiles();
		System.out.println("list files and directories ...");
		*/

		/*
		Get And Put file
		console.getFile("D:\\home_client", "A2.txt"); // 從Server取下來
		console.putFile(new File("D:\\home_client\\A1_from_client.txt")); // 從Client Put 出去
		*/
	}
	static void getFiles() {
		for (int i = 0; i < files.length; i++) {
			String name = files[i].getName();
			if (files[i].getType() == FtpDirEntry.Type.DIR)
				name += "(dir)";
			System.out.println(name);
		}
	}
	
	void changeDir(String dir) throws FtpProtocolException, IOException {
		client.changeDirectory(dir);
		System.out.println("change to " + dir + " ...");
		workingDir = client.getWorkingDirectory();
		System.out.println("Working Directory = " + workingDir);
		files = lsFiles();
		getFiles();
		System.out.println("list files and directories ...\n");
	}
	
	void changeToParentDir() throws FtpProtocolException, IOException {
		System.out.println("return to paraent directory ...");
		client.changeToParentDirectory();
		workingDir = client.getWorkingDirectory();
		System.out.println("Working Directory = " + workingDir);
		files = lsFiles();
		getFiles();
		System.out.println("list files and directories ...\n");
	}
	
	FtpDirEntry[] lsFiles() {
		ArrayList<FtpDirEntry> al = new ArrayList<FtpDirEntry>();
		Iterator<FtpDirEntry> it = null;
		try {
			it = client.listFiles(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (it.hasNext()) {
			FtpDirEntry entry = it.next();
			al.add(entry);
		}
		FtpDirEntry[] array = new FtpDirEntry[al.size()];
		al.toArray(array);
		return array;
	}

	void getFile(String dir, String filename) {
		File inFile = new File(dir + "\\" + filename);
		try {
			client.getFile(inFile.getName(), new FileOutputStream(inFile));
			System.out.println("Get file: " + inFile.getName() + "\n");

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

	}

	void putFile(File file) {
		try {
			client.putFile(file.getName(), new FileInputStream(file));
			System.out.println("Put file: " + file.getName() + "\n");
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}
	

}
