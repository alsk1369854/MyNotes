package Algorithm_Lab_001;

import java.util.ArrayList;

public class MergeSort {
	
	// Field of Variables
	private String course;
	private ArrayList<Student> students;
	private int comparisonCount = 0;
	private int exchangeCount = 0;
	
	// Field of Constructor
	public MergeSort(ArrayList<Student> students, String course ) {
		this.course = course;
		this.students = students;
		// Start sort
		sort(students, 0, students.size()-1);
	}
	
	// Field of public Methods
	public ArrayList<Student> getAnswers(){
		return students;
	}
	public int getComparisonCount() {
		return comparisonCount;
	}
	public int getExchangeCount() {
		return exchangeCount;
	}
	
	// Field of private Methods
	private void sort(ArrayList<Student> students, int p, int r) {
		if(p<r) {
			int q = (p+r)/2;
			// Left half
			sort(students, p, q);
			// Right half
			sort(students, q+1, r);
			merge(students, p, q, r);
		}
	}
	private void merge(ArrayList<Student> students, int p, int q, int r) {
		// Build sub Array
		int n1 = q-p+1;
		int n2 = r-q;
		Student[] leftArray = new Student[n1+1];
		Student[] rightArray = new Student[n2+1];
		int j,i;
		for(i=0; i<n1; i++) {
			leftArray[i] = students.get(p + i);
		}
		for(j=0; j<n2; j++) {
			rightArray[j] = students.get(q + j+1);
		}
		
		// Infinitely small last index
		Student tmpe = new Student("99999");
		tmpe.setGrades(course, -999);
		leftArray[n1] = tmpe;
		rightArray[n2] = tmpe;
		
		// Merging
		i=0;
		j=0;
		for(int k=p; k<=r; k++) {
			System.out.println(students.get(k).getStudentId());
			comparisonCount ++;
			if(leftArray[i].getGrades(course) >= rightArray[j].getGrades(course)) {
				exchangeCount ++;
				this.students.set(k, leftArray[i]);
				i++;
			}else {
				exchangeCount ++;
				this.students.set(k, rightArray[j]);
				j++;
			}
		}
	}

}
