package com.bridgelabz.addressbooksystem;

public interface AddressBookFacilitiesIF {
	public ContactPerson createContacts();
	public void editContacts();
	public void deleteContacts();
	public void createAddressBook();
	public void modifyAddressBook();
	public void searchPersonAcrossCity(String cityName);
	public void searchPersonAcrossState(String stateName);
	public void countByCityAndState();
}
