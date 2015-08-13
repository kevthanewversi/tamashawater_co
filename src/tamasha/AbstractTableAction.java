package tamasha;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AbstractTableAction<T extends JTable, M extends CreditorsTableModel>
		extends AbstractAction {

	private T table;
	private M model;

	public AbstractTableAction(T table, M model2) {
		this.table = table;
		this.model = model2;
	}

	public T getTable() {
		return table;
	}

	public M getModel() {
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
