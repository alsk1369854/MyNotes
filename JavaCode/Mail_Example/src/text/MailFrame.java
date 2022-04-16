package text;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MailFrame {
  private JFrame frame;
  private JTable table;
  JPanel centerPanel;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MailFrame window = new MailFrame();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public MailFrame() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);
    JPanel leftPanel = new JPanel();
    frame.getContentPane().add(leftPanel,
        BorderLayout.WEST);
    JButton button = new JButton("Get mails");
    button.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        	 Vector <Vector> rows = new Vector <Vector> ();
        	 MailReader reader = new MailReader();
        	 //Message[] messages = reader.read("host", "account", "pwd");//帳號資訊 
        	 Message[] messages = reader.read("127.0.0.1", "alsk1369854@gmail.com", "z1369854Qpxm");//帳號資訊 
        	 for (Message msg : messages) {
        		 Vector <Object> data = new Vector <Object> ();
        		 try {
        			 data.add(msg.getFrom()[0]);
        			 data.add(msg.getSubject());
        			 data.add(msg.getContent());
        			 rows.add(data);
        		 } catch (MessagingException e1) {
        			 e1.printStackTrace();
        		 } catch (IOException e1) {
        			 e1.printStackTrace();
        		 }
        	 }
        	 Vector <String> columnNames = new Vector <String> ();
        	 columnNames.add("From");
        	 columnNames.add("Subject");
        	 columnNames.add("Content");
        	 table.setModel(new DefaultTableModel(rows, columnNames));
    	}
    });
    leftPanel.add(button);
    centerPanel = new JPanel();
    frame.getContentPane().add(centerPanel,
    BorderLayout.CENTER);
    table = new JTable();
    centerPanel.add(table.getTableHeader(), BorderLayout.NORTH);
    centerPanel.add(table);
  }
}
