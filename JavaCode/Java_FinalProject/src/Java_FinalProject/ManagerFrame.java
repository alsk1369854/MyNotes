package Java_FinalProject;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;

public class ManagerFrame extends JFrame implements ActionListener {
	private Manager user = new Manager(new ArrayList<Object[]>());
	private JButton openShop = new JButton("開店業績初始化");
	private JButton showPerformance = new JButton("印出目前業績報告");
	private JButton menuAdd = new JButton("\"菜單\" 新增");
	private JButton menuRemove = new JButton("\"菜單\" 刪除");
	private JButton cashierWork = new JButton("收銀作業");
	private JButton signOut = new JButton("登出");
	
	public ManagerFrame() {
		setTitle("管理員介面");
		setLocation(300,100);
		setSize(950,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		// 開店初始化按鈕
		openShop.setLocation(150,50);
		openShop.setSize(300,100);
		openShop.setFont(new Font("新細明體", Font.BOLD,30));
		openShop.addActionListener(this);
		c.add(openShop);
		// 開店初始化按鈕
		showPerformance.setLocation(150,200);
		showPerformance.setSize(300,100);
		showPerformance.setFont(new Font("新細明體", Font.BOLD,30));
		showPerformance.addActionListener(this);
		c.add(showPerformance);
		// 菜單新增按鈕
		menuAdd.setLocation(150,350);
		menuAdd.setSize(300,100);
		menuAdd.setFont(new Font("新細明體", Font.BOLD,30));
		menuAdd.addActionListener(this);
		c.add(menuAdd);
		// 菜單刪除按鈕
		menuRemove.setLocation(150,500);
		menuRemove.setSize(300,100);
		menuRemove.setFont(new Font("新細明體", Font.BOLD,30));
		menuRemove.addActionListener(this);
		c.add(menuRemove);
		// 開店初始化按鈕
		cashierWork.setLocation(500,50);
		cashierWork.setSize(300,100);
		cashierWork.setFont(new Font("新細明體", Font.BOLD,30));
		cashierWork.addActionListener(this);
		c.add(cashierWork);
		//登出
		signOut.setLocation(850,10);
		signOut.setSize(80,30);
		signOut.setFont(new Font("新細明體",Font.BOLD,20));
		signOut.addActionListener(this);
		c.add(signOut);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openShop) {
			new CheckOpenShopFrame();
		}
		if(e.getSource() == showPerformance) {
			user.showDailyReport();
		}
		if(e.getSource() == menuAdd) {
			new MenuAddFrame();
		}
		if(e.getSource() == menuRemove) {
			new MenuRemoveFrame();
		}
		if(e.getSource() == cashierWork) {
			this.dispose();
			new CashierFrame();
		}
		//登出
		if(e.getSource() == signOut) {
			this.dispose();
			new MainSignInFrame();
		}
	}
}//end class