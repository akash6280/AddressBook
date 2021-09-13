package com.bridgelabz.addressbooksystem;

import java.util.LinkedList;

public class AddressBook {
	    private String bookName;
		private LinkedList<ContactPerson> contactList;
		
		public AddressBook(String bookName, LinkedList<ContactPerson> contactList) {
			this.bookName = bookName;
			this.contactList = contactList;
		}
  
		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
    }

		public void setContactList(LinkedList<ContactPerson> contactList) {
			this.contactList = contactList;
		}

		public LinkedList<ContactPerson> getContactList() {
			return contactList;
		}
		@Override
		public String toString() {
			return "\nAddressBook [bookName=" + bookName + ", contactList=" + contactList + "]";
		}
	    
}