package com.example.entity;

public class ngoaite {
	String maNT;
	String tenNgoaiTe;
	String muaTienMat;
	String muaChuyenKhoan;
	double ban;
	public ngoaite(String maNT, String tenNgoaiTe, String muaTienMat, String muaChuyenKhoan, double ban) {
		this.maNT = maNT;
		this.tenNgoaiTe = tenNgoaiTe;
		this.muaTienMat = muaTienMat;
		this.muaChuyenKhoan = muaChuyenKhoan;
		this.ban = ban;
	}
	public String getMaNT() {
		return maNT;
	}
	public void setMaNT(String maNT) {
		this.maNT = maNT;
	}
	public String getTenNgoaiTe() {
		return tenNgoaiTe;
	}
	public void setTenNgoaiTe(String tenNgoaiTe) {
		this.tenNgoaiTe = tenNgoaiTe;
	}
	public String getMuaTienMat() {
		return muaTienMat;
	}
	public void setMuaTienMat(String muaTienMat) {
		this.muaTienMat = muaTienMat;
	}
	public String getMuaChuyenKhoan() {
		return muaChuyenKhoan;
	}
	public void setMuaChuyenKhoan(String muaChuyenKhoan) {
		this.muaChuyenKhoan = muaChuyenKhoan;
	}
	public double getBan() {
		return ban;
	}
	public void setBan(double ban) {
		this.ban = ban;
	}
	
	
}
