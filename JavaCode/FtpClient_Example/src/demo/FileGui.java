package demo;

import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class FileGui {
	
	Controller ct;
	
	private JFrame frmMyClientFtp;
	private JTextField tfCd;
	private JTextField tfLcd;
	private JLabel lbDir;
	private JLabel lbFile;
	private JPanel panel;
	private String lcd;
	private JList<String> list = new <String> JList();
	DefaultListModel DLM = new DefaultListModel();
	
	String select;
	boolean isDir;
	//FtpDirEntry [] files;
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileGui window = new FileGui();
					window.frmMyClientFtp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	public FileGui() {
		initialize();
	}
	
	public void setVisible(boolean visiable) {
		ct.getListName();
		list.setModel(DLM);
		frmMyClientFtp.setVisible(visiable);
	}
	
	void loadData(){
		lbDir.setText(ct.getWorkingDir());
		ct.getListName();
	}

	private void initialize() {
		frmMyClientFtp = new JFrame();
		frmMyClientFtp.setTitle("My Client FTP (Simple Version)");
		frmMyClientFtp.setBounds(100, 100, 552, 573);
		frmMyClientFtp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.changeToParentDir();
				loadData();
			}
		});
		btnUp.setBounds(14, 13, 49, 27);
		frmMyClientFtp.getContentPane().setLayout(null);
		frmMyClientFtp.getContentPane().add(btnUp);
		JLabel lbDirCap = new JLabel("Working Dir: ");
		lbDirCap.setBounds(14, 53, 81, 27);
		lbDirCap.setBackground(Color.LIGHT_GRAY);
		frmMyClientFtp.getContentPane().add(lbDirCap);
		JLabel lblCd = new JLabel("CD");
		lblCd.setBounds(24, 97, 31, 19);
		frmMyClientFtp.getContentPane().add(lblCd);
		tfCd = new JTextField();
		tfCd.setBounds(72, 97, 273, 25);
		frmMyClientFtp.getContentPane().add(tfCd);
		tfCd.setColumns(10);
		JLabel lbLcd = new JLabel("LCD");
		lbLcd.setBounds(14, 135, 36, 19);
		frmMyClientFtp.getContentPane().add(lbLcd);
		tfLcd = new JTextField();
		tfLcd.setBounds(72, 135, 273, 25);
		frmMyClientFtp.getContentPane().add(tfLcd);
		tfLcd.setColumns(10);
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isDir) {
					ct.getFile(select);
				}
			}
		});
		btnGet.setBounds(402, 18, 56, 27);
		frmMyClientFtp.getContentPane().add(btnGet);
		
		JButton btnPut = new JButton("Put");
		btnPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.putFile();
			}
		});
		btnPut.setBounds(472, 18, 56, 27);
		frmMyClientFtp.getContentPane().add(btnPut);
		panel = new JPanel();
		panel.setBounds(37, 193, 407, 303);
		panel.setLayout(null);
		frmMyClientFtp.getContentPane().add(panel);
		
		lbDir = new JLabel("/");
		lbDir.setBounds(96, 57, 151, 19);
		frmMyClientFtp.getContentPane().add(lbDir);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				select = list.getSelectedValue();
				isDir = select.contains("(dir)");
				if(isDir) {
					ct.changeDir(select);
					loadData();
				}
				if(!isDir) lbFile.setText(select); 
			}
		});
		list.setBounds(0, 0, 300, 200);
		panel.add(list);
		
		JLabel lbFileCap = new JLabel("File: ");
		lbFileCap.setBounds(367, 57, 31, 19);
		frmMyClientFtp.getContentPane().add(lbFileCap);
		
		lbFile = new JLabel("");
		lbFile.setBounds(396, 57, 107, 19);
		frmMyClientFtp.getContentPane().add(lbFile);
		
		JButton btnCd = new JButton("execute CD");
		btnCd.setBounds(377, 93, 110, 27);
		frmMyClientFtp.getContentPane().add(btnCd);
		
		JButton btnLcd = new JButton("excute LCD");
		btnLcd.setBounds(377, 131, 110, 27);
		frmMyClientFtp.getContentPane().add(btnLcd);
	}
	
}
