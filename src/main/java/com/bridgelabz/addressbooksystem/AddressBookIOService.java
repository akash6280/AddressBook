package com.bridgelabz.addressbooksystem;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class AddressBookIOService {

    public static String ADDRESSBOOK_FILE_NAME_TXT = "addressbook-file.txt";
    public static String ADDRESSBOOK_FILE_NAME_CSV = "addressbook-file.csv";

    public void writeDataToTextFile(List<ContactPerson> contactList) {
        StringBuffer empBuffer = new StringBuffer();
        contactList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(ADDRESSBOOK_FILE_NAME_TXT), empBuffer.toString().getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void printData() {
    		try {
    			Files.lines(new File(ADDRESSBOOK_FILE_NAME_TXT).toPath()).forEach(System.out::println);
    		} catch(IOException e) {
    			e.printStackTrace(); 
    		}
    	}
	
	public void readDataFromTextFile() {
			
			try {
				Files.lines(new File(ADDRESSBOOK_FILE_NAME_TXT).toPath())
					.map(line -> line.trim())
					.forEach(System.out::println);
				
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	
	 public void writeDataToCsvFile(List<ContactPerson> contactList) {
		 try(
				 Writer writer =Files.newBufferedWriter(Paths.get(ADDRESSBOOK_FILE_NAME_CSV))
			){
				StatefulBeanToCsv<ContactPerson> beanToCsv= new StatefulBeanToCsvBuilder<ContactPerson>(writer)
															.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
															.build();

				beanToCsv.write(contactList);
			}
		 catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
	            e.printStackTrace();
	        }
		 
		 
	 }
	 
	 public void readFromCsv() {
			try {
		            Reader reader = Files.newBufferedReader(Paths.get(ADDRESSBOOK_FILE_NAME_CSV));
		            CSVReader csvReader = new CSVReader(reader);
		        	List<String[]> records = csvReader.readAll();
		        	for (String[] record : records) {
		        		System.out.println("Address: "+record[0]);
		        		System.out.println("City: "+record[1]);
		        		System.out.println("Email "+record[2]);
		        		System.out.println("First Name: "+record[3]);
		        		System.out.println("Last Name: "+record[4]);
		        		System.out.println("Phone Number: "+record[5]);
		        		System.out.println("State: "+record[6]);
		        		System.out.println("Zip"+record[7]);
		        	}
		        	
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	 

}
