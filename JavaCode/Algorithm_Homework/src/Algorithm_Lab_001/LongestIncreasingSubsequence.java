package Algorithm_Lab_001;

import java.util.ArrayList;

public class LongestIncreasingSubsequence {
	private int[] allGrades;
	String result;
	String allCases = "";
	int maxCount = 0;

	public LongestIncreasingSubsequence(ArrayList<Student> students, String course) {
		allGrades = new int[students.size()];
		String content = "";
		int count = 0;
		for(int i=0; i<students.size(); i++) {
			allGrades[i] = students.get(i).getGrades(course);
			if(i > 0) {
				allCases += "," + allGrades[i];
			}else {
				allCases += allGrades[i];
			}
			
		}
		for(int i=1; i<students.size(); i++) {
			content = "" + allGrades[i-1];
			count = 1;
			int tag = allGrades[i-1];
			for(int j=i; j<students.size(); j++) {
				if(tag < allGrades[j]) {
					count++;
					content += "," + allGrades[j];
					tag = allGrades[j];
				}
			}
			if(count > maxCount) {
				result = content;
				maxCount = count;
			}
		}
	}
	public String getAllCases() {
		return allCases;
	}
	public String getResult() {
		return result;
	}
	public int getMaxCount() {
		return maxCount;
	}
}
//DS 成績序列為 60,49,20,50,30,70,100,18，最長遞增子序列長度為 4