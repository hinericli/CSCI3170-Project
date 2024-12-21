package hi;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

import hi.Entities.Category;
import hi.Entities.Manufacturer;
import hi.Entities.Part;
import hi.Entities.Salesperson;
import hi.Entities.Transaction;


public class Administrator {
	protected static void performOperation(int operationType) {
		try {
			Connection conn = DataSource.getConnection();
			Scanner scanner = new Scanner(System.in);
			
			switch (operationType) {
				case 1:
					createTables(conn);
					break;
				case 2:
					deleteTables(conn);
					break;
				case 3:
					System.out.println("\nType in the Source Data Folder Path:");
					String path = scanner.nextLine();
					loadFromDataFile(conn, path);
					break;
				case 4: 
					System.out.println("\nWhich table would you like to show:");
					String tableName = scanner.nextLine();
					showTableContent(conn, tableName);
					break;
			}
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static void createTables(Connection conn) { 
		try {
			Statement stmt = conn.createStatement();
			String categoryQuery = 
					  "CREATE TABLE CATEGORY ("
					+ "    cID INT PRIMARY KEY,"
					+ "    cName VARCHAR(20)"
					+ ")";
			stmt.executeUpdate(categoryQuery);
			
			String manufacturerQuery = 
					  "CREATE TABLE MANUFACTURER ("
					+ "    mID INT PRIMARY KEY,"
					+ "    mName VARCHAR(255),"
					+ "    mAddress VARCHAR(255),"
					+ "    mPhoneNumber INT"
					+ ")";
			stmt.executeUpdate(manufacturerQuery);
			
			String partQuery = 
					"CREATE TABLE PART ("
					+ "    pID INT PRIMARY KEY,"
					+ "    pName VARCHAR(255),"
					+ "    pPrice INT,"
					+ "    mID INT,"
					+ "    cID INT,"
					+ "    pWarrantyPeriod INT,"
					+ "    pAvailableQuantity INT,"
					+ "    FOREIGN KEY (mID) REFERENCES manufacturer(mID),"
					+ "    FOREIGN KEY (cID) REFERENCES category(cID)"
					+ ")";
			stmt.executeUpdate(partQuery);
			
			String salespersonQuery =
					"CREATE TABLE SALESPERSON ("
					+ "    sID INT PRIMARY KEY,"
					+ "    sName VARCHAR(255),"
					+ "    sAddress VARCHAR(255),"
					+ "    sPhoneNumber INT,"
					+ "    sExperience INT"
					+ ")";
			stmt.executeUpdate(salespersonQuery);
			
			String transactionQuery =
					"CREATE TABLE TRANSACTION ("
					+ "    tID INT PRIMARY KEY,"
					+ "    pID INT,"
					+ "    sID INT,"
					+ "    tDate DATE,\n"
					+ "    FOREIGN KEY (pID) REFERENCES part(pID),"
					+ "    FOREIGN KEY (sID) REFERENCES salesperson(sID)"
					+ ")";
			stmt.executeUpdate(transactionQuery);

			System.out.println("Processing...Done! Database is initialized!");
		} catch(Exception e) {
			System.out.println("Failed to create tables!");
			System.err.println(e);
		}
	}
	
	private static void deleteTables(Connection conn) { 
		try {
	        Statement stmt = conn.createStatement();

	        //stmt.executeUpdate("ALTER TABLE TRANSACTION DROP CONSTRAINT pID");
	        //stmt.executeUpdate("ALTER TABLE TRANSACTION DROP CONSTRAINT sID");
	        //stmt.executeUpdate("ALTER TABLE PART DROP CONSTRAINT mID");
	        //stmt.executeUpdate("ALTER TABLE PART DROP CONSTRAINT cID");

	        // Drop the TRANSACTION table
	        stmt.executeUpdate("DROP TABLE TRANSACTION");

	        // Drop the SALESPERSON table
	        stmt.executeUpdate("DROP TABLE SALESPERSON");

	        // Drop the PART table
	        stmt.executeUpdate("DROP TABLE PART");

	        // Drop the MANUFACTURER table
	        stmt.executeUpdate("DROP TABLE MANUFACTURER");

	        // Drop the CATEGORY table
	        stmt.executeUpdate("DROP TABLE CATEGORY");

	        System.out.println("Processing...Done! Tables and constraints dropped successfully!");
	    } catch (Exception e) {
	        System.out.println("Failed to drop tables and constraints!");
	        System.err.println(e);
	    }
		
	}
	
	private static void loadFromDataFile(Connection conn, String folderPath) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CATEGORY VALUES (?, ?)");
			List<Category> categoryList = DataParser.parseCategory(folderPath);
			for (Category category : categoryList) {
	            pstmt.setInt(1, category.getcID());
	            pstmt.setString(2, category.getcName());
	            pstmt.executeUpdate();
	        }

			pstmt = conn.prepareStatement("INSERT INTO MANUFACTURER VALUES (?, ?, ?, ?)");
			List<Manufacturer> manufacturerList = DataParser.parseManufacturer(folderPath);
			for (Manufacturer manufacturer : manufacturerList) {
				pstmt.setInt(1, manufacturer.getmID());
				pstmt.setString(2, manufacturer.getmName());
				pstmt.setString(3, manufacturer.getmAddress());
				pstmt.setInt(4, manufacturer.getmPhoneNumber());
				pstmt.executeUpdate();
	        }
			
			pstmt = conn.prepareStatement( "INSERT INTO PART VALUES (?, ?, ?, ?, ?, ?, ?)");
			List<Part> partList = DataParser.parsePart(folderPath);
			for (Part part : partList) {
				pstmt.setInt(1, part.getpID());
				pstmt.setString(2, part.getpName());
				pstmt.setInt(3, part.getpPrice());
				pstmt.setInt(4, part.getmID());
				pstmt.setInt(5, part.getcID());
				pstmt.setInt(6, part.getpWarrantyPeriod());
				pstmt.setInt(7, part.getpAvailableQuantity());
				pstmt.executeUpdate();
	        }
			
			pstmt = conn.prepareStatement("INSERT INTO SALESPERSON VALUES (?, ?, ?, ?, ?)");
			List<Salesperson> salespersonList = DataParser.parseSalesperson(folderPath);
			for (Salesperson salesperson : salespersonList) {
			    pstmt.setInt(1, salesperson.getsID());
			    pstmt.setString(2, salesperson.getsName());
			    pstmt.setString(3, salesperson.getsAddress());
			    pstmt.setInt(4, salesperson.getsPhoneNumber());
			    pstmt.setInt(5, salesperson.getsExperience());
			    pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement("INSERT INTO TRANSACTION VALUES (?, ?, ?, ?)");
			List<Transaction> transactionList = DataParser.parseTransaction(folderPath); // Assuming a DataParser class for parsing Transaction data
			for (Transaction transaction : transactionList) {
			    pstmt.setInt(1, transaction.gettID());
			    pstmt.setInt(2, transaction.getpID());
			    pstmt.setInt(3, transaction.getsID());
			    pstmt.setDate(4, new java.sql.Date(transaction.gettDate().getTime()));
			    pstmt.executeUpdate();
			}
			
			System.out.println("Processing...Done! Database is inputted into the database!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static void showTableContent(Connection conn, String tableName) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;
			
			switch (tableName.toLowerCase()) {
				case "category" :
					rs = stmt.executeQuery("SELECT * FROM CATEGORY");
					System.out.println("| c_id | c_name |");
					while (rs.next()){
						System.out.print("| " + rs.getString("cID"));
						System.out.print(" | " + rs.getString("cName"));
						System.out.println(" |");
					}
					break;
				case "manufacturer":
					rs = stmt.executeQuery("SELECT * FROM MANUFACTURER");
					System.out.println("| m_id | m_name | m_address | mPhoneNumber |");
					while (rs.next()){
						System.out.print("| " + rs.getString("mID"));
						System.out.print(" | " + rs.getString("mName"));
						System.out.print(" | " + rs.getString("mAddress"));
						System.out.print(" | " + rs.getString("mPhoneNumber"));
						System.out.println(" |");
					}
					break;
				case "part":
					rs = stmt.executeQuery("SELECT * FROM PART");
					System.out.println("| p_id | p_name | p_price | mID | cID | p_warranty_period | p_available_quantity |");
					while (rs.next()){
						System.out.print("| " + rs.getString("pID"));
						System.out.print(" | " + rs.getString("pName"));
						System.out.print(" | " + rs.getString("pPrice"));
						System.out.print(" | " + rs.getString("mID"));
						System.out.print(" | " + rs.getString("cID"));
						System.out.print(" | " + rs.getString("pWarrantyPeriod"));
						System.out.print(" | " + rs.getString("pAvailableQuantity"));
						System.out.println(" |");
					}
					break;
				case "salesperson":
				    rs = stmt.executeQuery("SELECT * FROM SALESPERSON");
				    System.out.println("| sID | sName | sAddress | sPhoneNumber | sExperience |");
				    while (rs.next()) {
				        System.out.print("| " + rs.getString("sID"));
				        System.out.print(" | " + rs.getString("sName"));
				        System.out.print(" | " + rs.getString("sAddress"));
				        System.out.print(" | " + rs.getString("sPhoneNumber"));
				        System.out.print(" | " + rs.getString("sExperience"));
				        System.out.println(" |");
				    }
				    break;
				case "transaction":
				    rs = stmt.executeQuery("SELECT * FROM TRANSACTION");
				    System.out.println("| tID | pID | sID | tDate |");
				    while (rs.next()) {
				        System.out.print("| " + rs.getString("tID"));
				        System.out.print(" | " + rs.getString("pID"));
				        System.out.print(" | " + rs.getString("sID"));
				        System.out.print(" | " + rs.getString("tDate"));
				        System.out.println(" |");
				    }
				    break;
					
			}
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
