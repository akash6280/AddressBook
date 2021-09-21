package com.bridgelabz.addressbooksystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookIOService {

    public static String ADDRESSBOOK_FILE_NAME = "addressbook-file.txt";

    public void writeData(List<ContactPerson> employeePayrollList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(ADDRESSBOOK_FILE_NAME), empBuffer.toString().getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void printData() {
    		try {
    			Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath()).forEach(System.out::println);
    		} catch(IOException e) {
    			e.printStackTrace(); 
    		}
    	}
	
	public void readDataFromFile() {
			
			List<String> addressBookList = new ArrayList<String>();
			
			try {
				Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath())
					.map(line -> line.trim())
					.forEach(System.out::println);
				
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

}
