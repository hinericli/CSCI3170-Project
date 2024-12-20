package hi;
import java.sql.*;

public class Administrator {
	protected static void performOperation(int operationType) {
		switch (operationType) {
			case 1:
				createTables();
				break;
			case 2:
				deleteTables();
				break;
			case 3:
				loadFromDataFile();
				break;
			case 4: 
				showTableContent();
				break;
		}
	}
	
	private static void createTables() { 
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			String categoryQuery = "CREATE TABLE DISPATCHES("
				      + "ProductName VARCHAR (20) NOT NULL, "
				      + "CustomerName VARCHAR (20) NOT NULL, "
				      + "DispatchDate date, "
				      + "DeliveryTime timestamp, "
				      + "Price INT, "
				      + "Location varchar(20))";
			stmt.executeUpdate(categoryQuery);

		} catch(Exception e) {
			System.err.println(e);
			
		}
		
		
	}
	
	private static void deleteTables() { 
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			String deleteTableQuery = "DROP TABLE DISPATCHES";
			stmt.executeUpdate(deleteTableQuery);
			
		} catch(Exception e) {
			System.err.println(e);
		}
		
		
	}
	
	private static void loadFromDataFile() {}
	
	private static void showTableContent() {
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM DISPATCHES");
			while (rs.next()){
				System.out.println(rs.getString("ProductName"));
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		

		
		
		
	}
	
}
