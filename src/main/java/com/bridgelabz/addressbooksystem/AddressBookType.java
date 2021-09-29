package com.bridgelabz.addressbooksystem;

public class AddressBookType {
	public int typeID;
	public String typeName;
	public AddressBookType(int typeID, String typeName) {
		this.typeID = typeID;
		this.typeName = typeName;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
