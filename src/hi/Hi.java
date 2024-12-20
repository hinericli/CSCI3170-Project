package hi;
import java.sql.*;
import java.util.Scanner;

public class Hi {
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//db18.cse.cuhk.edu.hk:1521/oradb.cse.cuhk.edu.hk", "h062", "yogAkkef");
			Statement stmt = conn.createStatement();
			
			// ----- Initial Data Handling -----
			DataParser.parseData();
			
			// ----- User Input -----
			handleConsole();
			
		} catch (Exception e) {
			System.err.println("Something went wrong connection!");
			System.err.println(e);
		}
		
		System.out.println("Exit");
		
	}
	
	private static void handleConsole() {
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		while (isRunning == true) {
			System.out.println("\n");
			System.out.println("Welcome to sales system!\n\n"
					+ "-----Main Menu-----\n"
					+ "What kinds of operation would you like to perform?\n"
					+ "1. Operations for administrator\n"
					+ "2. Operations for salesperson\n"
					+ "3. Operations for manager\n"
					+ "4. Exit this program\n"
					+ "Enter Your Choice: ");
			int userType = Integer.parseInt(scanner.nextLine());
			int operationType;

			System.out.println("\n");
			switch(userType) {
				case 1: 
					System.out.println("-----Operations for administrator menu-----\n"
							+ "What kinds of operation would you like to perform?\n"
							+ "1. Create all tables\n"
							+ "2. Delete all tables\n"
							+ "3. Load from datafile\n"
							+ "4. Show content of a table\n"
							+ "5. Return to the main menu\n"
							+ "Enter Your Choice:");
					operationType = Integer.parseInt(scanner.nextLine());
					Administrator.performOperation(operationType);
					break;
				case 2:
					System.out.println("-----Operations for salesperson menu-----\n"
							+ "What kinds of operation would you like to perform?\n"
							+ "1. Search for parts\n"
							+ "2. Sell a part\n"
							+ "3. Return to the main menu\n"
							+ "Enter Your Choice:");
					operationType = Integer.parseInt(scanner.nextLine());
					Salesperson.performOperation(operationType);
					break;
				case 3:
					System.out.println("-----Operations for manager menu-----\n"
							+ "What kinds of operation would you like to perform?\n"
							+ "1. List all salespersons\n"
							+ "2. Count the no. of sales record of each salesperson under a specific range on years of experience\n"
							+ "3. Show the total sales value of each manufacturer\n"
							+ "4. Show the N most popular part\n"
							+ "5. Return to the main menu");	
			       operationType = Integer.parseInt(scanner.nextLine());
			       Manager.performOperation(operationType);
			       break;
				case 4:
					isRunning = false;
					System.exit(0);
					break;
				default:
					System.out.println("Invaild input!");
					break;		
			}
		}
		scanner.close();
	}
}



/*
		String query1 = "CREATE TABLE DISPATCHES("
			      + "ProductName VARCHAR (20) NOT NULL, "
			      + "CustomerName VARCHAR (20) NOT NULL, "
			      + "DispatchDate date, "
			      + "DeliveryTime timestamp, "
			      + "Price INT, "
			      + "Location varchar(20))";
		String query = "select * from dispatches"; // do not add a semi-colon here
		System.out.println(query);
		stmt.execute(query);
		ResultSet rs = stmt.executeQuery(query);*/


// https://docs.oracle.com/cd/F49540_01/DOC/java.815/a64685/basic1.htm
// select table_name from user_tables;
// describe <table_name>
