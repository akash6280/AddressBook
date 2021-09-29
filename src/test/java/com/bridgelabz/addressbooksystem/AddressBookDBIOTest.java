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
}