
public class Main {
	
	final static int MAX_SIZE = 5;
	static boolean readyToPrint = false;
	
	public static void main(String[] args) {
		Target target = new Target();
		new Adder(target).start();
		new Printer(target).start();
	}
}
