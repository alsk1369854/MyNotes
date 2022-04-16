package Java_FinalProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class CashierFrame extends JFrame implements ActionListener {
	private ArrayList<Object[]> orderList = new ArrayList<Object[]>();
	
	private Human user = new Human(orderList);
	private DefaultTableModel orderTableM;
	private DefaultTableModel menuTableM;
	private JTable orderTable;
	private JTable menuTable;
	private JScrollPane orderPane;
	private JScrollPane menuPane;
	private JComboBox<String> jcbMenu;
	private JButton delete = new JButton("全刪除");
	private JButton subtotal = new JButton("小計");
	private JButton checkouts = new JButton("結帳");
	private JButton signOut = new JButton("登出");
	private JLabel showTotal = new JLabel("總計:");
	private JLabel totalCost = new JLabel("00000");
	private JLabel labelProfitNow = new JLabel("目前業績:");
	private JLabel profitNow = new JLabel("0000000");
	
	
	public CashierFrame(){
		setTitle("高師點餐系統");
		setLocation(300,100);
		setSize(950,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		//JTable=========================================================
		//已點內容
    	String[] columns = { "編號", "品名", "單價", "數量", "金額", "刪除"};
		orderTableM = new DefaultTableModel(null, columns) {
			//表格不允許被編輯
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 5)
					return false;
				else
					return true;
			}
	    };
	    orderTable = new JTable(orderTableM) ;
	    orderTable.addMouseListener(new ListBtnMouseListener(orderTable));
	    //已點內容表格標題大小
	    JTableHeader head = orderTable.getTableHeader();
	    head.setFont(new Font("新細明體", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //已點內容表格大小
	    orderTable.setFont(new Font("新細明體", Font.PLAIN, 25));
	    orderTable.setRowHeight(35);
	    orderTable.getColumnModel().getColumn(5).setCellRenderer(new ListBtnRender("刪除"));
		orderTable.getColumnModel().getColumn(5).setCellEditor(new ListBtnEditor("刪除"));
		//已點內容欄寬
		TableColumn column = orderTable.getColumnModel().getColumn(0);
	    column.setPreferredWidth(50);
	    column=orderTable.getColumnModel().getColumn(1);
	    column.setPreferredWidth(170);
	    column=orderTable.getColumnModel().getColumn(2);
	    column.setPreferredWidth(50);
	    column=orderTable.getColumnModel().getColumn(3);
	    column.setPreferredWidth(50);
	    column=orderTable.getColumnModel().getColumn(4);
	    column.setPreferredWidth(65);
	    column=orderTable.getColumnModel().getColumn(5);
	    column.setPreferredWidth(60);
		//以點內容捲動
	    orderPane = new JScrollPane(orderTable);
	    orderPane.setLocation(10, 10);
	    orderPane.setSize(520, 640);
		c.add(orderPane);
		
		//菜單欄
		String[] menuColumns = { "商品", "單價", "加入"};
		menuTableM = new DefaultTableModel(null, menuColumns) {
			//表格不允許被編輯
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 3)
					return false;
				else
					return true;
			}
	    };
	    menuTable = new JTable(menuTableM);
	    menuTable.addMouseListener(new ListBtnMouseListener(menuTable));
	    //菜單欄表格標題大小
	    head = menuTable.getTableHeader();
	    head.setFont(new Font("新細明體", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //菜單欄表格大小
	    menuTable.setFont(new Font("新細明體", Font.PLAIN, 25));
	    menuTable.setRowHeight(35);
		//菜單欄寬
		column = menuTable.getColumnModel().getColumn(0);
	    column.setPreferredWidth(220);
	    column = menuTable.getColumnModel().getColumn(1);
	    column.setPreferredWidth(50);
	    column = menuTable.getColumnModel().getColumn(2);
	    column.setPreferredWidth(50);
	    menuTable.getColumnModel().getColumn(2).setCellRenderer(new ListBtnRender("加入"));
		menuTable.getColumnModel().getColumn(2).setCellEditor(new ListBtnEditor("加入"));
	    //菜單欄捲動
	    menuPane = new JScrollPane(menuTable);
	    menuPane.setLocation(530, 155);
	    menuPane.setSize(400, 440);
		c.add(menuPane);
		
	    //JComboBox=========================================================
		//選單
		jcbMenu = new JComboBox<String>();
		jcbMenu.addItem("套餐");
		jcbMenu.addItem("單點");
		jcbMenu.setLocation(540, 95);
		jcbMenu.setSize(130, 50);
		jcbMenu.setFont(new Font("新細明體",Font.BOLD,30));
		jcbMenu.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
    			user.cleanTable(menuTableM);
    			user.buildMenuTable((String)jcbMenu.getSelectedItem(),menuTableM,orderTableM);
    		}
    	});
        c.add(jcbMenu);
        
        //JLabel=========================================================
		//總計標籤
		showTotal.setLocation(550,25);
		showTotal.setSize(150,70);
		showTotal.setFont(new Font("新細明體", Font.BOLD,50));
		c.add(showTotal);
		//總費用
		totalCost.setLocation(680,27);
		totalCost.setSize(200,70);
		totalCost.setFont(new Font("新細明體", Font.BOLD,70));
		totalCost.setForeground(Color.RED);
		c.add(totalCost);
		//目前業績標籤
		labelProfitNow.setLocation(730,110);
		labelProfitNow.setSize(100,50);
		labelProfitNow.setFont(new Font("新細明體",Font.BOLD,20));
		c.add(labelProfitNow);
		//目前業績
		profitNow.setLocation(820,110);
		profitNow.setSize(200,50);
		profitNow.setFont(new Font("新細明體",Font.BOLD,25));
		c.add(profitNow);
		
		
		//JButton=========================================================
		//刪除按鈕
		delete.setLocation(530,595);
		delete.setSize(133,55);
		delete.setFont(new Font("新細明體",Font.BOLD,30));
		delete.addActionListener(this);
		c.add(delete);
		//小計
		subtotal.setLocation(663,595);
		subtotal.setSize(133,55);
		subtotal.setFont(new Font("新細明體",Font.BOLD,35));
		subtotal.addActionListener(this);
		c.add(subtotal);
		//結帳
		checkouts.setLocation(796,595);
		checkouts.setSize(133,55);
		checkouts.setFont(new Font("新細明體",Font.BOLD,35));
		checkouts.addActionListener(this);
		c.add(checkouts);
		//登出
		signOut.setLocation(850,10);
		signOut.setSize(80,30);
		signOut.setFont(new Font("新細明體",Font.BOLD,20));
		signOut.addActionListener(this);
		c.add(signOut);
		
		user.buildMenuTable((String)jcbMenu.getSelectedItem(),menuTableM,orderTableM);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//登出
		if(e.getSource() == signOut) {
			this.dispose();
			new MainSignInFrame();
		}
		//小計
		if(e.getSource() == subtotal) {
			totalCost.setText(user.computerSubtotal());
		}
		//結帳
		if(e.getSource() == checkouts) {
			totalCost.setText(user.computerSubtotal());
			user.checkouts(Integer.parseInt(totalCost.getText()));
			profitNow.setText("" + user.getProfitNow());
			
		}
		//刪除
		if(e.getSource() == delete) {
			user.cleanOrderList();
			user.cleanTable(orderTableM);
		}
	}
}//end class
