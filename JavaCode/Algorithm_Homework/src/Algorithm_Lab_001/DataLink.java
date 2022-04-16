package Algorithm_Lab_001;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class DataLink {
	// Field of Variable
	// All students data
	private String data;
	private ArrayList<Student> students = new ArrayList<Student>();
	private Map<String, Map<String, Integer>> studentsMap = new HashMap<>();
	
	// Field of Constructor
	public DataLink(String data) {
		// Build students content
		this.data = data;
		String content = new ReadFile(data).toString();
		for(int i=0; i<content.split("\n").length; i++) {
			System.out.println("Data Link build");
			String line = content.split("\n")[i];
			students.add(new Student(line));
			studentsMap.put(students.get(students.size()-1).getStudentId(), students.get(students.size()-1).getMap());
			System.out.println(studentsMap.get(students.get(students.size()-1).getStudentId()).get("DM"));
		}
	}
	
	// Field of public Methods
	// Save all students data to the DBS
	public void saveStudents() {
		String content = "";
		for(int i=0; i<students.size(); i++) {
			content = content.concat(students.get(i).getContent() + "\n");
		}
		new WriteFile(data, content);
	}	
	// Given course, return InsertionSort result
	public InsertionSort doInsertionSort(String course) {
		ArrayList<Student> students = havaCourseStudents(course);
		return new InsertionSort(students, course);
	}
	// Given course, return MergeSort result
	public MergeSort doMergeSort(String course) {
		ArrayList<Student> students = havaCourseStudents(course);
		return new MergeSort(students, course);
	}
	public RadixSort doRadixSort(String course) {
		ArrayList<Student> students = havaCourseStudents(course);
		return new RadixSort(students, course);
	}
	public LongestIncreasingSubsequence doLIS(String course) {
		return new LongestIncreasingSubsequence(havaCourseStudents(course), course);
	}
	public SortCourse doSortCourse(String data) {
		return new SortCourse(data);
	}
	
	public boolean idInMap(String id) {
		return studentsMap.get(id) != null;
	}
	public Object getStudentGrade(String id, String grade) {
		if(!idInMap(id)) {
			return null;
		}
		if(!grade.equals("ALL")) {
			return studentsMap.get(id).get(grade);
		}else {
			String content = "";
			Map<String, Integer> map = studentsMap.get(id);
			for(Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator(); entries.hasNext();) {
				Map.Entry<String, Integer> entry = entries.next();
				content = content.concat(" " + entry.getKey() + " " + entry.getValue());
			}
			return content;
		}
	}
	public boolean mapIsEmpty(String id) {
		if(idInMap(id)) {
			return studentsMap.get(id).isEmpty();
		}
		return false;
	}
	
	public boolean addStudent(String data) {
		String id = data.split(" ")[0];
		if(idInMap(id)) {
			return false;
		}else{
			students.add(new Student(data));
			studentsMap.put(students.get(students.size()-1).getStudentId(), students.get(students.size()-1).getMap());
			return true;
		}
	}
	public boolean removeStudent(String id) {
		if(idInMap(id)) {
			for(int i=0; i<students.size(); i++) {
				if(students.get(i).getStudentId().equals(id)) {
					students.remove(i);
					break;
				}
			}
			studentsMap.remove(id);
			return true;
		}
		return false;
	}
	// Field of private Methods
	// Choose students with courses
	private ArrayList<Student> havaCourseStudents(String course){
		ArrayList<Student> students = new ArrayList<Student>();
		for(int i=0; i<this.students.size(); i++){
			if(this.students.get(i).getGrades(course) != 999) {
				students.add(this.students.get(i));
			}
		}
		return students;
	}

}// END class
