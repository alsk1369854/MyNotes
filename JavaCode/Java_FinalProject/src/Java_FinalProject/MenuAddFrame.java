package Java_FinalProject;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MenuAddFrame extends JFrame implements ActionListener {
	private Manager user = new Manager(new ArrayList<Object[]>());
	private JLabel productNameLabel = new JLabel("商品名:");
	private JLabel productCostLabel = new JLabel("單   價:");
	private JLabel productTypeLabel = new JLabel("商品類:");
	private JTextField productName = new JTextField();
	private JTextField productCost = new JTextField();
	private JButton ok = new JButton("確定");
	private JButton no = new JButton("取消");
	private JComboBox<String> jcbMenu = new JComboBox<String>();
	
	public MenuAddFrame() {
		setTitle("新增菜單");
		setLocation(620,150);
		setSize(400,500);
		Container c = getContentPane();
		c.setLayout(null);
		
		//JLabel===================================================
		// 商品類:
		productTypeLabel.setLocation(30,50);
		productTypeLabel.setSize(120,50);
		productTypeLabel.setFont(new Font("新細明體", Font.BOLD,35));
		c.add(productTypeLabel);	
		// 商品名:
		productNameLabel.setLocation(30,150);
		productNameLabel.setSize(120,50);
		productNameLabel.setFont(new Font("新細明體", Font.BOLD,35));
		c.add(productNameLabel);	
		// 單   價:
		productCostLabel.setLocation(30,250);
		productCostLabel.setSize(120,50);
		productCostLabel.setFont(new Font("新細明體", Font.BOLD,35));
		c.add(productCostLabel);
		
	    //JComboBox=========================================================
		//選單
		jcbMenu.addItem("套餐");
		jcbMenu.addItem("單點");
		jcbMenu.setLocation(190, 50);
		jcbMenu.setSize(130, 50);
		jcbMenu.setFont(new Font("新細明體",Font.BOLD,30));
        c.add(jcbMenu);
		
		//JTextField======================================================
		// 商品類:
        productName.setLocation(180,150);
        productName.setSize(180,50);
        productName.setFont(new Font("新細明體", Font.BOLD,35));
		c.add(productName);
		// 商品類:
		productCost.setLocation(180,250);
		productCost.setSize(150,50);
		productCost.setFont(new Font("新細明體", Font.BOLD,35));
		c.add(productCost);
		
		//JButton=======================================================
		// 確認按鈕
		ok.setLocation(50,350);
		ok.setSize(120,55);
		ok.setFont(new Font("新細明體", Font.BOLD,35));
		ok.addActionListener(this);
		c.add(ok);			
		// 取消按鈕
		no.setLocation(210,350);
		no.setSize(120,55);
		no.setFont(new Font("新細明體", Font.BOLD,30));
		no.addActionListener(this);
		c.add(no);	
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			String type = (String)jcbMenu.getSelectedItem();
			String name = productName.getText();
			String cost = productCost.getText();
			if(!name.equals("") && !cost.equals("")) {
				user.addMenu(type, name, cost);
				JOptionPane.showMessageDialog(null, "新增完成");
			}else {
				JOptionPane.showMessageDialog(null, "請輸入資料");
			}
		}
		if(e.getSource() == no) {
			this.dispose();
		}
		
	}
	
	

}
