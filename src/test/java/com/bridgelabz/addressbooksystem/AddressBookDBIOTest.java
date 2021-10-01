package com.bridgelabz.addressbooksystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToLongFunction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AddressBookDBIOTest {	
	@Test
	public void givenContactInDB_WhenRetrieved_ShouldMatchContactCount(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		List<Contact> contactList =addressBookService.readContactData(IOEnum.DB_IO);
		Assert.assertEquals(6, contactList.size());
	}
	
	@Test
	public void givenNewEmailForContact_WhenUpdated_ShouldSyncWithDB() {
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		List<Contact> contactList = addressBookService.readContactData(IOEnum.DB_IO);
		addressBookService.updateEmail(897876374,"sisodia@gmail.com");
		boolean result = addressBookService.checkContactInSyncWithDB(897876374);
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenDateRange_WhenQueried_ShouldReturnContactCount(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		addressBookService.readContactData(IOEnum.DB_IO);
		LocalDate startDate = LocalDate.of(2019,01,01);
		LocalDate endDate = LocalDate.now(); 
		List<Contact> contactList = addressBookService.getContactsInADateRange(startDate,endDate);
		Assert.assertEquals(6, contactList.size());
	}
	
	@Test
	public void givenCity_WhenQueried_ShouldReturnContactInThatCity(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		List<Contact> contactList = addressBookService.getContactInCity("bangalore");
		Assert.assertEquals(2,contactList.size());
	}
}