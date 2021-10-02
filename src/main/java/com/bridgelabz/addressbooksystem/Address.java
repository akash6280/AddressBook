package com.bridgelabz.addressbooksystem;

public class Address {
	public String place;
	public String city;
	public String state;
	public long zip;
	
	public Address(String place, String city, String state, long zip) {
		super();
		this.place = place;
		this.city = city;
		this.state = state;
		this.zip = zip;
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
	public long getZip() {
		return zip;
	}
	public void setZip(long zip) {
		this.zip = zip;
	}
		
	
}
