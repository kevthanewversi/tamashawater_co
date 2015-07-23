package tamasha;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CreditorsTableModel implements TableModel {
	ArrayList al;
	ArrayList header;

	// constructor
	CreditorsTableModel(ArrayList visitdata, ArrayList columnNames) {
		// save the header
		this.header = columnNames;
		// and the rows
		this.al = visitdata;
		// al = new ArrayList();
		// copy the rows into the ArrayList
		// for (int i = 0; i < al.size(); ++i)
		// al.add( visitdata[i]);
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int c) {
		// TODO Auto-generated method stub
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return header.size();
	}

	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return (String) header.get(col);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// if ( al == null) return 0;
		return al.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		ArrayList rowList = (ArrayList) al.get(row);
		String result = null;
		if (col < rowList.size()) {
			result = rowList.get(col).toString();
		}

		return (result);
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
