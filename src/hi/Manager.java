package hi;

import java.util.Scanner;

public class Manager {
	protected static void performOperation(int operationType) {
		Scanner scanner = new Scanner(System.in);
		
		switch (operationType) {
			case 1:
				System.out.println("Choose ordering\n"
						+ "1. By ascending order\n"
						+ "2. By descending order\n"
						+ "Choose the list ordering:");
				int order = Integer.parseInt(scanner.nextLine()); 
				listSalespersons(order);
				break;
			case 2:
				System.out.println("Type in the lower bound for years of experience:\n");
				int lowerBound = Integer.parseInt(scanner.nextLine());
				System.out.println("Type in the upper bound for years of experience:\n");
				int upperBound = Integer.parseInt(scanner.nextLine());
				countTransactionRecords(lowerBound, upperBound);
				break;
			case 3:
				listManufacturers();
				break;
			case 4:
				System.out.println("Type in the number of parts:\n");
				int n = Integer.parseInt(scanner.nextLine());
				showNMostPopularParts(n);
				break;
		}	
		
		
		scanner.close();
	}
	
	private static void listSalespersons(int order) { }
	
	private static void countTransactionRecords(int lowerBound, int upperBound) { }
	
	private static void listManufacturers() { } 
	
	private static void showNMostPopularParts(int n) { }

	
}
