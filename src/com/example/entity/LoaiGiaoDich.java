package com.example.entity;

import java.io.Serializable;

public class LoaiGiaoDich implements Serializable{
	int type_id = 0;
	double thu, chi;
	String transition_date;
	
	
	public LoaiGiaoDich(double thu, double chi, String transition_date) {
		this.thu = thu;
		this.chi = chi;
		this.transition_date = transition_date;
	}


	public LoaiGiaoDich(int type_id, double thu, double chi, String transition_date) {
		this.type_id = type_id;
		this.thu = thu;
		this.chi = chi;
		this.transition_date = transition_date;
	}


	public int getType_id() {
		return type_id;
	}


	public void setType_id(int type_id) {
		this.type_id = type_id;
	}


	public double getThu() {
		return thu;
	}


	public void setThu(double thu) {
		this.thu = thu;
	}


	public double getChi() {
		return chi;
	}


	public void setChi(double chi) {
		this.chi = chi;
	}


	public String getTransition_date() {
		return transition_date;
	}


	public void setTransition_date(String transition_date) {
		this.transition_date = transition_date;
	}
	
	
}
