package com.example.entity;

public class accountMoney {
	String type;
	double tien;
	public accountMoney(String type, double tien) {
		this.type = type;
		this.tien = tien;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getTien() {
		return tien;
	}
	public void setTien(double tien) {
		this.tien = tien;
	}
	
	
	
}
