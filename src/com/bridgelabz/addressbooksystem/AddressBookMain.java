package com.bridgelabz.addressbooksystem;

import java.util.Scanner;



public class AddressBookMain {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Welcome To Address Book Program");
		AddressBookFacilitiesImpl services=new AddressBookFacilitiesImpl();
		
		while(true) {
			System.out.println("Enter 1 for create adress book \n 2 for Modify address book \n 3 for display address book \n 4 to exit");
			int choice=scanner.nextInt();
			
			switch (choice) {
			case 1:services.createAddressBook();
				break;
				
			case 2:services.modifyAddressBook();	
				break;
				
			case 3:services.displayAddressBook();	
				break;
				
			case 4:	System.exit(0);
			
			default: System.out.println("wrong choice");
				break;
			}
		}
	}
}