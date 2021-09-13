package com.bridgelabz.addressbooksystem;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
public class AddressBookFacilitiesImpl implements AddressBookFacilitiesIF {
	
	Scanner scanner=new Scanner(System.in);
	LinkedList<AddressBook> addressList=new LinkedList<>();
	HashMap<String, LinkedList<ContactPerson>> contactsByCity;
	HashMap<String, LinkedList<ContactPerson>> contactsByState;
	public void createAddressBook() {
		
		System.out.println("Enter book name");
		String bookName=scanner.nextLine();
		
		LinkedList<ContactPerson> contactList=new LinkedList<>();
		System.out.println("Enter number of contact you want to create");
		int numberOfContact=scanner.nextInt();
		
		scanner.nextLine();
		
		for(int index=0;index<numberOfContact;index++) {
			
			ContactPerson contactPerson=createContacts();
             
			boolean flag=false;
			for(int index1=0; index1< contactList.size();index1++) {
				if(contactPerson.getFirstName().equals(contactList.get(index1).getFirstName())) {
					System.out.println("Duplicate entry");
					flag=true;
					break;
				}
			}
			if(flag)
				continue;
			else
				contactList.add(contactPerson);
		}
				
			addressList.add(new AddressBook(bookName,contactList));
	}
	
	public  ContactPerson createContacts() {
			
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
			
			ContactPerson contactPerson=new  ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber, email);
			
			if(contactsByCity.get(city) == null) {
				contactsByCity.put(city,new LinkedList<>());
			}
			contactsByCity.get(city).add(contactPerson);
			
			if(contactsByCity.get(state) == null) {
				contactsByCity.put(state,new LinkedList<>());
			}
			contactsByCity.get(state).add(contactPerson);
			return contactPerson;
	}
	
	public void editContacts() {
			System.out.println("Enter book name in which you want to make changes");
			String bookName=scanner.next();
			System.out.println("Enter phone number of person");
			Long phoneNumber=scanner.nextLong();
			scanner.nextLine();
			
			for(AddressBook addressBook:addressList) {
				
				for(int index1=0; index1< addressList.size();index1++) {
					if(bookName.equals(addressList.get(index1).getBookName())) {
						
						for(ContactPerson detail:addressBook.getContactList()) {
							if(phoneNumber.equals(detail.getPhoneNumber())){
										
								System.out.println("Enter new first name:");
								detail.setFirstName(scanner.nextLine());
								
								System.out.println("Enter new last name:");
								detail.setLastName(scanner.nextLine());
								
								System.out.println("Enter new address:");
								detail.setAddress(scanner.nextLine());
								
								System.out.println("Enter new city:");
								detail.setCity(scanner.nextLine());
								
								System.out.println("Enter new state:");
								detail.setState(scanner.nextLine());
								
								System.out.println("Enter new zip");
								detail.setZip(scanner.nextInt());
								
								System.out.println("Enter new phone number");
								detail.setPhoneNumber(scanner.nextLong());
								
								scanner.nextLine();
								
								System.out.println("Enter new email");
								detail.setEmail(scanner.nextLine());
							}
						}
					}
				}
			}
	}
	
	
	public void deleteContacts() {
		System.out.println("Enter book name in which you want to make changes");
		String bookName=scanner.nextLine();
		System.out.println("Enter the phone number of contact to be deleted ");
		long phoneNumber=scanner.nextLong();
		int position=0;
		for(AddressBook addressBook:addressList) {
			++position;
				if(bookName.equals(addressBook.getBookName())) {
					for(ContactPerson detail:addressBook.getContactList()) {
						if(phoneNumber==(detail.getPhoneNumber())){
							addressList.get(position).getContactList().remove(detail);
						}
						
					}
				}
		}
	}
	public void displayAddressBook() {
		for(AddressBook addressBook:addressList) {
			System.out.print(addressBook);
			}
	}
	
	public void modifyAddressBook() {
		AddressBookFacilitiesImpl service=new AddressBookFacilitiesImpl();
		System.out.println("Enter Book name you want to update ");
		String bookName=scanner.nextLine();
		System.out.println("Enter 1 for add 2 for delete 3 for edit");
		int choice=scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1: ContactPerson contactPerson=createContacts();
							for(int index=0; index< addressList.size();index++) {
								if(bookName.equals(addressList.get(index).getBookName())) {
									addressList.get(index).getContactList().add(contactPerson);
								}
							}
			break;
			
		case 2: service.deleteContacts();
			break;	
			
		case 3:	service.editContacts();
			break;

		default: System.out.println("wrong choice");
			break;
		}
	}
		
	public void searchPersonAcrossState(String stateName) {
		for(AddressBook addressBook:addressList) {
			for(ContactPerson detail:addressBook.getContactList()) {
				if(stateName.equals(detail.getState()))
					System.out.println(detail);
			}
		}
	}
	
	public void searchPersonAcrossCity(String cityName) {
		for(AddressBook addressBook:addressList) {
			for(ContactPerson detail:addressBook.getContactList()) {
				if(cityName.equals(detail.getState()))
					System.out.println(detail);
			}
		}
	}
	
	public void countByCityAndState() {
		int countByCity=0;
		int countByState=0;
		for (HashMap.Entry<String, LinkedList<ContactPerson>> entry : contactsByCity.entrySet()) {
			countByCity+=contactsByCity.get(entry.getKey()).size();
		}
		for (HashMap.Entry<String, LinkedList<ContactPerson>> entry : contactsByState.entrySet()) {
			countByState+=contactsByState.get(entry.getKey()).size();
		}
		System.out.println("Count by state"+countByCity);
		System.out.println("Count by state"+countByState);
	}
}
					
			
