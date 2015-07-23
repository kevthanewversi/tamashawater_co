package tamasha;

import java.util.ArrayList;

import javax.swing.JTable;

public class StockTable {
	JTable stock;
	public static StockTableModel model;

	public StockTable(Object[][] visitdata2, ArrayList columnNames) {
		stock = new JTable(model);
		stock.setFillsViewportHeight(true);
	}

}
