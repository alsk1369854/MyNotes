package Algorithm_Lab_001;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WindowHandler extends WindowAdapter{
	private DataLink dataLink;
	
	public WindowHandler(DataLink dataLink) {
		this.dataLink = dataLink;
	}
	public void windowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "確定要結束程式嗎?", "確認訊息", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			dataLink.saveStudents();
			System.exit(0);
		}
	}
}
