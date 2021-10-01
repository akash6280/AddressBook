package com.bridgelabz.addressbooksystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


public class AddressBookJSONService {

    public static String ADDRESSBOOK_FILE_NAME_JSON = "addressbook-file.json";
	 
    public void writeDataToJSONFile(List<ContactPerson> contactList) {
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

