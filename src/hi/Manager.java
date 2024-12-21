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
			String query = "SELECT * FROM SALESPERSON "
					+ "WHERE sExperience >= '" + lowerBound 
					+ "' AND sExperience <= '" + upperBound
					+ "' ORDER BY sID DESC";
			rs = stmt.executeQuery(query);
			System.out.println("| ID | Name | Years of Experience | Number of Transaction ");
		    while (rs.next()) {
		        System.out.print("| " + rs.getString("sID"));
		        System.out.print(" | " + rs.getString("sName"));
		        System.out.print(" | " + rs.getString("sExperience"));
		        System.out.println(" |");
		    }
		} catch(Exception e) {
			System.err.println(e);
		}
		
		
		
		
	}
	
	private static void listManufacturers(Connection conn) { } 
	
	private static void showNMostPopularParts(Connection conn, int n) { }

	
}
