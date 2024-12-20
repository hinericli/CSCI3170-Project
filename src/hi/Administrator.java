package hi;
import java.sql.*;
import java.util.Scanner;

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
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO CATEGORY (cID, cName)"
					+ "VALUES (1, 'Testing')";
			stmt.executeUpdate(query);

			System.out.println("Processing...Done! Database is inputted into the database!");
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		
	}
	
	private static void showTableContent(Connection conn, String tableName) {
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORY");
			while (rs.next()){
				System.out.println(rs.getString("cID"));
				System.out.println(rs.getString("cName"));
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		

		
		
		
	}
	
}
