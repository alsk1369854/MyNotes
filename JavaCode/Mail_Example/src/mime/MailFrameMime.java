package mime;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MailFrameMime {
	private JFrame frame;
	private JTable table;
	JPanel centerPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailFrameMime window = new MailFrameMime();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MailFrameMime() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel leftPanel = new JPanel();
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		JButton button = new JButton("Get mails");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> rows = new Vector<Vector>();
				MailReaderMime reader = new MailReaderMime();
				Message[] messages = reader.read("host", "account","pwd");// 帳號資訊
				String text = null;
				for (Message msg : messages) {
					String contentType = null;
					try {
						contentType = msg.getContentType();
						System.out.println(msg.getContentType());
					} catch (MessagingException e2) {
						e2.printStackTrace();
					}

					if (contentType.contains("multipart")) {
						Multipart multiPart = null;
						try {
							multiPart = (Multipart) msg.getContent();
						} catch (IOException | MessagingException e1) {
							e1.printStackTrace();
						}

						try {
							for (int i = 0; i < multiPart.getCount(); i++) {
								MimeBodyPart part = (MimeBodyPart) multiPart
										.getBodyPart(i);

								try {
									if (part.getContentType().contains(
											"text/plain")) {
										text = (String) part.getContent();
									}
								} catch (IOException e2) {
									e2.printStackTrace();
								}
								if (Part.ATTACHMENT.equalsIgnoreCase(part
										.getDisposition())) {
									System.out
											.println("here we get attachment");
									try {
										part.saveFile("D:/Attachment_test/"
												+ part.getFileName());
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
						} catch (MessagingException e1) {
							e1.printStackTrace();
						}
					}
					Vector<Object> data = new Vector<Object>();
					try {
						data.add(msg.getFrom()[0]);
						data.add(msg.getSubject());
						// data.add(msg.getContent());
						data.add(text);
						rows.add(data);
					} catch (MessagingException e1) {
						e1.printStackTrace();
					}
				}
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("From");
				columnNames.add("Subject");
				columnNames.add("Content");
				table.setModel(new DefaultTableModel(rows, columnNames));
			}
		});
		leftPanel.add(button);
		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		table = new JTable();
		centerPanel.add(table.getTableHeader(), BorderLayout.NORTH);
		centerPanel.add(table);
	}
}
