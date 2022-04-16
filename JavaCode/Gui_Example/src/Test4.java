//Example of MouseAdapters  

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Test4 extends MouseAdapter {

	public static void main(String[] args) {

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Clicking");
	}
}
