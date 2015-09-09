package tamasha;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CreditorsTable {
	JTable creditors;
	public static CreditorsTableModel model;

	public CreditorsTable(Object[][] visitdata, ArrayList columnNames) {
		System.out.println(visitdata.length + "and" + columnNames.size());
		creditors = new JTable(model);
		creditors.setFillsViewportHeight(true);
	}

}
