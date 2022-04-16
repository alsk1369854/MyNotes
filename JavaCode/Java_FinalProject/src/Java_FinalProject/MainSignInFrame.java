package Java_FinalProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainSignInFrame implements ActionListener{
	private AccountList account = new AccountList();
	private JFrame signInFrame = new JFrame();
	private JLabel lbUser = new JLabel("使用者登入");
	private JLabel user = new JLabel("管理員: 123 / 員工: 410877033");
	private JButton btnSignIn = new JButton("登入");
	private JTextField jid = new JTextField(20);
	
	
	public static void main(String[] args) {
		new MainSignInFrame();
	}
	public MainSignInFrame() {
		signInFrame.setTitle("高師點餐系統");
		signInFrame.setLocation(300,100);
		signInFrame.setSize(950,700);
		signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = signInFrame.getContentPane();
		c.setLayout(null);
		// 加入標題
		lbUser.setLocation(280,250);
		lbUser.setSize(150,47);
		lbUser.setFont(new Font("新細明體", Font.BOLD,24));
		c.add(lbUser);
		// 用戶輸入欄
		jid.setLocation(420,252);
		jid.setSize(200,40);
		jid.setFont(new Font("新細明體", Font.BOLD,30));
		//jid.setEchoChar('*');
		c.add(jid);
		// 加入登入
		btnSignIn.setLocation(420,310);
		btnSignIn.setSize(90,40);
		btnSignIn.setFont(new Font("新細明體", Font.BOLD,24));
		btnSignIn.addActionListener(this);
		c.add(btnSignIn);
		
		// 帳號提示:
		user.setLocation(300,400);
		user.setSize(800,80);
		user.setFont(new Font("新細明體", Font.BOLD,24));
		c.add(user);
		
		signInFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSignIn) {
			if(account.checkUserExist(jid.getText())) {
				switch(account.getUser().getAuthority()) {
				case "管理員" :
					signInFrame.dispose();
					new ManagerFrame();
					break;
				case "員工" :
					signInFrame.dispose();
					new CashierFrame();
					break;
				}
			}else {
				JOptionPane.showMessageDialog(null, "使用者不存在");
			}
		}
	}
}//end class
