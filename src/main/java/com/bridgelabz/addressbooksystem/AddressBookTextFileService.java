package com.bridgelabz.addressbooksystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class AddressBookTextFileService {

    public static String ADDRESSBOOK_FILE_NAME_TXT = "addressbook-file.txt";

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


}

