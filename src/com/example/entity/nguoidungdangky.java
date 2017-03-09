package com.example.entity;

import java.io.Serializable;

public class nguoidungdangky implements Serializable{
	int id;
	String username, password, firstname, lastname, email, address, phonenumber, gender;

	
	public nguoidungdangky() {
	}

	public nguoidungdangky(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	
	public nguoidungdangky(int id, String username, String password, String firstname, String lastname, String email,
			String address, String phonenumber, String gender) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
		this.gender = gender;
	}


	public nguoidungdangky(String username, String password
			, String firstname, String lastname, String email
				,String address, String phonenumber) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getphonenumber() {
		return phonenumber;
	}

	public void setphonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
