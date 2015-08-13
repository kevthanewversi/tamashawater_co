package tamasha;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DeleteRowFromTableAction extends
		AbstractTableAction<JTable, CreditorsTableModel> {

	public DeleteRowFromTableAction(JTable creditors, CreditorsTableModel model) {
		// TODO Auto-generated constructor stub
		super(creditors, model);
		putValue(NAME, "Delete selected rows");
		putValue(SHORT_DESCRIPTION, "Delete selected rows");
		creditors.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						setEnabled(getTable().getSelectedRowCount() > 0);
					}
				});
		setEnabled(getTable().getSelectedRowCount() > 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("...");
		JTable table = getTable();
		if (table.getSelectedRowCount() > 0) {
			List<Vector> selectedRows = new ArrayList<>(25);
			CreditorsTableModel model = getModel();
			Vector rowData = model.getDataVector();
			for (int row : table.getSelectedRows()) {
				int modelRow = table.convertRowIndexToModel(row);
				Vector rowValue = (Vector) rowData.get(modelRow);
				selectedRows.add(rowValue);
			}

			for (Vector rowValue : selectedRows) {
				int rowIndex = rowData.indexOf(rowValue);
				model.removeRow(rowIndex);
			}
		}
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
