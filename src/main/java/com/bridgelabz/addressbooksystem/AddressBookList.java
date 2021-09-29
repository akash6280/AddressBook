package com.bridgelabz.addressbooksystem;


public class AddressBookList {
	public int bookID;
	public String bookName;
	
	
	public AddressBookList(int bookID, String bookName) {
		this.bookID = bookID;
		this.bookName = bookName;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Override
	public String toString() {
		return "AddressBookList [bookID=" + bookID + ", bookName=" + bookName + "]";
	}
	
}
