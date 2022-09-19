package Algorithm_Lab_001;

public class Test {
public static void main(String[] args) {
	Integer n1 = 1234;
	Integer n2 = 567;
	int x[] = new int[10];
	x[0] = 1;
	System.out.println(x[1]);
	for(int i=0; i<2 ; i++) {
		System.out.println("n1 : " + n1.highestOneBit(i));
		System.out.println("n2 : " + n2.lowestOneBit(i));
	}
}
}
