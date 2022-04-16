
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class OpenWindowsGui {
	private JFrame frame; 
	private JButton btnOpenClientGui;
	private JButton btnOpenServerGui;
	private JButton btnTerminate;
	
	ClientGui client;
	ServerGui server;
	
	OpenWindowsGui(ClientGui client, ServerGui server){
		this.client = client;
		this.server = server;
	}
	
	void setVisible(boolean visiable) {
		frame.setVisible(visiable);
	}
	
	void initialize() {
		frame = new JFrame();
		frame.setTitle("Open Windows");
		frame.setBounds(100, 100, 506, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnOpenClientGui = new JButton("Open ClientGui");
		btnOpenClientGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.setVisible(true);
			}
		});
		btnOpenClientGui.setFont(new Font("新細明體", Font.BOLD, 18));
		btnOpenClientGui.setBounds(82, 32, 326, 81);
		frame.getContentPane().add(btnOpenClientGui);
		
		btnOpenServerGui = new JButton("Open ServerGui");
		btnOpenServerGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.setVisible(true);
			}
		});
		btnOpenServerGui.setFont(new Font("新細明體", Font.BOLD, 18));
		btnOpenServerGui.setBounds(82, 145, 326, 81);
		frame.getContentPane().add(btnOpenServerGui);
		
		btnTerminate = new JButton("Terminate");
		btnTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.s.close();
					server.svs.close();
				} catch (NullPointerException e1) {
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
				
			}
		});
		btnTerminate.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnTerminate.setBounds(82, 258, 326, 81);
		frame.getContentPane().add(btnTerminate);
	}
	/*
	public static void main(String[] args) {
		OpenWindowsGui owg = new OpenWindowsGui(new ClientGui(null), new ServerGui(null));
		owg.initialize();
		owg.setVisible(true);
	}
	*/
}
