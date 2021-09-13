package com.bridgelabz.addressbooksystem;

import java.util.Scanner;



public class AddressBookMain {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Welcome To Address Book Program");
		AddressBookFacilitiesImpl services=new AddressBookFacilitiesImpl();
		
		while(true) {
			System.out.println("Enter 1 for create adress book \n 2 for Modify address book \n 3 for display address book \n 4 to search person with city name \n 5 to search person with state name\n 6 to exit");
			int choice=scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: services.createAddressBook();
				    break;
				
			case 2: services.modifyAddressBook();	
				    break;
				
			case 3: services.displayAddressBook();	
				    break;
				
			case 4: System.out.println("Enter city name");
					String cityName=scanner.nextLine();
					services.searchPersonAcrossCity(cityName);	
				    break;
				
			case 5: System.out.println("Enter state name");
					String stateName=scanner.nextLine();
					services.searchPersonAcrossState(stateName);
					break;	
				
			case 6:	System.exit(0);
			
			default: System.out.println("wrong choice");
				break;
			}
		}
	}
}