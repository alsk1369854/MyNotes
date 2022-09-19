package Algorithm_Lab_001;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JComboBox;

public class SelectSortFrame extends JFrame {

	private JPanel contentPane;
	private DataLink dataLink;
	private String course;
	

	public SelectSortFrame(DataLink dataLink, String course) {
		setTitle("排序學生成績");
		this.dataLink = dataLink;
		this.course = course;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("請選擇排序方法：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		lblNewLabel.setBounds(97, 10, 246, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1.Insertion Sort");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(161, 58, 114, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("2.Merge Sort");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(161, 78, 114, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("請輸入功能代號:");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(30, 190, 187, 42);
		panel.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 24));
		comboBox.setBounds(213, 192, 47, 39);
		for(int i=1 ; i<=3 ; i++) {
			comboBox.addItem("" + i);
		}
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("確定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox.getSelectedItem();
				switch(item) {
				case "1":
					System.out.println("5-1");
					//new InputSortCaseFrame(dataLink, "Insertion Sort");
					new SortResultFrame(course, "Insertion Sort", dataLink);
					dispose();
					break;
				case "2":
					System.out.println("5-2");
					//new InputSortCaseFrame(dataLink, "Merge Sort");
					new SortResultFrame(course, "Merge Sort", dataLink);
					dispose();
					break;
				
				case "3":
					System.out.println("5-3");
					//new InputSortCaseFrame(dataLink, "Merge Sort");
					new SortResultFrame(course, "Radix Sort", dataLink);
					dispose();
					break;
				}
		}});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 24));
		btnNewButton.setBounds(298, 190, 103, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("上一步");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new FirstFrame(dataLink);
				new InputSortCaseFrame(dataLink);
				dispose();
			}
		});
		btnNewButton_1.setBounds(0, 0, 87, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("回首頁");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FirstFrame(dataLink);
				dispose();
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1_1.setBounds(347, 0, 87, 23);
		panel.add(btnNewButton_1_1);
		
		JList list = new JList();
		list.setBounds(153, 134, 1, 1);
		panel.add(list);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("3.Radix Sort");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(161, 99, 114, 22);
		panel.add(lblNewLabel_1_1_1);
		
		// close window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowHandler(dataLink));
		
		setVisible(true);
	}
}
