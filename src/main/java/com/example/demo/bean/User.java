package com.example.demo.bean;

import javax.validation.constraints.NotNull;

public class User {
	
	Long ID;
    @NotNull(message = "Firstname is mandatory")
	String FirstName;
    @NotNull(message = "Lastname is mandatory")
	String LastName;
    @NotNull(message="Email is mandatory")
	String Email;
	//LocalDate Create_Date;
	//LocalDate DOB;
    @NotNull(message="Password is mandatory")
	String Password;

	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
		
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	/*public LocalDate getCreate_Date() {
		return Create_Date;
	}
	public void setCreate_Date(LocalDate create_Date) {
		Create_Date = create_Date;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}*/

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
