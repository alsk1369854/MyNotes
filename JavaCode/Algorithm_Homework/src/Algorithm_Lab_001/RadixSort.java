package Algorithm_Lab_001;

import java.util.ArrayList;

public class RadixSort {
	// Field of Variables
	private ArrayList<Student> students;
	private Student[] oldStudents;
	private Student[] newStudents;
	private String course;
	private int[] number;
	
	// Field of Constructor
	public RadixSort(ArrayList<Student> arrayList,String course) {
		this.course = course;
		this.students = arrayList;
		oldStudents = new Student[students.size()];
		newStudents = new Student[students.size()];
		
		for(int i=0; i<students.size(); i++) {
			oldStudents[i] = students.get(i); 
		}
		countingSort(1);
		countingSort(10);
		//countingSort(100);
		for(int i=0; i<students.size(); i++) {
			students.set(i, newStudents[i]);
		}
	}
	
	public ArrayList<Student> getAnswers(){
		return students;
	}
	
	private void countingSort(int k) {
		int[] count = new int[10]; // 0 ~ 9
		vectorNumber(k);
		for(int i=0; i<number.length; i++) {
			count[number[i]] += 1;		
		}
		for(int i=1; i<10; i++) {
			count[i] += count[i-1];
		}
		for(int i=number.length-1 ; i>=0; i--) {
			newStudents[(number.length-1) - (count[number[i]]-1)] = oldStudents[i]; 
			count[number[i]]--;
		}
	}
	
	private int[] vectorNumber(int k) {
		this.number = new int[students.size()];
		for(int i=0; i<students.size(); i++) {
			number[i] = (int)((oldStudents[i].getGrades(course))%(k*10))/k;
		}
		return number;
	}
	
}

