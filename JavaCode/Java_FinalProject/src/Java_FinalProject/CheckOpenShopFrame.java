package Java_FinalProject;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CheckOpenShopFrame extends JFrame implements ActionListener {
	private Manager user = new Manager(new ArrayList<Object[]>());
	private JButton ok = new JButton("確定");
	private JButton no = new JButton("取消");
	
	public CheckOpenShopFrame() {
		setTitle("要初始化嗎?");
		setLocation(640,400);
		setSize(300,200);
		Container c = getContentPane();
		c.setLayout(null);
		
		// 確認按鈕
		ok.setLocation(10,30);
		ok.setSize(120,100);
		ok.setFont(new Font("新細明體", Font.BOLD,40));
		ok.addActionListener(this);
		c.add(ok);	
		// 取消按鈕
		no.setLocation(150,30);
		no.setSize(120,100);
		no.setFont(new Font("新細明體", Font.BOLD,40));
		no.addActionListener(this);
		c.add(no);	
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			user.openShop();
			this.dispose();
		}
		if(e.getSource() == no) {
			this.dispose();
		}
	}
}//end class
