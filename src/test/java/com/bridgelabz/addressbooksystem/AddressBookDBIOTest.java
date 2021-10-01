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

import com.bridgelabz.addressbooksystem.AddressBookException.ExceptionType;

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
	
	@Test
	public void givenState_WhenQueried_ShouldReturnContactInThatState(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		List<Contact> contactList = addressBookService.getContactInState("karnataka");
		Assert.assertEquals(2,contactList.size());
	}
	
	@Test
	public void givenCityIsEmpty_WhenQueried_ShouldThrowException(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		try {
			addressBookService.getContactInCity("");
		}catch(AddressBookException e) {
			Assert.assertEquals(ExceptionType.EMPTY_STRING,e.exceptionType);	
		}
	}
	
	@Test
	public void givenStateIsEmpty_WhenQueried_ShouldThrowException(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		try {
			addressBookService.getContactInState("");
		}catch(AddressBookException e) {
			Assert.assertEquals(ExceptionType.EMPTY_STRING,e.exceptionType);	
		}
	
	}
	
	@Test
	public void givenCityIsNull_WhenQueried_ShouldThrowException(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		try {
			addressBookService.getContactInCity(null);
		}catch(AddressBookException e) {
			Assert.assertEquals(ExceptionType.NULL_STRING,e.exceptionType);	
		}
	}
	
	@Test
	public void givenStateIsNull_WhenQueried_ShouldThrowException(){
		AddressBookFacilitiesImpl addressBookService=new AddressBookFacilitiesImpl();
		try {
			addressBookService.getContactInState(null);
		}catch(AddressBookException e) {
			Assert.assertEquals(ExceptionType.NULL_STRING,e.exceptionType);	
		}
	}
}