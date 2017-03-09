package com.example.nguoidung;

import java.util.ArrayList;

import com.example.database.database;
import com.example.entity.GiaoDich;
import com.example.entity.nguoidungdangky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NguoidungDao {
	private SQLiteDatabase db;
	final static String DB_TABLE_USER = "account";
	final static String DB_USER_ID = "id";
	final static String DB_USER_LASTNAME = "lastname";
	final static String DB_USER_FIRSTNAME = "firstname";
	final static String DB_USER_USERNAME = "username";
	final static String DB_USER_PASSWORD = "password";
	final static String DB_USER_EMAIL = "email";
	final static String DB_USER_GENDER = "gioitinh";
	final static String DB_USER_ADDRESS = "address";
	final static String DB_USER_PHONE = "phonenumber";
	
	public NguoidungDao(Context context) {
		database dbHelper = new database(context);
		this.db = dbHelper.getWritableDatabase();
	}
	
	public boolean insert(String username, String password
			, String firstname, String lastname, String email
				,String address, String phonenumber, String gender){
		ContentValues values = new ContentValues();
		values.put(DB_USER_LASTNAME, lastname);
		values.put(DB_USER_FIRSTNAME, firstname);
		values.put(DB_USER_USERNAME, username);
		values.put(DB_USER_PASSWORD, password);
		values.put(DB_USER_EMAIL, email);
		values.put(DB_USER_ADDRESS, address);
		values.put(DB_USER_PHONE, phonenumber);
		values.put(DB_USER_GENDER, gender);
		long kq = db.insert(DB_TABLE_USER, null, values);
		if (kq == -1)
			return false;
		return true;
	}
	
	public boolean updatePassword(String email, String password){
		ContentValues values = new ContentValues();
		values.put(DB_USER_PASSWORD, password);
		db.update(DB_TABLE_USER, values, DB_USER_EMAIL + "=?", new String[]{email});
		return true;
	}
	
	public boolean checkemail(String email)
	{
		String sql = "SELECT * FROM account WHERE email = '"+email+"'";
		ArrayList<nguoidungdangky> nguoidung = getSQL(sql);
		if (nguoidung.isEmpty())
			return false;
		return true;
	}
	
	public boolean checklogin(String username, String password)
	{
		String sql = "SELECT * FROM account WHERE username = '"+username+"' AND password = '"+password+"'";
		ArrayList<nguoidungdangky> nguoidung = getSQL(sql);
		if (nguoidung.isEmpty())
			return false;
		return true;
	}
	
	public ArrayList<nguoidungdangky> getIdUser(String email)
	{
		String sql = "SELECT * FROM account WHERE email = '"+email+"'";
		ArrayList<nguoidungdangky> nguoidung = getSQL(sql);
		return nguoidung;
	}
	
	
	public boolean delete_user(String email)
	{
		int kq = db.delete(DB_TABLE_USER, DB_USER_EMAIL + "= ?", new String[]{email});
		if(kq > 0)
			return true;
		return false;
	}
	
	public ArrayList<nguoidungdangky> getListUser()
	{
		String sql = "SELECT * FROM account";
		ArrayList<nguoidungdangky> nguoidung = getSQL(sql);
		return nguoidung;
	}
	
	public ArrayList<nguoidungdangky> getSQL(String sql,String...args){
		ArrayList<nguoidungdangky> nguoidung  = new ArrayList<nguoidungdangky>();
		Cursor cursor = db.rawQuery(sql, args);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			int id 	= cursor.getInt(0);
			String lastname 	= cursor.getString(1);
			String firstname  	= cursor.getString(2);
			String username 	= cursor.getString(3);
			String password 	= cursor.getString(4);
			String email 		= cursor.getString(5);
			String address 		= cursor.getString(6);
			String phonenumber 	= cursor.getString(7);
			String gender 	    = cursor.getString(8);
			nguoidung.add(new nguoidungdangky(id, username, password, firstname, lastname
									, email, address, phonenumber,gender));
			cursor.moveToNext();
		}
		cursor.close();
		return nguoidung;
	}
}
