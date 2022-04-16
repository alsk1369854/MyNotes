package Java_FinalProject;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Human {
	ArrayList<Product> setMenu = new ProductList().getSetMenu();
	ArrayList<Product> singleChoice = new ProductList().getSingleChoice();
	private ArrayList<Object[]> orderList = new ArrayList<Object[]>();
	private int profitNow;
	
	public Human(ArrayList<Object[]> orderList){
		this.orderList = orderList;
	}
	
	//public method====================================================================================
	// 取得面前營業額
	public int getProfitNow() {
		return profitNow;
	}
	// 結帳
	public void checkouts(int checkoutMoney) {
		
		File file = new File("data\\day\\dailyReport.dat");
		String newFileContent = "";
		String[] fileContent = new ReadFile(file).toString().split("\n");
		//豬排 * 1 = 60
		//1 豬排 60 1 60
		profitNow = Integer.parseInt(fileContent[0].split(" ")[1]) + checkoutMoney;
		showOrder(checkoutMoney);
		newFileContent = newFileContent.concat(fileContent[0].split(" ")[0] + " " + profitNow + "\n");
		if(fileContent.length>1) {
			int temp = 999;
			for(int i=1; i<fileContent.length; i++) {
				for(int j=0; j<orderList.size(); j++) {
					String fileName = fileContent[i].split(" ")[0];
					String orderName = (String)orderList.get(j)[1];
					if(fileName.equals(orderName)) {
						int totalAmount = Integer.parseInt(fileContent[i].split(" ")[2]) + (int)orderList.get(j)[3];
						int totalMoney = Integer.parseInt(fileContent[i].split(" ")[4]) + (int)orderList.get(j)[4];
						fileContent[i] = fileName + " * " + totalAmount + " = " + totalMoney;
						temp = j;
					}
				}
				if(temp != 999) {
					orderList.remove(temp);
					temp = 999;
				}
				newFileContent = newFileContent.concat(fileContent[i] + "\n");
			}
		}
		if(!orderList.isEmpty()) {
			for(int i=0; i<orderList.size(); i++) {
				newFileContent = newFileContent.concat(orderList.get(i)[1] + " * " + orderList.get(i)[3] + " = " + orderList.get(i)[4] + "\n");
			}
		}	
		orderList.clear();
		new WriteFile(file,newFileContent);
	}
	// 清空表單
	public void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
	// 已訂購清單
	public void addOrderList(String name, int cost, int orderAmount, DefaultTableModel orderTableM) {
		boolean checkInList = false;

		for(int i=0; i<orderList.size(); i++) {
			if(orderList.get(i)[1].equals(name)) {
				Object[] temp = {orderList.get(i)[0],
								 orderList.get(i)[1],
								 orderList.get(i)[2],
								 (int)orderList.get(i)[3] + orderAmount,//數量
								 (int)orderList.get(i)[4] + (cost*orderAmount),//金額
								 orderList.get(i)[5]};
				orderList.set(i, temp);
				checkInList = true;
				break;
			}
		}
		
		if(!checkInList) {
			ListBtn btn = new ListBtn();
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event) {
					int number = (int) orderTableM.getValueAt(ListBtnMouseListener.getRow(), 0);
					removeOrderItem(number,orderTableM);
				}
			});
			Object[] temp = {(int)(orderList.size()+1), name, cost, orderAmount, (cost*orderAmount), btn};
			orderList.add(temp);
		}
		buildOrderTable(orderTableM);
	}
	// 菜單內容
	public void buildMenuTable(String item,  DefaultTableModel menuTableM, DefaultTableModel orderTableM) {
		ArrayList<Product> menu = setMenu;
		ListBtn btn = new ListBtn();
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String name = (String) menuTableM.getValueAt(ListBtnMouseListener.getRow(), 0);
				int cost = (int) menuTableM.getValueAt(ListBtnMouseListener.getRow(), 1);
				new SetAmountFrame(name,cost,orderTableM,new Human(orderList));
			}
		});
		if(item.equals("單點")) {
			menu = singleChoice;
		}
		for(int i=0; i<menu.size(); i++) {
			Object[] temp = {menu.get(i).getName(), menu.get(i).getCost(), btn};
			menuTableM.addRow(temp);
		}
	}
	// 小計至總金額
	public String computerSubtotal() {
		int countTotal = 0;
		for(int i=0; i<orderList.size(); i++) {
			countTotal += (int)orderList.get(i)[4];
		}	
		return "" + countTotal;
	}
	// 清乾淨
	public void cleanOrderList() {
		orderList.clear();
	}
	
	//private method=================================================================================
	//建立訂單Table
	private void buildOrderTable(DefaultTableModel orderTableM) {
		cleanTable(orderTableM);
		for(int i=0; i<orderList.size(); i++) {
			orderTableM.addRow(orderList.get(i));
		}
	}
	// 刪除訂單單項目
	private void removeOrderItem(int number, DefaultTableModel orderTableM) {
		boolean findOut = false;
		int maxSize = orderList.size()-1;
		for(int i=0; i<orderList.size(); i++) {	
			if((int)orderList.get(i)[0] == number) {
				orderList.remove(i);
				findOut = true;
			}
			if(findOut && (i != maxSize)) {
				orderList.get(i)[0] = i+1;
			}
		}
		buildOrderTable(orderTableM);
	}
	// 印出此筆訂單
	private void showOrder(int money) {
		String fileContent = "總金額: " + money + "\n";
		//1 豬排 60 1 60
		for(Object[] i : orderList) {
			fileContent = fileContent.concat(i[0] + " " + i[1] + " " + i[2] + " * " + i[3] + " = " + i[4] + "\n");
		}
		File file = new File("C:\\收營管理系統");
		file.mkdirs();
		try {
			file = new File("C:\\收營管理系統\\上一筆結帳.txt");
			file.createNewFile();
			new WriteFile(file, fileContent);
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists()) {         //checks file exists or not  
				desktop.open(file);   //opens the specified file  
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}//end class
