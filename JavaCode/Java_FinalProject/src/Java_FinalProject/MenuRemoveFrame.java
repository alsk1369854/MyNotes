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


public class MenuRemoveFrame extends JFrame implements ActionListener {
	private Manager user = new Manager(new ArrayList<Object[]>());
	private JLabel productNameLabel = new JLabel("商品名:");
	private JLabel productTypeLabel = new JLabel("商品類:");
	private JButton ok = new JButton("確定");
	private JButton no = new JButton("取消");
	private JComboBox<String> jcbMenu = new JComboBox<String>();
	private JComboBox<String> jcbProduct = new JComboBox<String>();
	
	public MenuRemoveFrame() {
		setTitle("刪除菜單");
		setLocation(620,200);
		setSize(400,400);
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
		
	    //JComboBox=========================================================
		//類別選單
		jcbMenu.addItem("套餐");
		jcbMenu.addItem("單點");
		jcbMenu.setLocation(190, 50);
		jcbMenu.setSize(130, 50);
		jcbMenu.setFont(new Font("新細明體",Font.BOLD,30));
		jcbMenu.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
    			jcbProduct.removeAllItems();
    	        String[] items = user.getMenuItems((String)jcbMenu.getSelectedItem());
    	        for(String i : items) {
    	        	jcbProduct.addItem(i);
    	        }
    			
    		}
    	});
        c.add(jcbMenu);
        //商品選單
        String[] items = user.getMenuItems((String)jcbMenu.getSelectedItem());
        for(String i : items) {
        	jcbProduct.addItem(i);
        }
        jcbProduct.setLocation(190, 150);
        jcbProduct.setSize(180, 50);
        jcbProduct.setFont(new Font("新細明體",Font.BOLD,30));
        c.add(jcbProduct);
        
		//JButton=======================================================
		// 確認按鈕
		ok.setLocation(50,250);
		ok.setSize(120,55);
		ok.setFont(new Font("新細明體", Font.BOLD,35));
		ok.addActionListener(this);
		c.add(ok);			
		// 取消按鈕
		no.setLocation(210,250);
		no.setSize(120,55);
		no.setFont(new Font("新細明體", Font.BOLD,30));
		no.addActionListener(this);
		c.add(no);	
		
		setVisible(true);
	}
	public MenuRemoveFrame(String startMenuItem) {
		this();
		jcbMenu.setSelectedItem(startMenuItem);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			user.removeMenu((String)jcbMenu.getSelectedItem(), (String)jcbProduct.getSelectedItem());
			JOptionPane.showMessageDialog(null, "刪除完成");
			this.dispose();
			new MenuRemoveFrame((String)jcbMenu.getSelectedItem());
		}
		if(e.getSource() == no) {
			this.dispose();
		}		
	}
}//end class