package demo;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

class FileSelector extends JFrame{
	
	Controller ct;
	String clientPath;
	
	FileSelector(String clientPath){
		this.clientPath = clientPath;
		/*
		FileSelector fs = new FileSelector();
		File file = this.select();
		System.out.println("file selected (fully qualified name): " + file.getAbsolutePath());
		System.out.println("file selected (file name only): " + file.getName());
		*/
 	}

	File select(){
		JFileChooser fileChooser = new JFileChooser();
		File selectedFile = null;
		fileChooser.setCurrentDirectory(new File(clientPath));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			String dir =  fileChooser.getCurrentDirectory().getName(); 
			fileChooser.setCurrentDirectory(new File (dir));
		}
		System.out.println("file selected (fully qualified name): " + selectedFile.getAbsolutePath());
		System.out.println("file selected (file name only): " + selectedFile.getName());
		return selectedFile;
	}
}