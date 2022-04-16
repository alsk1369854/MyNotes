package Algorithm_Lab_001;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class InputSortCaseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDs;
	private DataLink dataLink;
	private String sortType;
	boolean sortSelectTag = true;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertionSortInputFrame frame = new InsertionSortInputFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public InputSortCaseFrame(DataLink dataLink) {
		setTitle("Input Case");
		this.dataLink = dataLink;
		//this.sortType = sortType;
		
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
		
		JLabel lblNewLabel = new JLabel("請輸入科目名稱:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		lblNewLabel.setBounds(97, 10, 246, 44);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("確定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sortSelectTag) {
					String course = txtDs.getText();
					//new SortResultFrame(course, sortType, dataLink);
					new SelectSortFrame(dataLink,course);
				}else {
					new OutputSearchResultFrame(dataLink,txtDs.getText(), 6);
				}
				dispose();
		}});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 24));
		btnNewButton.setBounds(97, 177, 103, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("上一步");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new SelectSortFrame(dataLink);
				new FirstFrame(dataLink);
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
		
		txtDs = new JTextField();
		txtDs.setHorizontalAlignment(SwingConstants.CENTER);
		txtDs.setFont(new Font("新細明體", Font.PLAIN, 30));
		txtDs.setText("DS");
		txtDs.setBounds(156, 78, 126, 63);
		panel.add(txtDs);
		txtDs.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("重置");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDs.setText("");
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 24));
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setBounds(233, 177, 103, 41);
		panel.add(btnNewButton_2);
		
		// close window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowHandler(dataLink));
		
		setVisible(true);
	}
}
