package com.bridgelabz.addressbooksystem;

public class Address {
	public String id;
	public String place;
	public String city;
	public String state;
	public String zip;
	
	public Address(String id, String place, String city, String state, String zip) {
		super();
		this.id = id;
		this.place = place;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	
}
