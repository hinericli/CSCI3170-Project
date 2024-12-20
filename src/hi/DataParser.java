package hi;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hi.Entities.Category;
import hi.Entities.Manufacturer;
import hi.Entities.Part;

public class DataParser {
	protected static void parseAllData() {
		parseCategory();
		parseManufacturer();
		parsePart();
		parseSalesperson();
		parseTransaction();
	}
	
	protected static List<Category> parseCategory() {
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/category.txt")));
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

			for (Category category : categoryList) {
	            System.out.println("Category ID: " + category.getcID() + ", Category Name: " + category.getcName());
	        }
			
			return categoryList;
			
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}
	
	protected static List<Manufacturer> parseManufacturer() {
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/manufacturer.txt")));
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
			
			for (Manufacturer manufacturer : manufacturerList) {
	            System.out.println("Manufacturer ID: " + manufacturer.getmID());
	            System.out.println("Manufacturer Name: " + manufacturer.getmName());
	            System.out.println("Manufacturer Address: " + manufacturer.getmAddress());
	            System.out.println("Manufacturer Phone Number: " + manufacturer.getmPhoneNumber());
	            System.out.println();
	        }
			
			return manufacturerList;
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}

	
	protected static List<Part> parsePart() { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/part.txt")));
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
				
				System.out.println(pID + " " + pName + " " + pPrice + " " + mID + " " + cID + " " + pWarrantyPeriod + " " + pAvailableQuantity);
			}
			in.close();
			
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
			
			return partList;
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		return null;
	}
	

	protected static void parseSalesperson() { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/salesperson.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar;	// to remove the tab from the reader, tab is not going to be used
			
			int sID, sPhoneNumber, sExperience;
			String sName, sAddress;
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
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
				
				System.out.println(sID + " " + sName + " " + sAddress + " " + sPhoneNumber + " " + sExperience);
			}
			in.close();
		} catch (Exception e) {
			System.err.println(e);	
		}
	}


	protected static void parseTransaction() { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/transaction.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar;	// to remove the tab from the reader, tab is not going to be used
			
			int tID, pID, sID;
			String tDate;
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
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
				tDate = in.readLine();
				in.mark(1000);	
				
				System.out.println(tID + " " + pID + " " + sID + " " + tDate);
			}
			in.close();
		} catch (Exception e) {
			System.err.println(e);	
		}
		
		
	}
	
}
