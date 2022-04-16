//Example of implementing Listener interface

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test1 extends JFrame implements ActionListener {

	public static void main(String[] args) {
		Test1 t = new Test1();
		t.setTitle("Test 1: Implementing Listener interface");
		t.setSize(400, 400);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	Test1(){
		JButton  btn = new JButton("Say Hello");
		getContentPane().add(btn);
	    btn.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Test 1: hello");
		
	}

	

}
