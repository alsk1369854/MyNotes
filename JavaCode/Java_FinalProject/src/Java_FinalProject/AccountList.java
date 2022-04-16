package Java_FinalProject;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class AccountList {
	private ArrayList<Account> account = new ArrayList<Account>();	
	private int userIndex ;//紀錄上次查找用戶
	
	public AccountList() {
		//建立使用者
		buildAccount(new File("data\\account.dat"), account);
	}
	
	//====================================================================
	public boolean checkUserExist(String id) {
		for(int i=0; i<account.size(); i++) {
			if(account.get(i).getId().equals(id)) {
				//紀錄用戶
				userIndex = i;
				return true;
			}
		}
		return false;
	}
	public Account getUser(){
		return account.get(userIndex);
	}
	
	//=====================================================================
	private boolean buildAccount(File file, ArrayList<Account> arrayList) {
		try {
			InputStreamReader reade = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				String id = line.split(" ")[0];
				String authority = line.split(" ")[1];
				arrayList.add(new Account(id, authority));
			}
			reader.close();
		}catch(Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
}
