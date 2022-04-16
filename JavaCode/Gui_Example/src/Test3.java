//Example implementing anonymous class

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Test3 extends JFrame {

	public static void main(String[] args) {
		Test3 t = new Test3();
		t.setTitle("Test 3: implementing anonymous class");
		t.setSize(400, 400);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	Test3(){
		JButton  btn = new JButton("Say Hello");
		getContentPane().add(btn);
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Test 3: hello");
				
			}});
	 }
}
