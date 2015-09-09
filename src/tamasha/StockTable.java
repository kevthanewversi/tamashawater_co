package tamasha;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StockTable {
	JTable stock;
	public static StockTableModel model;

	public StockTable(Object[][] visitdata2, ArrayList columnNames) {
		stock = new JTable(model);
		// stock.setFillsViewportHeight(true);
	}

}
