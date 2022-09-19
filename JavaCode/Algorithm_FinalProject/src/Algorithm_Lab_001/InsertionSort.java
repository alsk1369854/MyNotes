package Algorithm_Lab_001;

import java.util.ArrayList;

public class InsertionSort {
	// Field of Variables
	private ArrayList<Student> students;
	private String course;
	private int comparisonCount = 0;
	private int exchangeCount = 0;
	
	// Field of Constructor
	public InsertionSort(ArrayList<Student> arrayList,String course) {
		this.course = course;
		this.students = arrayList;
		// define the key according to the students to be compared
		for(int j=1; j<students.size(); j++) {
			Student key = students.get(j);
			int i = j-1;
			// Sorting
			while(i>=0 && students.get(i).getGrades(course)<key.getGrades(course)) {
				comparisonCount ++;
				exchangeCount ++;
				students.set(i+1, students.get(i));
				i = i-1;
				
				if(!(i>=0 && students.get(i).getGrades(course)<key.getGrades(course))) {
					comparisonCount --;
				}
			}
			comparisonCount ++;
			students.set(i+1, key);
		}
	}
	
	// Field of public Methods
	public ArrayList<Student> getAnswers() {
		return students;
	}
	public int getComparisonCount() {
		return comparisonCount;
	}
	public int getExchangeCount() {
		return exchangeCount;
	}
}
