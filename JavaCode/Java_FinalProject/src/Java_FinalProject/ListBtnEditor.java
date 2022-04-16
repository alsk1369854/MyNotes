package Java_FinalProject;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ListBtnEditor extends DefaultCellEditor {
	private ListBtn button;
	
	public ListBtnEditor(String btnName) {
		super(new JTextField());
		button = new ListBtn(btnName);
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			button.setRow(row);
			button.setColumn(column);
			return button;
	}
	
}