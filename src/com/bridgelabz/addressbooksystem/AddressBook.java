package com.bridgelabz.addressbooksystem;

public class AddressBook {
	    String name;
		private  ContactPerson[] contactList;
		
	    AddressBook(String name,ContactPerson[] contactList) {
			this.contactList=contactList;
			this.name=name;
		}

		public ContactPerson[] getContactList() {
			return contactList;
		}

		public void setContactList(ContactPerson[] contactList) {
			this.contactList = contactList;
		}

	    
}