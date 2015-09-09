package tamasha;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AbstractStockTableAction<T extends JTable, M extends StockTableModel>
		extends AbstractAction {

	private T table;
	private M model;

	// constructor
	public AbstractStockTableAction(T table, StockTableModel model2) {
		this.table = table;
		this.model = (M) model2;
	}

	// getters
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
