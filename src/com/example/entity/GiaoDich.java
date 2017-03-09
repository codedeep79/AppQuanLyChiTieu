package com.example.entity;

import java.io.Serializable;

public class GiaoDich implements Serializable{
	String transition_id, user_id, type_id, transition_name, transition_account, transition_date, transition_state;
	double transition_money;
	
	
	public GiaoDich() {
	}

	public GiaoDich(String transition_name, String transition_date) {
		this.transition_name = transition_name;
		this.transition_date = transition_date;
	}

	public GiaoDich(String transition_id, String user_id, String type_id, String transition_name,
			String transition_account, String transition_date, String transition_state, double transition_money) {
		this.transition_id = transition_id;
		this.user_id = user_id;
		this.type_id = type_id;
		this.transition_name = transition_name;
		this.transition_account = transition_account;
		this.transition_date = transition_date;
		this.transition_state = transition_state;
		this.transition_money = transition_money;
	}

	public GiaoDich(String user_id, String type_id, String transition_name, String transition_account,
			String transition_date, String transition_state, double transition_money ) {
		this.user_id = user_id;
		this.type_id = type_id;
		this.transition_name = transition_name;
		this.transition_account = transition_account;
		this.transition_date = transition_date;
		this.transition_state = transition_state;
		this.transition_money = transition_money;
	}

	public String getTransition_id() {
		return transition_id;
	}

	public void setTransition_id(String transition_id) {
		this.transition_id = transition_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getTransition_name() {
		return transition_name;
	}

	public void setTransition_name(String transition_name) {
		this.transition_name = transition_name;
	}

	public String gettransition_account() {
		return transition_account;
	}

	public void settransition_account(String transition_account) {
		this.transition_account = transition_account;
	}

	public String getTransition_date() {
		return transition_date;
	}

	public void setTransition_date(String transition_date) {
		this.transition_date = transition_date;
	}

	public String getTransition_state() {
		return transition_state;
	}

	public void setTransition_state(String transition_state) {
		this.transition_state = transition_state;
	}

	public double getTransition_money() {
		return transition_money;
	}

	public void setTransition_money(double transition_money) {
		this.transition_money = transition_money;
	}

	
	
	
	
}
