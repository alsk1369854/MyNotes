
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ServerGui {
	
	private JFrame frame;
	JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	private JButton btnStartListening;
	private JButton btnClose;
	private JButton btnSend;
	
	ServerGui gui;
	SendFlag sf;
	ServerSocket svs;
	Server server;
	
	public ServerGui(SendFlag sendFlag) {
		gui = this;
		sf = sendFlag;
	}
	
	void setVisible(boolean visiable) {
		frame.setVisible(visiable);
	}
	
	void initialize() {
		frame = new JFrame();
		frame.setTitle("P2P 聊天室 Server");
		frame.setBounds(100, 100, 506, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server Port");
		lblNewLabel.setBounds(10, 21, 65, 15);
		frame.getContentPane().add(lblNewLabel);
		
		tfPort = new JTextField();
		tfPort.setText("3000");
		tfPort.setBounds(86, 18, 51, 21);
		frame.getContentPane().add(tfPort);
		tfPort.setColumns(10);
		
		btnStartListening = new JButton("開始聆聽");
		btnStartListening.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					svs = new ServerSocket(Integer.parseInt(tfPort.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				server = new Server(gui);
				server.start();
			}
		});
		btnStartListening.setBounds(369, 17, 87, 23);
		frame.getContentPane().add(btnStartListening);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taBoard.setText("");
				try {
					svs.close();
					server.s.close();
				} catch (NullPointerException e1) {
					frame.setVisible(false);
					//System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
				//System.exit(0);
			}
		});
		btnClose.setBounds(369, 47, 87, 23);
		frame.getContentPane().add(btnClose);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.serverSendFlag = 1;
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
					sf.serverSendFlag = 1;
				}
			}
		});
		
	}
	
	/*
	public static void main(String[] args) {
		ServerGui sg = new ServerGui(new SendFlag());
		sg.initialize();
		sg.setVisible(true);
	}
	*/
}
