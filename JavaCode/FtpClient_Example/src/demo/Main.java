package demo;

import java.io.IOException;

import sun.net.ftp.FtpProtocolException;

public class Main {
	/*
		@ open XAMPP FileZilla user:(id:abc, password:abc)
		@ check 'Client File' and 'Server File' is ready
	*/
	static String clientPath = "D:\\home_client";
	
	public static void main(String[] args) throws FtpProtocolException, IOException{
		FileGui gui = new FileGui();
		FileSelector fs = new FileSelector(clientPath);
		FtpConsole ftpC = new FtpConsole();
		
		Controller ct = new Controller(gui,fs,ftpC,clientPath);
		gui.setVisible(true);
	}

}
