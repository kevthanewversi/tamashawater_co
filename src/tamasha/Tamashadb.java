package tamasha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Tamashadb {
	// public static void main(String args[]) {
	// Connection c = null;
	// try {
	// Class.forName("org.sqlite.JDBC");
	// c = DriverManager.getConnection("jdbc:sqlite:test.db"); // create
	// connection object
	// } catch (Exception e) {
	// System.err.println(e.getClass().getName() + ": " + e.getMessage());
	// System.exit(0);
	// }
	// System.out.println("Opened database successfully");
	// }

	// STEP 1. Import required packages
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/TAMASHA";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn;
	static ResultSet rs;
	public static ArrayList columnNames;
	public static ArrayList visitdata;
	static Statement stmt;
	static Statement stmt1;
	public static ArrayList columnNamesC;
	public static ArrayList visitdataC;

	public static void main(String[] args) {
		createDB();
		FetchfromDB();
		// }

	}// end main

	/* db operations for the water bottles */
	public static void createDB() {// if db exists just connect to it
		try {

			// STEP 4: Execute a query
			System.out.println("Creating database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS TAMASHA";

			stmt.executeUpdate(sql);

			System.out.println("Database created successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			// create tamasha tables
			String stocksql = "CREATE TABLE IF NOT EXISTS STOCK"
					+ "(ID INT PRIMARY KEY AUTO_INCREMENT,"
					+ "HALFLT INTEGER NOT NULL," + "ONELT INTEGER NOT NULL,"
					+ "FIVELT INTEGER NOT NULL," + "TOTAL INTEGER NOT NULL,"
					+ "PURCHASED INTEGER NOT  NULL,"
					+ "UNPURCHASED INTEGER NOT NULL)";

			String creditorssql = "CREATE TABLE IF  NOT EXISTS CREDITORS"
					+ "(ID INT PRIMARY KEY AUTO_INCREMENT,"
					+ "FNAME VARCHAR(50)," + "SNAME  VARCHAR(50),"
					+ "ACREDITED INTEGER NOT NULL," + "APAID INTEGER NOT NULL,"
					+ "AOWED INTEGER NOT  NULL)";

			try {
				stmt.executeUpdate(creditorssql);
				stmt.executeUpdate(stocksql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ArrayList[] FetchfromDB() {
		// create cursor to fetch
		columnNames = new ArrayList();
		visitdata = new ArrayList<Object[]>();
		/*
		 * SQL query that retrieves all the information
		 */
		String query = "SELECT * FROM STOCK";
		try {
			// Connect to the Database
			ConnecttoDB();
			// Read data from a table
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();
			// Get number of columns
			int columns = md.getColumnCount();
			// Get column names
			for (int i = 1; i <= columns; i++) {
				System.out.println(md.getColumnName(i));
				columnNames.add(md.getColumnName(i));
				System.out.println(columnNames.size());
			}
			// Get row data
			while ((rs != null) && (rs.next())) {
				ArrayList row = new ArrayList(columns);
				for (int i = 1; i <= columns; i++) {
					row.add(rs.getObject(i));
				}
				visitdata.add(row);

			}
			// Close the resultset, statement and the connection
			rs.close();
			closeDBconnection();
			// System.out.println("Hey " + visitdata.size());

			System.out.println("Finished fetching from  database...");

		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList[] data = { visitdata, columnNames };
		System.out.println(data[1].size() + " " + data[0].size());
		return data;

	}

	// before method is called connect to db first
	public static void InserttoDB(int onelitre, int onelitre2, int fivelitre,
			int purchased) {
		ConnecttoDB();

		try {
			// get values from textfield first
			String insertDB = "INSERT INTO STOCK (HALFLT,ONELT,FIVELT,PURCHASED,UNPURCHASED,TOTAL)  VALUES('"
					+ onelitre
					+ "','"
					+ onelitre2
					+ "','"
					+ fivelitre
					+ "','"
					+ fivelitre + "','" + fivelitre + "','" + purchased + "' )";
			stmt1 = conn.createStatement();
			stmt1.executeUpdate(insertDB);
			System.out.println("Records entered to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void DeletefromDB() {
	// String deletefromdb = "DELETE FROM STOCK"
	// }

	public void UpdateinDB() {
	}

	public void searchDB() {

	}

	/* db operations for the creditors */

	public static ArrayList[] FetchfromCDB() {
		// create cursor to fetch
		columnNamesC = new ArrayList();
		visitdataC = new ArrayList<Object[]>();
		/*
		 * SQL query that retrieves all the information
		 */
		String query = "SELECT * FROM CREDITORS";
		try {
			// Connect to the Database
			ConnecttoDB();
			// Read data from a table
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();
			// Get number of columns
			int columns = md.getColumnCount();
			// Get column names
			for (int i = 1; i <= columns; i++) {
				System.out.println(md.getColumnName(i));
				columnNamesC.add(md.getColumnName(i));
				System.out.println(columnNamesC.size());
			}
			// Get row data
			while ((rs != null) && (rs.next())) {
				ArrayList rowC = new ArrayList(columns);
				for (int i = 1; i <= columns; i++) {
					rowC.add(rs.getObject(i));
				}
				visitdataC.add(rowC);

			}
			// Close the resultset, statement and the connection
			rs.close();
			closeDBconnection();
			stmt.close();

			System.out.println("Finished fetching from  database...");

		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList[] data = { visitdataC, columnNamesC };
		System.out.println(data[1].size() + " " + data[0].size());
		return data;

	}

	public static void InserttoCDB(String fullname, String surname,
			int amountcredited, int amountpaid) {
		try {
			String insertCDB = "INSERT INTO STOCK (HALFLT,ONELT,FIVELT,PURCHASED,UNPURCHASED,TOTAL)  VALUES('"
					+ fullname
					+ "','"
					+ surname
					+ "','"
					+ amountcredited
					+ "','" + amountpaid + "' )";
			stmt1 = conn.createStatement();
			stmt1.executeUpdate(insertCDB);
			System.out.println("Records entered to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	// public void DeletefromDB() {
	// String deletefromdb = "DELETE FROM STOCK"
	// }

	public void UpdateinCDB() {
	}

	public void searchCDB() {

	}

	public static void ConnecttoDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to database...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// close the DB connection
	public static void closeDBconnection() {
		// TODO Auto-generated method stub
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}// end finally try
		System.out.println("BYE!");
	}// end try

}
