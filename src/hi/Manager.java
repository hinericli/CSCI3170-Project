package hi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Manager {
	protected static void performOperation(int operationType) {
		try {
			Connection conn = DataSource.getConnection();
			Scanner scanner = new Scanner(System.in);
			
			switch (operationType) {
				case 1:
					System.out.println("Choose ordering\n"
							+ "1. By ascending order\n"
							+ "2. By descending order\n"
							+ "Choose the list ordering:");
					int order = Integer.parseInt(scanner.nextLine()); 
					listSalespersons(conn, order);
					break;
				case 2:
					System.out.println("Type in the lower bound for years of experience:\n");
					int lowerBound = Integer.parseInt(scanner.nextLine());
					System.out.println("Type in the upper bound for years of experience:\n");
					int upperBound = Integer.parseInt(scanner.nextLine());
					countTransactionRecords(conn, lowerBound, upperBound);
					break;
				case 3:
					listManufacturers(conn);
					break;
				case 4:
					System.out.println("Type in the number of parts:\n");
					int n = Integer.parseInt(scanner.nextLine());
					showNMostPopularParts(conn, n);
					break;
			}	
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static void listSalespersons(Connection conn, int order) { 
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String query = "SELECT * FROM SALESPERSON ";
			
			if (order == 1) {
				query += "ORDER BY sExperience ASC";
			} else if (order == 2) {
				query += "ORDER BY sExperience DESC";
			}
			
			rs = stmt.executeQuery(query);
			
		    System.out.println("| ID | Name | Mobile Phone | Years of Experience |");
		    while (rs.next()) {
		        System.out.print("| " + rs.getString("sID"));
		        System.out.print(" | " + rs.getString("sName"));
		        System.out.print(" | " + rs.getString("sPhoneNumber"));
		        System.out.print(" | " + rs.getString("sExperience"));
		        System.out.println(" |");
		    }
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static void countTransactionRecords(Connection conn, int lowerBound, int upperBound) { 
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;
			
			if (lowerBound > upperBound) {
				System.err.println("Lower bound is larger than upper bound!");
				return;
			}
		    
		    String query = "SELECT SALESPERSON.sID, SALESPERSON.sName, SALESPERSON.sExperience, COUNT(TRANSACTION.tID) AS tCount "
		    		+ "FROM SALESPERSON JOIN TRANSACTION "
		    		+ "ON SALESPERSON.sID=TRANSACTION.sID "
		    		+ "GROUP BY SALESPERSON.sID, SALESPERSON.sName, SALESPERSON.sExperience "
		    		+ "HAVING SALESPERSON.sExperience >= '" + lowerBound
		    		+ "' AND SALESPERSON.sExperience <= '" + upperBound
		    		+ "' ORDER BY SALESPERSON.sID DESC";
		    rs = stmt.executeQuery(query);
			System.out.println("| ID | Name | Years of Experience | Number of Transaction |");
		    while (rs.next()) {
		        System.out.print("| " + rs.getString("sID"));
		        System.out.print(" | " + rs.getString("sName"));
		        System.out.print(" | " + rs.getString("sExperience"));
		        System.out.print(" | " + rs.getString("tCount"));
		        System.out.println(" |");
		    }
		    
		} catch(Exception e) {
			System.err.println(e);
		}
	}
	
	private static void listManufacturers(Connection conn) { 
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;		    
			
		    String pTableQuery = "WITH PTABLE AS (SELECT PART.mID, PART.pID, PART.pPrice, PART.pPrice*COUNT(PART.pID) AS pTotalSales "
					+ "FROM TRANSACTION JOIN PART ON PART.pID=TRANSACTION.pID "
					+ "GROUP BY PART.mID, PART.pID, PART.pPrice) "
					
					+ "SELECT PTABLE.mID, MANUFACTURER.mName, SUM(PTABLE.pTotalSales) AS mTotalSales "
					+ "FROM PTABLE JOIN MANUFACTURER ON PTABLE.mID=MANUFACTURER.mID "
					+ "GROUP BY PTABLE.mID, MANUFACTURER.mName "
					+ "ORDER BY mTotalSales DESC";
		    rs = stmt.executeQuery(pTableQuery);
		    System.out.println("| Manufacturer ID | Manufacturer Name | Total Sales Value |");
		    while (rs.next()) {
		    	System.out.print("| " + rs.getString("mID"));
		    	System.out.print(" | " + rs.getString("mName"));
		        System.out.print(" | " + rs.getString("mTotalSales"));
		        System.out.println(" |");
		    }
			
		} catch(Exception e) {
			System.err.println(e);
		}
	} 
	
	private static void showNMostPopularParts(Connection conn, int n) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;		    
			
			if (n < 0) {
				System.err.println("N should be a positive number!");
				return;
			}
			
		    String pTableQuery = "SELECT PART.pID, PART.pName, COUNT(PART.pID) AS tCount "
					+ "FROM TRANSACTION JOIN PART ON PART.pID=TRANSACTION.pID "
					+ "GROUP BY PART.pID, PART.pName "
					+ "ORDER BY tCount DESC ";
		    rs = stmt.executeQuery(pTableQuery);
		    System.out.println("| Part ID | Part Name | No. of Transaction |");
		    int count = 0;
		    while (rs.next() && count < n) {
		    	System.out.print("| " + rs.getString("pID"));
		    	System.out.print(" | " + rs.getString("pName"));
		        System.out.print(" | " + rs.getString("tCount"));
		        System.out.println(" |");
		        count++;
		    }
		} catch(Exception e) {
			System.err.println(e);
		}
	}

	
}
