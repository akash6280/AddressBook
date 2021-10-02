package com.bridgelabz.addressbooksystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.addressbooksystem.AddressBookException.ExceptionType;

public class AddressBookDBService {
	private PreparedStatement addressBookDataStatement;
	private PreparedStatement addressBookDataStatementToUpdate;
	private PreparedStatement addressBookDataStatementForDateRange;
	private PreparedStatement addressBookDataStatementForContactInCity;
	private PreparedStatement addressBookDataStatementForContactInState;
	private static AddressBookDBService addressBookDBService;
	
	public static AddressBookDBService getInstance(){
		if (addressBookDBService == null)
			addressBookDBService=new AddressBookDBService();
		return addressBookDBService;
	}
	
	private Connection getConnection() throws SQLException {
		String jdbcURL="jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
		String userName="root";
		String password="Welcome@123";
		Connection connection;
		System.out.println("Connecting to database"+ jdbcURL);
		connection=DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("Connection is successfull"+ connection);
		return connection;
	}

	public List<Contact> readData() {
		String sql = "select * from contact,address where contact.contactID=address.contactID;";
		List<Contact> contactList = new ArrayList<>();
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList=this.getContactData(result);
		} catch (SQLException e) {
			throw new AddressBookException(ExceptionType.INCORRECT_QUERY,"Cannot execute");
		}
		return contactList;
	}
	
	public Contact addContactToAddressBook(Contact contact, Address address, AddressBookType addressBookType,AddressBook addressBook) {
		int contactID = -1;
		int addressID=-1;
		
		Connection connection = null;
		try{
			connection = this.getConnection();
			connection.setAutoCommit(false);
		}catch (SQLException e) {
			throw new AddressBookException(ExceptionType.CONNECTION_FAILED,"Could not connect");
		}
		
		try (Statement statement = connection.createStatement()){
			String sql = String.format("select * from addressbook where addressbook_id = '%s'",addressBook.getBookId());
			ResultSet result = statement.executeQuery(sql);
			if(result.next() == false) {
				throw new AddressBookException(ExceptionType.INCORRECT_QUERY, addressBook.getBookName()+"is not present");
			}
		}catch(SQLException e) {
			throw new AddressBookException(ExceptionType.INCORRECT_QUERY, "query execution failed");
		}
		
		try (Statement statement = connection.createStatement()){
			String sql = String.format("select * from addressbook_type where addressbook_type_id = '%s'",addressBookType.getTypeID());
			ResultSet result = statement.executeQuery(sql);
			if(result.next() == false) {
				throw new AddressBookException(ExceptionType.INCORRECT_QUERY,addressBookType.getTypeName()+"is not present");
			}
		}catch(SQLException e) {
			throw new AddressBookException(ExceptionType.INCORRECT_QUERY, "query execution failed");
		}
		
		
		try (Statement statement = connection.createStatement()){

			String sql = String.format("insert into contact (firstName,lastName,phoneNumber,email,startDate) VALUES ('%s', '%s', '%s','%s','%s');",
								contact.getFirstName(),contact.getLastName(),contact.getPhoneNumber(),contact.getEmail(),Date.valueOf(contact.getStartDate()));

			int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
			if(rowAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) contactID = resultSet.getInt(1);
			}  
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try(Statement statement = connection.createStatement()){

			String sql = String.format("insert into address (contactID,place,city,state,zip)"
					     + "values ('%s', '%s', '%s', '%s','%s')",contactID,address.getPlace(),address.getCity(),address.getState(),address.getZip());
			int rowAffected = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			if(rowAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) addressID = resultSet.getInt(1);
			}  		
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try (Statement statement = connection.createStatement()){

			String sql = String.format("insert into addressbook_contact (addressbook_id,ContactID) VALUES ('%s', '%s');",addressBook.getBookId(),contactID);
			statement.executeUpdate(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try (Statement statement = connection.createStatement()){

			String sql = String.format("insert into addressbookTypeContact(addressbook_id,addressbook_type_id,ContactID)"
				     	+"VALUES ('%s', '%s',%s);",	addressBook.getBookId(),addressBookType.getTypeID(),contactID);
			statement.executeUpdate(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		
		try {
			connection.commit();
		}catch (SQLException e) {
				e.printStackTrace();
		}
			finally {
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) { 
					e.printStackTrace();
				}
		}
		return contact;
	}

	public int updateContactData(long phoneNumber, String email) {
			return this.updateContactDataUsingPreparedStatement(phoneNumber,email);
	}
	
	private int updateContactDataUsingPreparedStatement(long phoneNumber,String email) {
			if(email.isEmpty())
				throw new AddressBookException(ExceptionType.EMPTY_STRING,"Entered empty string");
	        if (this.addressBookDataStatementToUpdate == null)
	            this.prepareStatementForUpdatingContactData();
	        try {
	        	addressBookDataStatementToUpdate.setString(1,email);
	        	addressBookDataStatementToUpdate.setLong(2,phoneNumber);
	            return addressBookDataStatementToUpdate.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch(NullPointerException e) {
	        	throw new AddressBookException(ExceptionType.NULL_STRING,"Entered null string");
	        }
			return 0;
	}
	
	private void prepareStatementForUpdatingContactData() {
        try {
            Connection connection = this.getConnection();
            String sql ="update contact set email=? where phoneNumber =?";
            addressBookDataStatementToUpdate = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new AddressBookException(ExceptionType.UPDATE_FAILED,"Update failed");
        }
    }


	public List<Contact> getContactData(long phoneNumber) {
		List<Contact> contactList = null;
		if (this.addressBookDataStatement == null)
			this.prepareStatementForContactData();
		try {
			addressBookDataStatement.setLong(1, phoneNumber);
			ResultSet resultSet = addressBookDataStatement.executeQuery();
			contactList = this.getContactData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;	
	}
	
	private void prepareStatementForContactData() {
		try {
			Connection connection = this.getConnection();
			String sql=	"SELECT	* FROM contact l WHERE phoneNumber = ?";
			addressBookDataStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private List<Contact> getContactData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try{
			while(resultSet.next()) {
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				String email=resultSet.getString(5);
				LocalDate startDate=resultSet.getDate(6).toLocalDate();
				contactList.add(new Contact(firstName,lastName,phoneNumber,email,startDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public List<Contact> getContactBetweenDateRange(LocalDate startDate, LocalDate endDate) {
		List<Contact> contactList = null;
		if (this.addressBookDataStatementForDateRange == null)
			this.prepareStatementToGetContactBetweenDateRange();
		try {
			addressBookDataStatementForDateRange.setDate(1,java.sql.Date.valueOf(startDate));
			addressBookDataStatementForDateRange.setDate(2,java.sql.Date.valueOf(endDate));
			ResultSet resultSet = addressBookDataStatementForDateRange.executeQuery();
			contactList = this.getContactData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;	
	}
	
	private void prepareStatementToGetContactBetweenDateRange() {
		try {
			Connection connection = this.getConnection();
			String sql=	"select * from contact where startDate between ? and ?";
			addressBookDataStatementForDateRange = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public  List<Contact> getContactInCity(String city) {
		if(city.isBlank())
			throw new AddressBookException(ExceptionType.EMPTY_STRING,"Entered empty string");
		List<Contact> contactList = null;
		if (this.addressBookDataStatementForContactInCity == null)
			this.prepareStatementToGetContactInACity();
		try {
			addressBookDataStatementForContactInCity.setString(1,city);
			ResultSet resultSet = addressBookDataStatementForContactInCity.executeQuery();
			contactList = this.getContactData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			throw new AddressBookException(ExceptionType.NULL_STRING,"Entered null string");
		}
		return contactList;	
	}
	
	private void prepareStatementToGetContactInACity() {
		try {
			Connection connection = this.getConnection();
			String sql=	"select * from contact c,address a where c.contactID=a.contactID and city=?";
			addressBookDataStatementForContactInCity = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public  List<Contact> getContactInState(String state) {
		if(state.isBlank())
			throw new AddressBookException(ExceptionType.EMPTY_STRING,"Entered empty string");
		List<Contact> contactList = null;
		if (this.addressBookDataStatementForContactInState == null)
			this.prepareStatementToGetContactInAState();
		try {
			addressBookDataStatementForContactInState.setString(1,state);
			ResultSet resultSet = addressBookDataStatementForContactInState.executeQuery();
			contactList = this.getContactData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			throw new AddressBookException(ExceptionType.NULL_STRING,"Entered null string");
		}
		return contactList;	
	}
	
	private void prepareStatementToGetContactInAState() {
		try {
			Connection connection = this.getConnection();
			String sql=	"select * from contact c,address a where c.contactID=a.contactID and state=?";
			addressBookDataStatementForContactInState = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}