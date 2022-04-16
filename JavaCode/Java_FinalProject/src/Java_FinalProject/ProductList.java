package Java_FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductList {
	private ArrayList<Product> setMenu = new ArrayList<Product>();
	private ArrayList<Product> singleChoice = new ArrayList<Product>();
	
	public ProductList() {
		//建立套餐
		buildArrayList(new File("data\\product\\套餐.dat"), setMenu);
		//建立單點
		buildArrayList(new File("data\\product\\單點.dat"), singleChoice);
	}
	
	//======================================================================
	public ArrayList<Product> getSetMenu() {
		return setMenu;
	}
	public ArrayList<Product> getSingleChoice(){
		return singleChoice;
	}
	
	//======================================================================
	private boolean buildArrayList(File file, ArrayList<Product> arrayList) {
		try {
			InputStreamReader reade = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				String name = line.split(" ")[0];
				String cost = line.split(" ")[1];
				arrayList.add(new Product(name, cost));
			}
			reader.close();
		}catch(Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	} 
}
