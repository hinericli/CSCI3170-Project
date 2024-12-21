package hi;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import hi.Entities.Category;
import hi.Entities.Manufacturer;
import hi.Entities.Part;
import hi.Entities.Salesperson;
import hi.Entities.Transaction;

public class DataParser {
	protected static void parseAllData() {
		String folderPath = "sample_data";
		parseCategory(folderPath);
		parseManufacturer(folderPath);
		parsePart(folderPath);
		parseSalesperson(folderPath);
		parseTransaction(folderPath);
	}
	
	protected static List<Category> parseCategory(String folderPath) {
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./" + folderPath + "/category.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar;	// to remove the tab from the reader, tab is not going to be used
			List<Category> categoryList = new ArrayList<Category>();

			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
				int cID;
				String cName;
				
				// --- cID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				cID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- cName ---
				cName = in.readLine();
				in.mark(1000);	
				
				Category category = new Category(cID, cName);
				categoryList.add(category);	
			}
			in.close();

			/*
			for (Category category : categoryList) {
	            System.out.println("Category ID: " + category.getcID() + ", Category Name: " + category.getcName());
	        }*/
			
			return categoryList;
			
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}
	
	protected static List<Manufacturer> parseManufacturer(String folderPath) {
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./" + folderPath + "/manufacturer.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar; // tab: to remove the tab from the reader, tab is not going to be used
			List<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
				int mID, mPhoneNumber;
				String mName, mAddress;
				
				// --- mID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				mID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- mName ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				mName = sb.toString();
				sb.setLength(0);	// reset string builder
				
				// --- mAddress ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				mAddress = sb.toString();
				sb.setLength(0);	// reset string builder
				
				// --- mPhoneNumber ---
				mPhoneNumber = Integer.parseInt(in.readLine());
				
				Manufacturer manufacturer = new Manufacturer(mID, mName, mAddress, mPhoneNumber);
				manufacturerList.add(manufacturer);
				
				in.mark(1000);	
			}
			in.close();
			
			/*
			for (Manufacturer manufacturer : manufacturerList) {
	            System.out.println("Manufacturer ID: " + manufacturer.getmID());
	            System.out.println("Manufacturer Name: " + manufacturer.getmName());
	            System.out.println("Manufacturer Address: " + manufacturer.getmAddress());
	            System.out.println("Manufacturer Phone Number: " + manufacturer.getmPhoneNumber());
	            System.out.println();
	        }*/
			
			return manufacturerList;
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}

	
	protected static List<Part> parsePart(String folderPath) { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./" + folderPath + "/part.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar; // tab: to remove the tab from the reader, tab is not going to be used
			List<Part> partList = new ArrayList<>();
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
				int pID, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity;
				String pName;
				
				// --- pID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				pID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- pName ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				pName = sb.toString();
				sb.setLength(0);	// reset string builder
				
				// --- pPrice ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				pPrice = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- mID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				mID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- cID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				cID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- pWarrantyPeriod ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				pWarrantyPeriod = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- pAvailableQuantity ---
				pAvailableQuantity = Integer.parseInt(in.readLine());
				
				in.mark(1000);	
				
				Part part = new Part(pID, pName, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity);
				partList.add(part);
			}
			in.close();
			
			/*
			for (Part part : partList) {
		            System.out.println("Part ID: " + part.getpID());
		            System.out.println("Part Name: " + part.getpName());
		            System.out.println("Part Price: $" + part.getpPrice());
		            System.out.println("Manufacturer ID: " + part.getmID());
		            System.out.println("Category ID: " + part.getcID());
		            System.out.println("Warranty Period: " + part.getpWarrantyPeriod() + " months");
		            System.out.println("Available Quantity: " + part.getpAvailableQuantity());
		            System.out.println();
	    	}
	    	*/
			
			return partList;
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}
	

	protected static List<Salesperson> parseSalesperson(String folderPath) { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./" + folderPath + "/salesperson.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar;	// to remove the tab from the reader, tab is not going to be used
			
			List<Salesperson> salespersonList = new ArrayList<>();
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
				int sID, sPhoneNumber, sExperience;
				String sName, sAddress;
				// --- sID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				sID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- sName ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				sName = sb.toString();
				sb.setLength(0);
				
				// --- sAddress ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				sAddress = sb.toString();
				sb.setLength(0); // reset string builder
				
				// --- sPhoneNumber ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				sPhoneNumber = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- sExperience ---
				sExperience = Integer.parseInt(in.readLine());
				in.mark(1000);	
				
				Salesperson salesperson = new Salesperson(sID, sName, sAddress, sPhoneNumber, sExperience);
				salespersonList.add(salesperson);
			}
			in.close();
			
			/*
			for (Salesperson salesperson : salespersonList) {
	            System.out.println("Salesperson ID: " + salesperson.getsID());
	            System.out.println("Salesperson Name: " + salesperson.getsName());
	            System.out.println("Salesperson Address: " + salesperson.getsAddress());
	            System.out.println("Phone Number: " + salesperson.getsPhoneNumber());
	            System.out.println("Experience: " + salesperson.getsExperience() + " years");
	            System.out.println();
			}
			*/
			
			return salespersonList;
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}


	protected static List<Transaction> parseTransaction(String folderPath) { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./" + folderPath + "/transaction.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar;	// to remove the tab from the reader, tab is not going to be used
			
	        List<Transaction> transactionList = new ArrayList<>();
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
				int tID, pID, sID;
				Date tDate;
				
				// --- tID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				tID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- pID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				pID = Integer.parseInt(sb.toString());
				sb.setLength(0);
				
				// --- sID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				sID = Integer.parseInt(sb.toString());
				sb.setLength(0); // reset string builder
				
				// --- tDate ---
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date utilDate = sdf.parse(in.readLine());
		        tDate = new java.sql.Date(utilDate.getTime());

				in.mark(1000);	
				
				Transaction transaction = new Transaction(tID, pID, sID, tDate);
				transactionList.add(transaction);
			}
			in.close();
			
			/*
			for (Transaction transaction : transactionList) {
	            System.out.println("Transaction ID: " + transaction.gettID());
	            System.out.println("Part ID: " + transaction.getpID());
	            System.out.println("Salesperson ID: " + transaction.getsID());
	            System.out.println("Transaction Date: " + transaction.gettDate());
	            System.out.println();
	        }
	        */
			
			return transactionList;
		} catch (Exception e) {
			System.err.println(e);	
		}
		return null;
	}
	
}
