//package tamasha;
//
//import java.util.ArrayList;
//import java.util.Vector;
//
//import javax.swing.event.TableModelListener;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//
//public class StockTableModel extends AbstractTableModel {
//	Vector al;
//	Vector header;
//
//	// constructor
//	StockTableModel(Vector visitdata, Vector columnNames) {
//		// save the header
//		this.header = columnNames;
//		// and the rows
//		this.al = visitdata;
//	}
//
//	@Override
//	public void addTableModelListener(TableModelListener arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Class<?> getColumnClass(int c) {
//		// TODO Auto-generated method stub
//		return getValueAt(0, c).getClass();
//	}
//
//	@Override
//	public int getColumnCount() {
//		// TODO Auto-generated method stub
//		return header.size();
//	}
//
//	@Override
//	public String getColumnName(int col) {
//		// TODO Auto-generated method stub
//		return (String) header.get(col);
//	}
//
//	@Override
//	public int getRowCount() {
//		// TODO Auto-generated method stub
//		// if ( al == null) return 0;
//		return al.size();
//	}
//
//	@Override
//	public Object getValueAt(int row, int col) {
//		// TODO Auto-generated method stub
//		Vector rowList = (Vector) al.get(row);
//		String result = null;
//		if (col < rowList.size()) {
//			result = rowList.get(col).toString();
//		}
//
//		return (result);
//	}
//
//	@Override
//	public boolean isCellEditable(int row, int col) {
//		// TODO Auto-generated method stub
//		if (col > 1)
//			return true;
//		return false;
//	}
//
//	@Override
//	public void removeTableModelListener(TableModelListener arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setValueAt(Object arg0, int arg1, int arg2) {
//		// TODO Auto-generated method stub
//
//	}
//
// }
