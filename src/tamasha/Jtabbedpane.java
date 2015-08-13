package tamasha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import tamasha.Tamashadb;

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
		StockTableModel model;
		JButton SaveBtn, DelBtn, ExitBtn;
		JPanel inspanel = new JPanel(new GridLayout(20, 1));
		JPanel viewpanel = new JPanel();

		// tabbedPane1 inside the stock panel
		tabbedPane1 = new JTabbedPane();
		tabbedPane1.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane1.setBounds(0, 0, 800, 600);
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

		// Create table with data queried from the table "VisitTable"

		// add the model variable to the table then add table to panel
		// creditors = new JTable(model);
		// creditors.setFillsViewportHeight(true);

		// add table to scroll pane
		Tamashadb.FetchfromDB();
		model = new StockTableModel(Tamashadb.visitdata, Tamashadb.columnNames);
		stock = new JTable(model);
		stock.setFillsViewportHeight(true);

		// to select delete several rows at once
		DeleteRowFromStockTableAction deleteAction = new DeleteRowFromStockTableAction(
				stock, model);

		JToolBar tb = new JToolBar();
		tb.add(deleteAction);

		InputMap im = stock
				.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = stock.getActionMap();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
		am.put("deleteRow", deleteAction);
		JScrollPane tableContainer = new JScrollPane(stock);

		viewpanel.add(tableContainer);

	}

	public void createPage2() {
		JTable creditors;
		CreditorsTableModel model;
		JButton SaveBtn, DelBtn, ExitBtn;
		JPanel inscrpanel = new JPanel(new GridLayout(20, 1));
		JPanel viewcrpanel = new JPanel();

		// tabbedPane2 inside the creditor's report panel
		tabbedPane2 = new JTabbedPane();
		tabbedPane2.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane2.setBounds(0, 0, 800, 600);
		tabbedPane2.addTab("Record Creditor's Data", null, inscrpanel); // addicons
		tabbedPane2.addTab("View Creditor's Reports", null, viewcrpanel);
		ArrayList data[] = Tamashadb.FetchfromCDB();

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

		model = new CreditorsTableModel(Tamashadb.visitdataC,
				Tamashadb.columnNamesC);
		creditors = new JTable(model);

		// to select delete several rows at once
		DeleteRowFromTableAction deleteAction = new DeleteRowFromTableAction(
				creditors, model);

		JToolBar tb = new JToolBar();
		tb.add(deleteAction);

		InputMap im = creditors
				.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = creditors.getActionMap();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
		am.put("deleteRow", deleteAction);

		JScrollPane tableContainer = new JScrollPane(creditors);

		viewcrpanel.add(tableContainer);

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
}