package Java_FinalProject;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ListBtnRender implements TableCellRenderer {
	private ListBtn button;

	public ListBtnRender(String btnName) {
		button = new ListBtn(btnName);
		button.setFont(new Font("新細明體",Font.BOLD,18));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return button;
	}

}
