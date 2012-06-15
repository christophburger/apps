package data;

import javax.swing.table.DefaultTableModel;


public class MyTableModel extends DefaultTableModel {

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

}
