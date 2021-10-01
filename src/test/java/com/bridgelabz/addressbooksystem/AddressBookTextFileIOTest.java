package com.bridgelabz.addressbooksystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;;

public class AddressBookTextFileIOTest {
	

	private AddressBookFacilitiesImpl addressbookservice;
	private List<ContactPerson> contactList;
	private ContactPerson contact1;
	private ContactPerson contact2;
	private long count;
	@Before
	public void initialise() {
		contact1 = new ContactPerson("agam","singh","i life","bangalore","karnataka",560100,876478736,"abc@gmail.com");
		contact2 = new ContactPerson("anubhav","singh","heritage villa","bangalore","karnataka",562125,987654212,"qwerty@gmail.com");
		contactList=new ArrayList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		addressbookservice=new AddressBookFacilitiesImpl();
		addressbookservice.addressList.add(new AddressBook("BOOK1",contactList));
		count=0;	
	}

	@Test
	public void whenDataReadFromTextFile_ShouldMatchCount() {	
		AddressBookTextFileService addressbookservice=new AddressBookTextFileService();
		List<String> contactList=addressbookservice.readDataFromTextFile();
		Assert.assertEquals(2,contactList.size());
	}
	
	@Test
	public void givenContactDetails_WhenAddedToTextFile_ShouldMatchCount() throws IOException {
		addressbookservice.writeData(IOEnum.FILE_IO);
		count = Files.lines(Paths.get(AddressBookTextFileService.ADDRESSBOOK_FILE_NAME_TXT)).count();
		Assert.assertEquals(2,count);		
	}
	
	@Test
	public void givenContactDetails_WhenAddedToTextFile_ShouldMisMatchCount() throws IOException {
		addressbookservice.writeData(IOEnum.FILE_IO);
		count = Files.lines(Paths.get(AddressBookTextFileService.ADDRESSBOOK_FILE_NAME_TXT)).count();
		Assert.assertNotEquals(3,count);		
	}
	
	
	
}
