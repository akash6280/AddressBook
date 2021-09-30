package com.bridgelabz.addressbooksystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class AddressBookFileService {

    public static String ADDRESSBOOK_FILE_NAME_TXT = "addressbook-file.txt";
    public static String ADDRESSBOOK_FILE_NAME_CSV = "addressbook-file.csv";
    public static String ADDRESSBOOK_FILE_NAME_JSON = "addressbook-file.json";

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
	
	public List<String> readDataFromTextFile() {
		    List<String> contactList = new ArrayList<>();
			try {
				Files.lines(new File(ADDRESSBOOK_FILE_NAME_TXT).toPath())
					.map(contact -> contact.trim())
					.forEach(contact -> {
						System.out.println(contact);
						contactList.add(contact);
					});

				
			}
			catch(IOException e){
				e.printStackTrace();
			}
			return contactList;
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
	 
	 public List<String[]> readDataFromCsvFile() {
		 List<String[]> records = null;
			try {
		            Reader reader = Files.newBufferedReader(Paths.get(ADDRESSBOOK_FILE_NAME_CSV));
		            CSVReader csvReader = new CSVReader(reader);
		        	records = csvReader.readAll();
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
			return records;
		}
	 
	 public void writeDatatoJSONFile(List<ContactPerson> contactList) {
			FileWriter writer;
			try {
				writer = new FileWriter(ADDRESSBOOK_FILE_NAME_JSON);
				writer.write(new Gson().toJson(contactList));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	 
	
	 public List<ContactPerson> readDataFromJSONFile(){
		 	try {
	            Gson gson = new Gson();
	            BufferedReader br = new BufferedReader(new FileReader(ADDRESSBOOK_FILE_NAME_JSON));
	            ContactPerson[] contactsFile = gson.fromJson(br, ContactPerson[].class);
	            List<ContactPerson> contactList = Arrays.asList(contactsFile);
	            for (ContactPerson contact  : contactList) {
	        		System.out.println("Address: "+contact.getAddress());
	        		System.out.println("City: "+contact.getCity());
	        		System.out.println("Email "+contact.getEmail());
	        		System.out.println("First Name: "+contact.getFirstName());
	        		System.out.println("Last Name: "+contact.getLastName());
	        		System.out.println("Phone Number: "+contact.getPhoneNumber());
	        		System.out.println("State: "+contact.getState());
	        		System.out.println("Zip:" +contact.getZip());
	        	}
	            return contactList;
	        }catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	 }
}

