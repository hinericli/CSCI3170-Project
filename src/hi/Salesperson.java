package hi;

import java.sql.*;
import java.util.Scanner;

public class Salesperson {
	protected static void performOperation(int operationType) {
		try {
			Connection conn = DataSource.getConnection();
			Scanner scanner = new Scanner(System.in);

			switch (operationType) {
				case 1:
					System.out.println("Choose the Search criterion:\n"
							+ "1. Part Name\n"
							+ "2. Manufacturer Name\n"
							+ "Choose the search criterion:");
					int searchDB = Integer.parseInt(scanner.nextLine()); // 1: By Part; 2: By Manufacturer Name
					System.out.println("Type in the Search Keyword: ");
					String searchKeyword = scanner.nextLine();
					System.out.println("Choose ordering:\n"
							+ "1. By price, ascending order\n"
							+ "2. By price, descending order\n"
							+ "Choose the search criterion:");
					int order = Integer.parseInt(scanner.nextLine());	// 1: asc; 2: desc
					searchParts(conn, searchDB, searchKeyword, order);
					break;
				case 2:
					System.out.println("Enter The Part ID:");
					int partID = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter The Salesperson ID:");
					int sID = Integer.parseInt(scanner.nextLine());
					performTransaction(conn, partID, sID);
					break;
			}
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	private static void searchParts(Connection conn, int searchDB, String searchKeyword, int order) {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * "
					+ "FROM PART "
					+ "JOIN MANUFACTURER ON PART.mID=MANUFACTURER.mID "
					+ "JOIN CATEGORY ON PART.cID=CATEGORY.cID ";
			
			if (searchDB == 1) {
				query += "WHERE pName LIKE '%" + searchKeyword + "%' ";
			} else if (searchDB == 2) {
				query += "WHERE mName LIKE '%" + searchKeyword + "%'";
			}
			
			if (order == 1) {
				query += "ORDER BY pPrice ASC";
			} else if (order == 2) {
				query += "ORDER BY pPrice DESC";
			}

			ResultSet rs = stmt.executeQuery(query);
			System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
			while (rs.next()) {
				System.out.print("| " + rs.getString("pID"));
				System.out.print(" | " + rs.getString("pName"));
				System.out.print(" | " + rs.getString("mName"));
				System.out.print(" | " + rs.getString("cName"));
				System.out.print(" | " + rs.getString("pAvailableQuantity"));
				System.out.print(" | " + rs.getString("pWarrantyPeriod"));
				System.out.print(" | " + rs.getString("pPrice"));
				System.out.println(" |");
			}			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static void performTransaction(Connection conn, int partID, int sID) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;
			
			// --- Handle salesperson DB: check if salesperson exist ---
			String salespersonQuery = "SELECT sID FROM SALESPERSON WHERE sID='" + sID + "'";
			rs = stmt.executeQuery(salespersonQuery);
			if (!rs.next()) {
				System.err.println("Salesperson ID does not exist in the database!");
				return;
			}
			
			// --- Handle part DB ---
			String partQuery = "SELECT pID, pName, pAvailableQuantity "
					+ "FROM PART "
					+ "WHERE pID='" + partID + "'";
			rs = stmt.executeQuery(partQuery);
			
			if (rs.next()) {
				String pName = rs.getString("pName");
				String pID = Integer.toString(rs.getInt("pID"));
				int quantity = rs.getInt("pAvailableQuantity");
				
				if (quantity <= 0) {
					System.err.println("No available quantity for the part!");
					return;
				}
				
				int newQuantity = quantity - 1;
		        stmt.executeUpdate("UPDATE PART "
		        		+ "SET pAvailableQuantity = '" + newQuantity + "'"
		        		+ "WHERE pID='" + partID + "'");
		        System.out.println("Product: "+pName+"(id:"+pID+") Remaining Quality: "+newQuantity);
			}
			
	        // --- Handle transaction DB ---
			// Get max(tID) for tID of next transaction record
			String transactionQuery = "SELECT tID FROM (SELECT * FROM TRANSACTION ORDER BY tID DESC) WHERE ROWNUM<=1 ORDER BY tID DESC";
			rs = stmt.executeQuery(transactionQuery);
			if (rs.next()) {
				int nexttID = rs.getInt("tID") + 1;
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TRANSACTION VALUES (?, ?, ?, ?)");
				pstmt.setInt(1, nexttID);
			    pstmt.setInt(2, partID);
			    pstmt.setInt(3, sID);

			    long millis=System.currentTimeMillis();  
		        java.sql.Date tDate=new java.sql.Date(millis);  
			    pstmt.setDate(4, tDate);
			    pstmt.executeUpdate();
			}
			
			
		} catch (Exception e) {
			System.err.println(e);
			
		}
		
		
		
	}
	
	
}
