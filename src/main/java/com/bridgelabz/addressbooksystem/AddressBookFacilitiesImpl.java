package com.bridgelabz.addressbooksystem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddressBookFacilitiesImpl implements AddressBookFacilitiesIF {
	Scanner scanner=new Scanner(System.in);
	List<AddressBook> addressList=new ArrayList<>();
	HashMap<String, LinkedList<ContactPerson>> contactsByCity=new HashMap<>();;
	HashMap<String, LinkedList<ContactPerson>> contactsByState=new HashMap<>();;
	List<Contact> contactList=new ArrayList<>();
	

	public void createAddressBook() {
		
		System.out.println("Enter book name");
		String bookName=scanner.nextLine();
		
		LinkedList<ContactPerson> contactList=new LinkedList<>();
		System.out.println("Enter number of contact you want to create");
		int numberOfContact=scanner.nextInt();
		
		scanner.nextLine();
		
		for(int index=0;index<numberOfContact;index++) {
			
			ContactPerson contactPerson=createContacts();
			Predicate<ContactPerson> isPresentOrNot = c->c.getFirstName().equals(contactPerson.getFirstName());
			boolean find = contactList.stream().anyMatch(isPresentOrNot);
			if(find) {
				System.out.println("Duplicate entry");
				continue;
			}
				
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
			
			if(!contactsByCity.containsKey(city)) {
				contactsByCity.put(city,new LinkedList<>());
			}
			contactsByCity.get(city).add(contactPerson);
			
			if(!contactsByState.containsKey(state)) {
				contactsByState.put(state,new LinkedList<>());
			}
			contactsByState.get(state).add(contactPerson);
			
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
									Predicate	<ContactPerson> isPresentOrNot = c->c.getFirstName().equals(contactPerson.getFirstName());
									boolean find = addressList.get(index).getContactList().stream().anyMatch(isPresentOrNot);
									if(find) 
										System.out.println("Duplicate entry");
									else
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
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().filter(contact->contact.getState().equals(stateName))
		.filter(contact->contact.getState().equals(stateName))
		.forEach(System.out::println));
	}
	
	public void searchPersonAcrossCity(String cityName) {
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().filter(contact->contact.getCity().equals(cityName))
		.forEach(System.out::println));
	}
	
	public void countByCity(String cityName) {
		
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream()
		.filter(contact->contact.getCity().equals(cityName))
		.count());
	}
	public void countByState(String stateName) {
		
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream()
		.filter(contact->contact.getState().equals(stateName))
		.count());
	}


	public void sortByName() {
			addressList.stream()
			.forEach(addressBook->addressBook.getContactList()
			.stream()
			.sorted((contact1, contact2) -> contact1.getFirstName().compareTo(contact2.getFirstName()))
			.forEach(System.out::println));
		
	}
	 
	public void sortByCity() {
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream()
		.sorted((contact1, contact2) -> contact1.getCity().compareTo(contact2.getCity()))
		.forEach(System.out::println));
		
	}
	
	public void sortByState() {
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream()
		.sorted((contact1, contact2) -> contact1.getState().compareTo(contact2.getState()))
		.forEach(System.out::println));
		
	}
	public void sortByZip() {
		addressList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream()
		.sorted((contact1, contact2) -> String.valueOf(contact1.getZip()).compareTo(String.valueOf(contact2.getZip())))
		.forEach(System.out::println));
		
	}
	public void writeData(IOEnum ioType) {
		if(ioType.equals(IOEnum.CONSOLE_IO))
			System.out.println(addressList.get(0).getContactList());
		
		if(ioType.equals(IOEnum.FILE_IO))
			new AddressBookTextFileService().writeDataToTextFile(addressList.get(0).getContactList());
		
		if(ioType.equals(IOEnum.CSV_IO))
			new AddressBookCSVService().writeDataToCsvFile(addressList.get(0).getContactList());
		
		if(ioType.equals(IOEnum.JSON_IO))
			new AddressBookJSONService().writeDataToJSONFile(addressList.get(0).getContactList());
			
	}
	
	public void readData(IOEnum ioType) {
		if(ioType.equals(IOEnum.CONSOLE_IO))
			new AddressBookFacilitiesImpl().createAddressBook();
		
		if(ioType.equals(IOEnum.FILE_IO))
			new AddressBookTextFileService().readDataFromTextFile();
		
		if(ioType.equals(IOEnum.CSV_IO))
			new AddressBookCSVService().readDataFromCsvFile();
		
		if(ioType.equals(IOEnum.JSON_IO))
			new AddressBookJSONService().readDataFromJSONFile();
			
	}
	
	public List<Contact> readContactData(IOEnum ioType) {
        if(ioType.equals(IOEnum.DB_IO))
    		this.contactList = new AddressBookDBService().readData();
        
    		return this.contactList;
    	}

	public void updateEmail(long phoneNumber, String email) {
		int result =new AddressBookDBService().updateContactData(phoneNumber,email);
		if(result == 0) return;
		Contact contact = this.getContactData(phoneNumber);
		if(contact != null) contact.setEmail(email);
		}

	private Contact getContactData(long phoneNumber) {
		return this.contactList.stream()
				.filter(contact->contact.getPhoneNumber()==(phoneNumber))
				.findFirst()
				.orElse(null);
	}

	public boolean checkContactInSyncWithDB(long phoneNumber) {
		List<Contact> contactDataList = new AddressBookDBService().getContactData(phoneNumber);
		return contactDataList.get(0).equals(getContactData(phoneNumber));
	}	
	 

	public List<Contact> getContactsInADateRange(LocalDate startDate, LocalDate endDate) {
		return new AddressBookDBService().getContactBetweenDateRange(startDate, endDate); 
	}


	public List<Contact> getContactInCity(String city) {
		return new AddressBookDBService().getContactInCity(city);
	}

	public List<Contact> getContactInState(String state) {
		return new AddressBookDBService().getContactInState(state);
	}

	public void addContactToAddressBook(Contact contact, Address address, AddressBookType addressBookType,AddressBook addressBook) {
		this.contactList.add(new AddressBookDBService().addContactToAddressBook(contact,address,addressBookType,addressBook));
		
	}

}
					
			
