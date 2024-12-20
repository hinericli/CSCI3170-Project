package hi;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

import hi.Entities.Category;

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
					+ "    cName VARCHAR(255)"
					+ ")";
			stmt.executeUpdate(categoryQuery);

			System.out.println("Processing...Done! Database is initialized!");
		} catch(Exception e) {
			System.out.println("Failed to create tables!");
		}
	}
	
	private static void deleteTables(Connection conn) { 
		try {
			Statement stmt = conn.createStatement();
			String deleteTableQuery = "DROP TABLE CATEGORY";
			stmt.executeUpdate(deleteTableQuery);
			System.out.println("Processing...Done! Database is removed!");
		} catch(Exception e) {
			System.out.println("Failed to remove tables!");
		}
	}
	
	private static void loadFromDataFile(Connection conn, String folderPath) {
		try {
			PreparedStatement pstmt = conn.prepareStatement( "INSERT INTO CATEGORY VALUES (?, ?)");
			
			List<Category> categoryList = DataParser.parseCategory();
			for (Category category : categoryList) {
	            pstmt.setInt(1, category.getcID());
	            pstmt.setString(2, category.getcName());
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
			
			switch (tableName.toLowerCase()) {
				case "category" :
					ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORY");
					System.out.println("| c_id | c_name |");
					while (rs.next()){
						System.out.print("| " + rs.getString("cID"));
						System.out.print(" | " + rs.getString("cName"));
						System.out.println(" |");
					}
			}
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
