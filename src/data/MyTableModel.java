package data;


import javax.swing.table.DefaultTableModel;

import control.PluginManager;



public class MyTableModel extends  DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyTableModel() {
		super();
	}
	
	public MyTableModel(Object[][] data, Object[] columnObjects) {
		super(data, columnObjects);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		//return super.isCellEditable(row, column);
		if(column == 2)
			return true;
		return false;
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
		String p = (String)getValueAt(row, 0);
		//Plugin plugin = PluginManager.getPlugin(p);
		System.out.println("value: " + aValue);
		if(aValue.equals("Aktiviert"))
			PluginManager.loadPlugin(p);
		else
			PluginManager.unloadPlugin(p);
	}
	
	@Override
	public void fireTableDataChanged() {
		// TODO Auto-generated method stub
		super.fireTableDataChanged();
	}
	
}
