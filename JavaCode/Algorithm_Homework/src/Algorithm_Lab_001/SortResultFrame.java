package Algorithm_Lab_001;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class SortResultFrame extends JFrame {

	private JPanel contentPane;
	private String course;
	private String sortType;
	private DataLink dataLink;
	private JTable table;
	private JScrollPane tablePane;
	private ArrayList<Student> students;
	private int exchangeCount;
	private int comparisonCount;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortResultFrame frame = new SortResultFrame("DS", "Insertion Sort", new DataLink());
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
	public SortResultFrame(String course, String sortType, DataLink dataLink) {
		setTitle("Sort Results");
		this.sortType = sortType;
		this.course = course;
		this.dataLink = dataLink;
		switch(sortType){
		case "Insertion Sort":
			InsertionSort insertionSort = dataLink.doInsertionSort(course);
			System.out.println(insertionSort.getAnswers().get(0).getContent());
			this.students = insertionSort.getAnswers();
			this.exchangeCount = insertionSort.getExchangeCount();
			this.comparisonCount = insertionSort.getComparisonCount();
			break;
		case "Merge Sort":
			MergeSort mergeSort = dataLink.doMergeSort(course);
			this.students = mergeSort.getAnswers();
			this.exchangeCount = mergeSort.getExchangeCount();
			this.comparisonCount = mergeSort.getComparisonCount();
			break;
		case "Radix Sort":
			RadixSort radixSort = dataLink.doRadixSort(course);
			this.students = radixSort.getAnswers();
			break;
		}
		
		
		
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
		
		JLabel lblNewLabel = new JLabel("輸入科目: " + course);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		lblNewLabel.setBounds(97, 10, 246, 44);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("上一步");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectSortFrame(dataLink,course);
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
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("新細明體", Font.PLAIN, 12));
		table.setBackground(Color.WHITE);
		
		Object[][] object = new Object[students.size()][4];
		for(int i=0 ; i<students.size(); i++) {
			Object[] temp = {i+1, students.get(i).getStudentId(), course, students.get(i).getGrades(course)};
			object[i] = temp;
			/*
			object[i][0] = i;
			object[i][1] = students.get(i).getStudentId();
			object[i][2] = this.course;
			object[i][3] = students.get(i).getGrades(course);
			*/
		}
		table.setModel(new DefaultTableModel(
			object
			,
			new String[] {"編號", "學號", "科目", "成績"}
		){
			public boolean isCellEditable(int row, int column) {
				if (column < 4)
					return false;
				else
					return true;
			}
		});
		JTableHeader head = table.getTableHeader();
	    head.setFont(new Font("新細明體", Font.BOLD, 18));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    
	    table.setFont(new Font("新細明體", Font.PLAIN, 18));
	    table.setRowHeight(21);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setMinWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(20);
		tablePane = new JScrollPane(table);
		tablePane.setLocation(10, 64);
	    tablePane.setSize(414, 128);
		panel.add(tablePane);
		
		JLabel lblNewLabel_1 = new JLabel(
				"使用 " + sortType + " 排序，系統完成排序共比較 " + comparisonCount + " 次，交換元素 " + exchangeCount + " 次");
		lblNewLabel_1.setBounds(10, 202, 414, 49);
		panel.add(lblNewLabel_1);
		
		// close window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowHandler(dataLink));
				
		setVisible(true);
	}
}
