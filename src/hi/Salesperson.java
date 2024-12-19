package hi;

import java.util.Scanner;

public class Salesperson {
	protected static void performOperation(int operationType) {
		Scanner scanner = new Scanner(System.in);
		
		switch (operationType) {
			case 1:
				System.out.println("Choose the Search criterion:\n"
						+ "1. Part Name\n"
						+ "2. Manufacturer Name\n"
						+ "Choose the search criterion:");
				int searchCriterion = Integer.parseInt(scanner.nextLine()); // 1: By Part; 2: By Manufacturer Name
				System.out.println("Type in the Search Keyword: ");
				String searchKeyword = scanner.nextLine();
				System.out.println("Choose ordering:\n"
						+ "1. By price, ascending order\n"
						+ "2. By price, descending order\n"
						+ "Choose the search criterion:");
				int order = Integer.parseInt(scanner.nextLine());	// 1: asc; 2: desc
				searchParts(searchCriterion, searchKeyword, order);
				break;
			case 2:
				System.out.println("Enter Your Choice:");
				int choice = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter The Part ID:");
				int partID = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter The Salesperson ID:");
				int sID = Integer.parseInt(scanner.nextLine());
				performTransaction(choice, partID, sID);
				break;
		}
		
		scanner.close();
	}
	
	private static void searchParts(int searchCriterion, String searchKeyword, int order) {}
	
	private static void performTransaction(int choice, int partID, int sID) {}
	
	
}
