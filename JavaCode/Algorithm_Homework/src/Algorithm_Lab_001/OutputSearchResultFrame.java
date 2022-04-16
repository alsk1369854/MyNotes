package Algorithm_Lab_001;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

public class OutputSearchResultFrame extends JFrame {

	private JPanel contentPane;
	private DataLink dataLink;
	private String id;
	private String grade;
	private String course;
	private String content = "";
	private int type = 0;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutputSearchResult frame = new OutputSearchResult(new DataLink(),"97501","DS");
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

	public OutputSearchResultFrame(DataLink datalink, String course, int type) {
		this.dataLink = datalink;
		this.type = type;
		if(type == 6) {
			this.course = course;
			LongestIncreasingSubsequence lis = dataLink.doLIS(course);
			content += course + " 成績序列為 " + lis.getAllCases() + "\n最長遞增子序列為 " + lis.getResult() + " 長度為 " + lis.getMaxCount();
		}else if(type == 7) {
			content += dataLink.doSortCourse(course).getResult();
		}
		showGui();
	}
	public OutputSearchResultFrame(DataLink datalink, String id, String grade) {
		setTitle("Output Result");
		this.dataLink = datalink;
		this.id = id;
		this.grade = grade;
		if(dataLink.getStudentGrade(id, grade) != null && dataLink.idInMap(id) && !dataLink.mapIsEmpty(id)) {
			if(!grade.equals("ALL")) {
				content = id + " 學生的 " + grade + " 分數為 " + dataLink.getStudentGrade(id, grade);
				System.out.println(content);
			}else {
				content = id + " 學生的 分數為" + dataLink.getStudentGrade(id, grade);
				System.out.println(content);
			}
		}else if(dataLink.mapIsEmpty(id)){
			content = id + " 此學生並沒修課";
		}else if(!dataLink.idInMap(id)){
			content = "無 " + id + " 此資料";
		}else {
			content = "無 " + id + " 的 " + grade + " 科目 成績";
		}
		showGui();
	}
	
	public void showGui() {	
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
		
		JButton btnNewButton_1 = new JButton("上一步");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new SelectSortFrame(dataLink);
				if(type == 0) {
					new InputShowGradeFrame(dataLink, grade);
				}else if(type == 6) {
					InputSortCaseFrame inputCaseGui = new InputSortCaseFrame(dataLink);
					inputCaseGui.sortSelectTag = false;
				}else if(type == 7) {
					AddStudentFrame sortCourseInputGui = new AddStudentFrame(dataLink, "SortCourse");
					sortCourseInputGui.lblNewLabel.setText("請輸入課程總數和先決條件列表:");
					sortCourseInputGui.txtDs.setText("4,[[1,0],[2,0],[3,1],[3,2]]");
				}
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
		
		JTextPane textPane = new JTextPane();
		textPane.setText(content);
		textPane.setBounds(25, 45, 381, 192);
		panel.add(textPane);
		
		// close window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowHandler(dataLink));
		
		setVisible(true);
	}
}
