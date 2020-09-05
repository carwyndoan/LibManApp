package lib.man.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibraryMember {
	private StringProperty memberID;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty phone;

	public LibraryMember() {
		super();
		this.memberID 	= new SimpleStringProperty("");
		this.firstName 	= new SimpleStringProperty("");
		this.lastName 	= new SimpleStringProperty("");
		this.phone 		= new SimpleStringProperty("");
	}
	public LibraryMember(String memberID, String firstName, String lastName, String phone) {
		super();
		this.memberID 	= new SimpleStringProperty(memberID);
		this.firstName 	= new SimpleStringProperty(firstName);
		this.lastName 	= new SimpleStringProperty(lastName);
		this.phone 		= new SimpleStringProperty(phone);
	}
	public String getMemberID() {
		return memberID.get();
	}
	public void setMemberID(String memberID) {
		this.memberID.set(memberID);
	}
	public String  getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	
	public StringProperty memberIDProperty() {
		return memberID;
	}
	public StringProperty firstNameProperty() {
		return firstName;
	}
	public StringProperty lastNameProperty() {
		return lastName;
	}
	public StringProperty phoneProperty() {
		return phone;
	}
	
}
