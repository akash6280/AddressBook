package com.bridgelabz.addressbooksystem;

import java.util.Scanner;
import com.bridgelabz.addressbooksystem.AddressBookFacilitiesImpl.IOService;



public class AddressBookMain {
	public static String cityName,stateName;
	public static int option;;
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Welcome To Address Book Program");
		AddressBookFacilitiesImpl services=new AddressBookFacilitiesImpl();
		
		while(true) {
			System.out.println("Enter 1 For create adress book \n 2 For Modify address book \n 3 For display address book \n 4 To search person  \n 5 To view count \n 6 Perform File IO  operations b \n 7 To exit");
			int choice=scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: services.createAddressBook();
				    break;
				
			case 2: services.modifyAddressBook();	
				    break;
				
			case 3: services.displayAddressBook();	
				    break;
				
			case 4: System.out.println("Enter 1 to search by city name \n 2 to search by state name");
					option=scanner.nextInt();
					if(option==1){
							cityName=scanner.next();
							services.searchPersonAcrossCity(cityName);
						}
					else{
							stateName=scanner.next();
							services.searchPersonAcrossState(stateName);
						}
				    break;
				
			case 5: System.out.println("Enter 1 to count by city name \n 2 to count by state name");
					option=scanner.nextInt();
					if(option==1){
						cityName=scanner.next();
						services.countByCity(cityName);
					}
					else{
						stateName=scanner.next();
						services.countByState(stateName);
					}
					break;
					
			case 6: System.out.println("Enter 1 to perform write to file \n 2 to read from file");
					option=scanner.nextInt();
					if(option==1) {
						services.writeDataToFile(IOService.FILE_IO);
					}
					else {
						services.readDataFromFile();;
					}
					break;
			case 7: System.exit(0);
			
			default: System.out.println("wrong choice");
				break;
			}
		}
	}
}