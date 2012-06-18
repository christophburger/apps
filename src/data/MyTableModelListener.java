package data;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MyTableModelListener implements TableModelListener  {

	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getType() == TableModelEvent.UPDATE) {
			//int row = 
			System.out.println(e.getSource());
			TableModel model = (TableModel)e.getSource();
		}
	}

}
