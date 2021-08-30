package com.bridgelabz.addressbooksystem;

public class AddressBook {
	
		private  ContactPerson[] contactList;
		
	    AddressBook(ContactPerson[] contactList) {
			this.contactList=contactList;
		}

		public ContactPerson[] getContactList() {
			return contactList;
		}

		public void setContactList(ContactPerson[] contactList) {
			this.contactList = contactList;
		}

	    
}