package demo3;
public class Client {

	 static boolean readyToPrint = false;
		public static void main(String[] args) {
			Target target = new Target();
			new Adder(target).start();
			new Printer(target).start();
		}
}
