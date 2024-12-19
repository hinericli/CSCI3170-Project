package hi;

import java.io.*;
import java.sql.Date;

public class DataParser {
	protected static void parseData() {
		handleCategory();
		handleManufacturer();
		handlePart();
		handleSalesperson();
		handleTransaction();
	}
	
	private static void handleCategory() {
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/category.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar;	// to remove the tab from the reader, tab is not going to be used
			
			int cID;
			String cName;
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
				// --- cID ---
				while ((nextChar = (char) in.read()) != '\t') {
					sb.append(nextChar);
				}
				cID = Integer.parseInt(sb.toString());
				sb.setLength(0);	// reset string builder
				
				// --- cName ---
				cName = in.readLine();
				in.mark(1000);	
				
				System.out.println(cID + " " + cName);
			}
			in.close();
		} catch (Exception e) {
			System.err.println(e);	
		}
	}
	
	private static void handleManufacturer() {
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/manufacturer.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar; // tab: to remove the tab from the reader, tab is not going to be used

			int mID, mPhoneNumber;
			String mName, mAddress;
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
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
				
				in.mark(1000);	
				System.out.println(mID + " " + " " + mName + " " + mAddress + " " + mPhoneNumber);
			}
			in.close();
		} catch (Exception e) {
			System.err.println(e);	
		}
	}

	
	private static void handlePart() { 
		try {
			BufferedReader in = new BufferedReader (new FileReader(new File("./sample_data/part.txt")));
			StringBuilder sb = new StringBuilder(1000);
			char nextChar; // tab: to remove the tab from the reader, tab is not going to be used

			int pID, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity;
			String pName;
			
			in.mark(1000);	// mark the starting position
			while(in.readLine() != null) {
				in.reset();	// since the previous line in.readLine moves the pointer to the next line, this resets the point to the previous mark
				
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
				System.out.println(pID + " " + pName + " " + pPrice + " " + mID + " " + cID + " " + pWarrantyPeriod + " " + pAvailableQuantity);
			}
			in.close();
		} catch (Exception e) {
			System.err.println(e);	
		}
	}
	

	private static void handleSalesperson() { 
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


	private static void handleTransaction() { 
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
