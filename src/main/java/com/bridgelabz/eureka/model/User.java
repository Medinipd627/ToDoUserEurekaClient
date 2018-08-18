package com.bridgelabz.eureka.model;

import org.springframework.data.annotation.Id;
/********************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose:User pojo class for the login and registration
 *******************************************************************************************************/
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {

@Id
	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private boolean isVerified;

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
