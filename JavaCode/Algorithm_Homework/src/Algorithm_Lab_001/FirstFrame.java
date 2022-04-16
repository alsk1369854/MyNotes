package Algorithm_Lab_001;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

public class FirstFrame {

	private JPanel contentPane;
	private DataLink dataLink;
	private JFrame f;
	private JPanel panel;
	
	public FirstFrame(DataLink dataLink) {
		//f.setTitle("Algorithm Lab_1 : 410877033 梁家銘");
		f = new JFrame("Algorithm Lab_1 : 410877033 梁家銘");
		this.dataLink = dataLink;
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("功能代號如下:");
		lblNewLabel.setBounds(126, 0, 246, 44);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1.查詢學生單科成績");
		lblNewLabel_1.setBounds(68, 40, 114, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("2.查詢學生所有成績");
		lblNewLabel_1_1.setBounds(68, 76, 114, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("3.新增學生成績");
		lblNewLabel_1_2.setBounds(68, 108, 114, 22);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("4.刪除學生成績");
		lblNewLabel_1_3.setBounds(68, 140, 114, 22);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("5.排序學生成績");
		lblNewLabel_1_4.setBounds(68, 172, 114, 22);
		panel.add(lblNewLabel_1_4);
		
		JLabel longestIncreasingSubsequence = new JLabel("6.最長遞增成績子序列");
		longestIncreasingSubsequence.setBounds(214, 40, 130, 22);
		panel.add(longestIncreasingSubsequence);
		
		JLabel lblNewLabel_1_7 = new JLabel("7.排課");
		lblNewLabel_1_7.setBounds(214, 76, 114, 22);
		panel.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_5 = new JLabel("8.離開系統");
		lblNewLabel_1_5.setBounds(214, 108, 114, 22);
		panel.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_2 = new JLabel("請輸入功能代號:");
		lblNewLabel_2.setBounds(17, 219, 187, 42);
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 24));
		panel.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(214, 221, 47, 39);
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 24));
		for(int i=1 ; i<=8 ; i++) {
			comboBox.addItem("" + i);
		}
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("確定");
		btnNewButton.setBounds(292, 220, 103, 41);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String) comboBox.getSelectedItem();
				switch(item){
					case "1":
						System.out.println("1");
						new InputShowGradeFrame(dataLink, "only");
						f.dispose();
						break;
					case "2":
						System.out.println("2");
						new InputShowGradeFrame(dataLink, "ALL");
						f.dispose();
						break;
					case "3":
						System.out.println("3");
						AddStudentFrame addStudentGui = new AddStudentFrame(dataLink, "AddStudent");
						addStudentGui.lblNewLabel.setText("請輸入學生學號及科目成績:");
						addStudentGui.txtDs.setText("97531 DS 80 DM 80 LA 80");
						f.dispose();
						break;
					case "4":
						System.out.println("4");
						new RemoveStudentFrame(dataLink);
						f.dispose();
						break;
					case "5":
						System.out.println("5");
						//new SelectSortFrame(dataLink);
						new InputSortCaseFrame(dataLink);
						f.dispose();
						break;
					case "6":
						System.out.println("6");
						InputSortCaseFrame inputCaseGui = new InputSortCaseFrame(dataLink);
						inputCaseGui.sortSelectTag = false;
						f.dispose();
						break;
					case "7":
						System.out.println("7");
						AddStudentFrame sortCourseInputGui = new AddStudentFrame(dataLink, "SortCourse");
						sortCourseInputGui.lblNewLabel.setText("請輸入課程總數和先決條件列表:");
						sortCourseInputGui.txtDs.setText("4,[[1,0],[2,0],[3,1],[3,2]]");
						f.dispose();
						break;
					case "8":
						System.out.println("8");
						dataLink.saveStudents();
						f.dispose();
						break;
					}
		}});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 24));
		panel.add(btnNewButton);
		
		// close window
		f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(new WindowHandler(dataLink));
		
		f.setVisible(true);
	}
}


