package com.bridgelabz.addressbooksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddressBookDBService {

	public List<ContactPerson> readData() {
		String sql = "select * from contact_detail;";
		List<ContactPerson> contactList = new ArrayList<>();
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String firstName = result.getString(1);
				String lastName = result.getString(2);
				String address=result.getString(3);
				String state=result.getString(4);
				String city=result.getString(5);
				long zip=result.getInt(6);
				long phoneNumber = result.getLong(7);
				String email = result.getString(8);
				contactList.add(new ContactPerson(firstName,lastName,address,city,state,zip,phoneNumber,email));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
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
}