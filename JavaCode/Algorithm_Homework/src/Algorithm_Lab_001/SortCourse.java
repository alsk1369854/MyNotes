package Algorithm_Lab_001;

import java.util.Stack;

public class SortCourse {
	String data;
	int hasCases;
	String casesString;
	static int[][] pointArray;
	static Stack st;
	Point[] point;
	int time = 0;
	String result = "";
	
	public SortCourse(String data) {
		//System.out.println(0/2);
		this.data = data;
		hasCases = Integer.parseInt(data.substring(0, data.indexOf(",")));
		casesString = data.substring(data.indexOf(",") + 1);
		pointArray = new int[hasCases][hasCases];
		// create point array
		splitCases(casesString);
		
		point = new Point[hasCases];
		result += "[";
		for(int i=0; i<hasCases ;i++) {
			point[i] = new Point(i);
		}
		DFS();
		
		// point after sort
		insertionSort();
		boolean isFirst = true;
		for(int i=0; i<hasCases ; i++) {
			if(isFirst) {
				result += point[i].name;
				isFirst = false;
			}else {
				result += ", " + point[i].name;
			}
		}
		result += "]";
	}
	// create point array
	static void splitCases(String in) {
		st = new Stack();
		for(int i=0; i<in.length(); i++) {
			Character now = in.charAt(i);
			if(!now.equals(']')) {
				st.push(now);
				//System.out.println(now);
			}else {
				// a -> b
				int a = 0; 
				int b = 0;
				Character tag = '[';
				Character pop = null;
				boolean isfirst = true;
				while(!tag.equals(pop)) {
					pop = (Character) st.pop();
					if(tag.equals(pop)) {
						break;
					} else if(!pop.equals(',') && isfirst) {
						a = Character.getNumericValue(pop);
						//System.out.println(a);
						isfirst = false;
					}else if(!pop.equals(',') && !isfirst && !tag.equals(pop)){
						b = Character.getNumericValue(pop);
						//System.out.println(b);
					}
				}
				if(!st.empty()) {
					pointArray[a][b] = 1;
				}
			}
		}
	}
	
	void DFS() {
		for(Point p : point) {
			if(p.status == 0) {
				DFSVisit(p);
			}
		}
	}
	void DFSVisit(Point p) {
		time += 1;
		p.startTime = time;
		p.status = 1;
		for(int i=0; i<hasCases ; i++) {
			if(pointArray[p.name][i] == 1) {
				if(point[i].status == 0) {
					point[i].prev = p;
					DFSVisit(point[i]);
				}
			}
		}
		p.status = 2;
		time += 1;
		p.finishTime = time;
	}
	
	void insertionSort(){
		for(int i=1; i<hasCases; i++) {
			Point key = point[i];
			int j = i-1;
			while(j >= 0 && point[j].finishTime < key.finishTime ) {
				point[j+1] = point[j];
				j = j-1;
			}
			point[j+1] = key;
		}
	}
	
	public String getResult() {
		return result;
	}
	/*
	public static void main(String[] args) {
		SortCourse a = new SortCourse("4,[[1,0],[2,0],[3,1],[3,2]]");
		System.out.println(a.data);
		System.out.println(a.hasCases);
		System.out.println(a.casesString);
		for(int i=0; i<a.point.length; i++) {
			for(int j=0; j<a.point.length; j++) {
				System.out.print(a.pointArray[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println(a.result);
	}
	*/
}

class Point {
	int name;
	int status = 0;
	Point prev = null;
	int startTime;
	int finishTime;
	Point(int name){
		this.name = name;
	}
}
