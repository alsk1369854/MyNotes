//Example of using inner class

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Test2 extends JFrame {

	public static void main(String[] args) {
		Test2 t = new Test2();
		t.setTitle("Test 2: Implementing Inner class");
		t.setSize(400, 400);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	Test2(){
		JButton  btn = new JButton("Say Hello");
		getContentPane().add(btn);
	    btn.addActionListener(new Lis());
	}

   class Lis implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Test 2: hello");
	}
   }
	
}
