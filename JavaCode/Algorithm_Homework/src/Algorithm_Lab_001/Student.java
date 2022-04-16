package Algorithm_Lab_001;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Student implements Student_interface{ 
	private Map<String, Integer> map = new HashMap<>();
	private String id;
	
	public Student(String data) {
		int length = data.split(" ").length;
		this.id = data.split(" ")[0];
		for(int i=1 ; i<length ; i+=2) {
			map.put(data.split(" ")[i], Integer.valueOf(Integer.parseInt(data.split(" ")[i+1])));
		}
	}
	
	
	@Override
	public String getStudentId() {
		return id;
	}
	@Override
	public int getGrades(String key) {
		if(map.get(key) != null) {
			return map.get(key).intValue();
		}else {
			return 999;
		}
	}
	public Map getMap() {
		return map;
	}
	public void setGrades(String key, int grades) {
		map.put(key, Integer.valueOf(grades));
	}
	public String getContent() {
		String data = id;
		for(Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator(); entries.hasNext();) {
			Map.Entry<String, Integer> entry = entries.next();
			data = data.concat(" " + entry.getKey() + " " + entry.getValue());
		}
		return data;
	}


}
