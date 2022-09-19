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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class RemoveStudentFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtDs;
	private DataLink dataLink;
	private String sortType;
	private JLabel lblNull;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveStudentFrame frame = new RemoveStudentFrame(new DataLink());
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
	
	public RemoveStudentFrame(DataLink dataLink) {
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
		
		JLabel lblNewLabel = new JLabel("請輸入學生學號:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		lblNewLabel.setBounds(48, 33, 352, 41);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("確定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = txtDs.getText();
				if(dataLink.removeStudent(data)) {
					lblNull.setText("刪除成功!");
				}else {
					lblNull.setText("查無此學號!");
				}
				
				//new SortResultFrame(course, sortType, dataLink);
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
		txtDs.setText("97501");
		txtDs.setBounds(139, 92, 182, 35);
		panel.add(txtDs);
		txtDs.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("重置");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDs.setText("97531 DS 80 DM 80 LA 80");
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 24));
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setBounds(233, 177, 103, 41);
		panel.add(btnNewButton_2);
		
		lblNull = new JLabel("");
		lblNull.setHorizontalAlignment(SwingConstants.CENTER);
		lblNull.setFont(new Font("新細明體", Font.BOLD, 24));
		lblNull.setBounds(48, 137, 347, 30);
		panel.add(lblNull);
		
		// close window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowHandler(dataLink));
		
		setVisible(true);
	}
}