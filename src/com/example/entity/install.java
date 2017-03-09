package com.example.entity;

import java.io.Serializable;

public class install implements Serializable{
	int user_id;
	String install_type, install_name;
	
	public install(int user_id, String install_type, String install_name) {
		this.user_id = user_id;
		this.install_type = install_type;
		this.install_name = install_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getInstall_type() {
		return install_type;
	}

	public void setInstall_type(String install_type) {
		this.install_type = install_type;
	}

	public String getInstall_name() {
		return install_name;
	}

	public void setInstall_name(String install_name) {
		this.install_name = install_name;
	}
	
	
	
}
