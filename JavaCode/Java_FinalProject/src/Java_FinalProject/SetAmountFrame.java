package Java_FinalProject;


import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class SetAmountFrame extends JFrame implements ActionListener {
	private Human user;
	private String name;
	private int cost;
	private DefaultTableModel orderTableM;
	
	private JLabel amountLabel = new JLabel("數量:");
	private JButton check = new JButton("確認");
	private JTextField amount = new JTextField("1");
	
	
	
	public SetAmountFrame(String name, int cost, DefaultTableModel orderTableM,Human user) {
		this.name = name;
		this.cost = cost;
		this.orderTableM = orderTableM;
		this.user = user;
		
		setTitle("加入數量");
		setLocation(640,400);
		setSize(300,200);
		setVisible(true);
		
		
		Container c = getContentPane();
		c.setLayout(null);
		
		// 數量:
		amountLabel.setLocation(38,28);
		amountLabel.setSize(100,50);
		amountLabel.setFont(new Font("新細明體", Font.BOLD,40));
		c.add(amountLabel);
		// 數量預設 1
		amount.setLocation(160,28);
		amount.setSize(80,50);
		amount.setFont(new Font("新細明體", Font.BOLD,40));
		c.add(amount);
		// 確認
		check.setLocation(85,90);
		check.setSize(120,60);
		check.setFont(new Font("新細明體", Font.BOLD,35));
		check.addActionListener(this);
		c.add(check);		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == check) {
			int orderAmount = Integer.parseInt(amount.getText());
			user.addOrderList(name,cost,orderAmount,orderTableM);
			this.dispose();
		}
		
	}
}//end class
