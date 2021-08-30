package com.bridgelabz.addressbooksystem;
import java.util.Scanner;
public class AddressBookFacilitiesImpl implements AddressBookFacilitiesIF {
	
	Scanner scanner=new Scanner(System.in);
	public  void createContact() {
		ContactPerson[] contactList;
		System.out.println("Enter number of contact you want to create");
		int numberOfContact=scanner.nextInt();
		contactList=new ContactPerson[numberOfContact];
		scanner.nextLine();
		
		for(int index=0;index<numberOfContact;index++) {
			
			System.out.println("Enter first name: ");
			String firstName = scanner.nextLine();
			System.out.println("Enter last name: ");
			String lastName = scanner.nextLine();
			System.out.println("Enter address: ");
			String address = scanner.nextLine();
			System.out.println("Enter city: ");
			String city = scanner.nextLine();
			System.out.println("Enter state: ");
			String state = scanner.nextLine();
			System.out.println("Enter Zip Code: ");
			int zip = scanner.nextInt();
			System.out.println("Enter Phone Number: ");
			long phoneNumber = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Enter email id: ");
			String email = scanner.nextLine();
			
			contactList[index]=new ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber, email);
		}
		    
			AddressBook addressbook=new AddressBook(contactList);
	}
	
}
