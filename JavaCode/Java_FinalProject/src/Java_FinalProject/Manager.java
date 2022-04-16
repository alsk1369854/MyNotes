package Java_FinalProject;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Manager extends Human{
	public Manager(ArrayList<Object[]> orderList) {
		super(orderList);
	}	
	
	// 開店初始化
	public void openShop() {
		File file = new File("data\\day\\dailyReport.dat");
		String fileContent = "總銷售: 0\n";
		new WriteFile(file,fileContent);
	}
	// 顯示目前業績報表
	public void showDailyReport() {
		File file = new File("data\\day\\dailyReport.dat");
		String fileContent = new ReadFile(file).toString();
		file = new File("C:\\收營管理系統");
		file.mkdirs();
		try {
			file = new File("C:\\收營管理系統\\業績報告.txt");
			file.createNewFile();
			new WriteFile(file, fileContent);
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists()) {         //checks file exists or not  
				desktop.open(file);   //opens the specified file  
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		JOptionPane.showMessageDialog(null, "已存至 C:\\收營管理系統");
	}
	// 增加菜單
	public void addMenu(String type, String name, String cost) {
		File file = new File("data\\product\\" + type + ".dat");
		String newFileContent = name + " " + cost + "\n";
		String fileContent = new ReadFile(file).toString();
		newFileContent = newFileContent.concat(fileContent);
		new WriteFile(file,newFileContent);
	}
	// 取得類別菜單所有名稱
	public String[] getMenuItems(String type) {
		String[] items;
		if(type.equals("套餐")) {
			items = new String[setMenu.size()];
			for(int i=0; i<setMenu.size(); i++) {
				items[i] = setMenu.get(i).getName();
			}
		}else {
			items = new String[singleChoice.size()];
			for(int i=0; i<singleChoice.size(); i++) {
				items[i] = singleChoice.get(i).getName();
			}
		}
		return items;
	}
	// 刪除菜單項目
	public void removeMenu(String type, String product) {
		File file = new File("data\\product\\" + type + ".dat");
		String newFileContent = "";
		String[] fileContent = new ReadFile(file).toString().split("\n");
		for(String i : fileContent) {
			String name = i.split(" ")[0];
			if(!name.equals(product)) {
				newFileContent = newFileContent.concat(i + "\n");
			}
		}
		new WriteFile(file, newFileContent);
	}
	
	
	
}//end class
