package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper{
	final static String DB_NAME = "quanlychitieu.db";
	final static int DB_VERSION = 1;
	
	final static String DB_TABLE_USER = "account";
	final static String DB_USER_ID = "user_id";
	final static String DB_USER_LASTNAME = "lastname";
	final static String DB_USER_FIRSTNAME = "firstname";
	final static String DB_USER_USERNAME = "username";
	final static String DB_USER_PASSWORD = "password";
	final static String DB_USER_EMAIL = "email";
	final static String DB_USER_ADDRESS = "address";
	final static String DB_USER_PHONE = "phonenumber";
	final static String DB_USER_GENDER = "gioitinh";
	final static String DB_USER_SODU = "sodu";
	
	final static String DB_TABLE_TYPE = "type";
	final static String DB_TYPE_ID = "type_id";
	final static String DB_TYPE_THU = "thu";
	final static String DB_TYPE_CHI = "chi";
	
	final static String DB_TABLE_TRANSITION  = "transition";
	final static String DB_TRANSITION_ID = "transition_id";
	final static String DB_TRANSITION_NAME = "transition_name";
	final static String DB_TRANSITION_ACCOUNT = "transition_account";
	final static String DB_TRANSITION_DATE = "transition_date";
	final static String DB_TRANSITION_MONEY = "transition_money";
	final static String DB_TRANSITION_STATE = "transition_state";
	
	final static String DB_TABLE_INSTALL = "install";
	final static String DB_INSTALL_ID 	 = "install_id";
	final static String DB_INSTALL_TYPE  = "install_type";
	final static String DB_INSTALL_NAME  = "install_name";
	
	SQLiteDatabase db;
	public database(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.db = this.getWritableDatabase();
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_INSTALL+"");
		String type = "CREATE TABLE "+DB_TABLE_TYPE+" ("+DB_TYPE_ID+" INTEGER PRIMARY KEY"
				+ ", "+DB_TYPE_THU+" REAL"
				+ ", "+DB_TYPE_CHI+" REAL"
				+ ", "+DB_TRANSITION_DATE+" TEXT)";
		
		String transition = "CREATE TABLE "+DB_TABLE_TRANSITION+"( "+DB_TRANSITION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+DB_USER_ID+" INTEGER,"+DB_TYPE_ID+" INTEGER"
				+ "	, "+DB_TRANSITION_NAME+" TEXT, "+DB_TRANSITION_ACCOUNT+" TEXT, "+DB_TRANSITION_DATE+" TEXT"
						+ ","+DB_TRANSITION_STATE+" TEXT ,"+DB_TRANSITION_MONEY+" REAL"
								+ ", FOREIGN KEY("+DB_USER_ID+") REFERENCES "+DB_TABLE_USER+"("+DB_USER_ID+")"
										+ ", FOREIGN KEY("+DB_TYPE_ID+") REFERENCES "+DB_TABLE_TYPE+"("+DB_TYPE_ID+"))";
		
		String account = "CREATE TABLE "+DB_TABLE_USER+" ("+DB_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ", "+DB_USER_LASTNAME+" NVARCHAR(50) NOT NULL, "+DB_USER_FIRSTNAME+" NVARCHAR(30) NOT NULL"
						+ ", "+DB_USER_USERNAME+" NVARCHAR(30) NOT NULL UNIQUE, "+DB_USER_PASSWORD+" TEXT NOT NULL"
								+ ", "+DB_USER_EMAIL+" TEXT NOT NULL UNIQUE, "+DB_USER_ADDRESS+" TEXT"
										+ ", "+DB_USER_PHONE+" NVARCHAR(15), "+DB_USER_GENDER+" NVARCHAR(5)"
												+ ", "+DB_USER_SODU+" REAL)";
		
		String install = "CREATE TABLE "+DB_TABLE_INSTALL+" ("+DB_INSTALL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ", "+DB_USER_ID+" INTEGER, "+DB_INSTALL_TYPE+" TEXT NOT NULL"
				+ ", "+DB_INSTALL_NAME+" TEXT NOT NULL)";
		
		db.execSQL(account);
		db.execSQL(type);
		
		db.execSQL(transition);
		db.execSQL(install);
		String insertTaiKhoan1 = "INSERT INTO "+DB_TABLE_USER+" VALUES(1,'nguyen','van a'"
				+ ", 'admin@gmail.com','admin','admin@gmail.com', 'Thành Phố Hồ Chí Minh','0903024421','Nam',0.0)";
		String insertTaiKhoan2 = "INSERT INTO "+DB_TABLE_USER+" VALUES(2,'nguyen','van b'"
				+ ", 'bnv@gmail.com','123','bnv@gmail.com', 'Thành Phố Hồ Chí Minh','0903024422','Nam',0.0)";
		String insertTaiKhoan3 = "INSERT INTO "+DB_TABLE_USER+" VALUES(3,'nguyen','van c'"
				+ ", 'cnv@gmail.com','123','cnv@gmail.com', 'Thành Phố Hồ Chí Minh','0903024423','Nam',0.0)";
		String insertTaiKhoan4 = "INSERT INTO "+DB_TABLE_USER+" VALUES(4,'nguyen','van d'"
				+ ", 'dnv@gmail.com','123','dnv@gmail.com', 'Thành Phố Hồ Chí Minh','0903024424','Nam',0.0)";
		String insertTaiKhoan5 = "INSERT INTO "+DB_TABLE_USER+" VALUES(5,'nguyen','van e'"
				+ ", 'env@gmail.com','123','env@gmail.com', 'Thành Phố Hồ Chí Minh','0903024425','Nam',0.0)";
		
		
		db.execSQL(insertTaiKhoan1);
		db.execSQL(insertTaiKhoan2);
		db.execSQL(insertTaiKhoan3);
		db.execSQL(insertTaiKhoan4);
		db.execSQL(insertTaiKhoan5);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
