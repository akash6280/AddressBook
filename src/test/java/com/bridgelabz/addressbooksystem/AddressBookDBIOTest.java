package com.bridgelabz.addressbooksystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bridgelabz.addressbooksystem.AddressBookFacilitiesImpl.IOService;

public class AddressBookDBIOTest {	
	@Test
	public void givenContactInDB_WhenRetrieved_ShouldMatchContactCount(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		List<ContactPerson> contactList =addressBookService.readContactData(IOService.DB_IO);
		Assert.assertEquals(6, contactList.size());
	}
	
	@Test
	public void givenNewPhoneNumberForContact_WhenUpdated_ShouldSyncWithDB() {
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		List<ContactPerson> contactList = addressBookService.readContactData(IOService.DB_IO);
		addressBookService.updateLastName("yash","yash@gmail.com");
		boolean result = addressBookService.checkContactInSyncWithDB("yash");
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenDateRange_WhenQueried_ShouldReturnContactCount(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		addressBookService.readContactData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2019,01,01);
		LocalDate endDate = LocalDate.now(); 
		List<ContactPerson> contactList = addressBookService.getContactsInADateRange(startDate,endDate);
		Assert.assertEquals(6, contactList.size());
	}
}