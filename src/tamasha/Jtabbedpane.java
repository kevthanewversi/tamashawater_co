package tamasha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import tamasha.Tamashadb;
import tamasha.DeleteRowFromStockTableAction;

class Jtabbedpane extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	JTabbedPane tabbedPane1, tabbedPane2;
	private JPanel panel1, panel2, panel3;
	JTextField halflt, onelt, fivelt, apurchased;
	JTextField fname, sname, acredited, apaid;
	DeleteRowFromStockTableAction deleteAction;

	public Jtabbedpane() {
		// NOTE: to reduce the amount of code in this example, it uses
		// panels with a NULL layout. This is NOT suitable for
		// production code since it may not display correctly for
		// a look-and-feel.

		setTitle("Tamasha Water Co.");
		setSize(800, 600);
		setBackground(Color.gray);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		// Create the tab pages
		createPage1();
		createPage2();
		createPage3();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();

		tabbedPane.addTab("STOCK", tabbedPane1);

		tabbedPane.addTab("CREDITORS' REPORTS", tabbedPane2);
		tabbedPane.addTab("HELP", panel3);
		topPanel.add(tabbedPane, BorderLayout.CENTER);
	}

	public void createPage1() {
		JTable stock;
		DefaultTableModel model;
		JPanel inspanel = new JPanel(new GridLayout(20, 1));
		JPanel viewpanel = new JPanel();
		viewpanel.setLayout(new BoxLayout(viewpanel, BoxLayout.Y_AXIS));

		// tabbedPane1 inside the stock panel
		tabbedPane1 = new JTabbedPane();
		tabbedPane1.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane1.addTab("Record Stock Data", null, inspanel); // add icons
		tabbedPane1.addTab("View Stock", null, viewpanel);

		// panel contains all the form fields used to insert data into DB
		JLabel label1 = new JLabel("Half Litre bottles");
		label1.setBounds(290, 30, 150, 20);
		inspanel.add(label1);

		halflt = new JTextField();
		halflt.setBounds(290, 60, 150, 20);
		inspanel.add(halflt);

		JLabel label2 = new JLabel("One Litre bottles");
		label2.setBounds(290, 90, 150, 20);
		inspanel.add(label2);

		onelt = new JTextField();
		onelt.setBounds(290, 120, 150, 20);
		inspanel.add(onelt);

		JLabel label3 = new JLabel("Five Litre bottles");
		label3.setBounds(290, 150, 150, 20);
		inspanel.add(label3);

		fivelt = new JTextField();
		fivelt.setBounds(290, 180, 150, 20);
		inspanel.add(fivelt);

		JLabel label4 = new JLabel("Bottles Sold");
		label4.setBounds(290, 210, 150, 20);
		inspanel.add(label4);

		apurchased = new JTextField();
		apurchased.setBounds(290, 240, 150, 20);
		inspanel.add(apurchased);

		JButton save = new JButton("Save");
		save.setBounds(600, 270, 150, 20);
		inspanel.add(save);

		// action listener for the save button
		save.addActionListener(saveevent);

		// viewPanel where you can view stock records in DB
		// add the model variable to the table then add table to panel

		Tamashadb.FetchfromDB();
		model = new DefaultTableModel(Tamashadb.visitdata,
				Tamashadb.columnNames);
		stock = new JTable(model);
		JScrollPane tableContainer = new JScrollPane(stock); // add table to
		// scroll pane
		viewpanel.add(tableContainer);

		// to select delete several rows at once
		deleteAction = new DeleteRowFromStockTableAction(stock, model);

		// JToolBar tb = new JToolBar("Delete");
		// tb.setFloatable(false);
		// tb.add(deleteAction);

		InputMap im = stock
				.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
		ActionMap am = stock.getActionMap();
		am.put("deleteRow", new DeleteRowFromStockTableAction(stock, model));

		// viewpanel.add(tb);

	}

	public void createPage2() {
		JTable creditors;
		DefaultTableModel model;
		JPanel inscrpanel = new JPanel(new GridLayout(20, 1));
		JPanel viewcrpanel = new JPanel();
		viewcrpanel.setLayout(new BoxLayout(viewcrpanel, BoxLayout.Y_AXIS));

		// tabbedPane2 inside the creditor's report panel
		tabbedPane2 = new JTabbedPane();
		tabbedPane2.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane2.addTab("Record Creditor's Data", null, inscrpanel); // addicons
		tabbedPane2.addTab("View Creditor's Reports", null, viewcrpanel);
		Vector data[] = Tamashadb.FetchfromCDB();

		// panel contains all the form fields used to insert data into DB

		// panel contains all the form fields used to insert data into DB
		JLabel label1 = new JLabel(" First Name");
		label1.setBounds(290, 30, 150, 20);
		inscrpanel.add(label1);

		fname = new JTextField();
		fname.setBounds(290, 60, 150, 20);
		inscrpanel.add(fname);

		JLabel label2 = new JLabel("Surname");
		label2.setBounds(290, 90, 150, 20);
		inscrpanel.add(label2);

		sname = new JTextField();
		sname.setBounds(290, 120, 150, 20);
		inscrpanel.add(sname);

		JLabel label3 = new JLabel("Amount Credited");
		label3.setBounds(290, 150, 150, 20);
		inscrpanel.add(label3);

		acredited = new JTextField();
		acredited.setBounds(290, 180, 150, 20);
		inscrpanel.add(acredited);

		JLabel label4 = new JLabel("Amount Paid");
		label4.setBounds(290, 210, 150, 20);
		inscrpanel.add(label4);

		apaid = new JTextField();
		apaid.setBounds(290, 240, 150, 20);
		inscrpanel.add(apaid);

		JButton savecr = new JButton("Save");
		savecr.setBounds(600, 270, 150, 20);
		inscrpanel.add(savecr);

		// action listener for the save button
		savecr.addActionListener(saveeventcr);

		// Create table with data queried from the table "VisitTable"
		// add the model variable to the table then add table to panel

		model = new DefaultTableModel(Tamashadb.visitdataC,
				Tamashadb.columnNamesC);
		creditors = new JTable(model);

		// to select delete several rows at once
		DeleteRowFromCreditorsTableAction deleteAction = new DeleteRowFromCreditorsTableAction(
				creditors, model);

		JToolBar tb = new JToolBar();
		tb.setFloatable(false);
		tb.add(deleteAction);

		// for the delete key binding
		InputMap im = creditors
				.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = creditors.getActionMap();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
		am.put("deleteRow", deleteAction);

		JScrollPane tableContainer = new JScrollPane(creditors);
		viewcrpanel.add(tableContainer);
		viewcrpanel.add(tb);

	}

	public void createPage3() {

	}

	// Main method to get things started
	public static void main(String args[]) {
		// Create an instance of the test application
		Jtabbedpane mainFrame = new Jtabbedpane();
		mainFrame.setVisible(true);
	}

	ActionListener saveevent = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int halflitre = Integer.parseInt(halflt.getText());
			int onelitre = Integer.parseInt(onelt.getText());
			int fivelitre = Integer.parseInt(fivelt.getText());
			int purchased = Integer.parseInt(apurchased.getText());

			// call insert to DB method with above parameters
			Tamashadb.InserttoDB(halflitre, onelitre, fivelitre, purchased);
		}
	};

	ActionListener saveeventcr = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String fullname = fname.getText();
			String surname = sname.getText();
			int amountcredited = Integer.parseInt(acredited.getText());
			int amountpaid = Integer.parseInt(apaid.getText());

			// call insert to DB method with above parameters
			Tamashadb
					.InserttoCDB(fullname, surname, amountcredited, amountpaid);
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public class DeleteRowFromStockTableAction extends
			AbstractStockTableAction<JTable, DefaultTableModel> {

		public DeleteRowFromStockTableAction(JTable stock,
				DefaultTableModel model) {
			// TODO Auto-generated constructor stub
			super(stock, model);
			putValue(NAME, "Delete selected rows");
			putValue(SHORT_DESCRIPTION, "Delete selected rows");
			stock.getSelectionModel().addListSelectionListener(
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
			System.out.println("Delete Key has been pressed");
			JTable table = getTable();
			if (table.getSelectedRowCount() > 0) {
				List<Vector> selectedRows = new ArrayList<>(25);
				DefaultTableModel model = getModel();
				Vector rowData = model.getDataVector();
				for (int row : table.getSelectedRows()) {
					int modelRow = table.convertRowIndexToModel(row);
					Vector rowValue = (Vector) rowData.get(modelRow);
					selectedRows.add(rowValue);
				}

				int a = JOptionPane.showConfirmDialog(null,
						"Delete this stock entry?", "Delete Stock Entry ",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					for (Vector rowValue : selectedRows) {
						int rowIndex = rowData.indexOf(rowValue);
						// delete entry from database
						Tamashadb.DeletefromDB((int) rowValue.firstElement());

						model.removeRow(rowIndex);
					}
				}
			}
		}
	}

	public class DeleteRowFromCreditorsTableAction extends
			AbstractTableAction<JTable, DefaultTableModel> {

		/**
 * 
 */
		private static final long serialVersionUID = 5133264544084101883L;

		public DeleteRowFromCreditorsTableAction(JTable creditors,
				DefaultTableModel model) {
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
				DefaultTableModel model = getModel();
				Vector rowData = model.getDataVector();
				for (int row : table.getSelectedRows()) {
					int modelRow = table.convertRowIndexToModel(row);
					Vector rowValue = (Vector) rowData.get(modelRow);
					selectedRows.add(rowValue);
				}

				int a = JOptionPane.showConfirmDialog(null,
						"Delete this stock entry?", "Delete Stock Entry ",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					for (Vector rowValue : selectedRows) {
						int rowIndex = rowData.indexOf(rowValue);
						Tamashadb.DeletefromCDB((int) rowValue.firstElement());
						model.removeRow(rowIndex);
					}
				}
			}

			final JTable stock = new JTable();
			stock.getModel().addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					switch (e.getType()) {
					case TableModelEvent.INSERT:
						stock

					case TableModelEvent.DELETE:

					case TableModelEvent.UPDATE:

					}
				}

			});

		}
	}
}