import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ClientGui {
	
	private JFrame frame;
	private JTextField tfIP;
	private JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	
	ClientGui gui;
	SendFlag sf;
	Socket s;
	Client client;
	
	public ClientGui(SendFlag sendFlag) {
		gui = this;
		sf = sendFlag;
	}
	
	void setVisible(boolean visiable) {
		frame.setVisible(visiable);
	}
	
	void initialize() {
		frame = new JFrame();
		frame.setTitle("P2P 聊天室 Client");
		frame.setBounds(100, 100, 506, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setBounds(10, 21, 65, 15);
		frame.getContentPane().add(lblNewLabel);

		tfIP = new JTextField();
		tfIP.setText("127.0.0.1");
		tfIP.setBounds(74, 18, 105, 21);
		frame.getContentPane().add(tfIP);
		tfIP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setBounds(211, 21, 72, 15);
		frame.getContentPane().add(lblNewLabel_1);

		tfPort = new JTextField();
		tfPort.setText("3000");
		tfPort.setBounds(282, 18, 51, 21);
		frame.getContentPane().add(tfPort);
		tfPort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					s = new Socket(tfIP.getText(), Integer.parseInt(tfPort.getText()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					taBoard.append("連線失敗\n");
					e.printStackTrace();
				}
				client = new Client(gui);
				client.start();
			}
		});
		btnConnect.setBounds(369, 17, 87, 23);
		frame.getContentPane().add(btnConnect);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//s.close();
				} catch (NullPointerException e) {
					frame.setVisible(false);
					//System.exit(0);
				}
				frame.setVisible(false);
				//System.exit(0);
			}
		});
		btnClose.setBounds(369, 47, 87, 23);
		frame.getContentPane().add(btnClose);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.clientSendFlag = 1;
			}
		});
		btnSend.setBounds(381, 341, 87, 23);
		frame.getContentPane().add(btnSend);
		
		taBoard = new JTextArea();
		taBoard.setBounds(22, 71, 446, 247);
		frame.getContentPane().add(taBoard);
		
		tfMessage = new JTextField();
		tfMessage.setBounds(22, 342, 349, 21);
		frame.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);		
		
		// KeyListener
		tfMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					sf.clientSendFlag = 1;
				}
			}
		});
		
	}
	
	/*
	public static void main(String[] args) {
		ClientGui cg = new ClientGui(new SendFlag());
		cg.initialize();
		cg.setVisible(true);
	}
	*/
}
