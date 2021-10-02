package com.bridgelabz.addressbooksystem;

import java.util.LinkedList;
import java.util.List;

public class AddressBook {
		private int bookId;
	    private String bookName;
		private List<ContactPerson> contactList;
		
		public AddressBook(String bookName, List<ContactPerson> contactList) {
			this.bookName = bookName;
			this.contactList = contactList;
		}
		public AddressBook(int bookId, String bookName) {
			this.bookId = bookId;
			this.bookName = bookName;
		}
		public int getBookId() {
			return bookId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public void setContactList(List<ContactPerson> contactList) {
			this.contactList = contactList;
		}

		public List<ContactPerson> getContactList() {
			return contactList;
		}
		@Override
		public String toString() {
			return "\nAddressBook [bookName=" + bookName + ", contactList=" + contactList + "]";
		}
	    
}