package hi;

public class Administrator {
	protected static void performOperation(int operationType) {
		switch (operationType) {
			case 1:
				createTables();
			case 2:
				deleteTables();
			case 3:
				loadFromDataFile();
			case 4: 
				showTableContent();
		}
	}
	
	private static void createTables() { }
	
	private static void deleteTables() { }
	
	private static void loadFromDataFile() {}
	
	private static void showTableContent() {}
	
}
