package com.example.nguoidung;

import java.util.ArrayList;

import com.example.database.database;
import com.example.entity.install;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User_install {
	private SQLiteDatabase db;
	private Context context;
	final static String DB_TABLE_INSTALL = "install";
//	final static String DB_INSTALL_ID 	 = "install_id";
	final static String DB_INSTALL_TYPE  = "install_type";
	final static String DB_INSTALL_NAME  = "install_name";
	final static String DB_USER_ID 		 = "user_id";
	
	public User_install(Context context) {
		this.context = context;
		database databaseHelper = new database(context);
		this.db = databaseHelper.getWritableDatabase();
	}
	
	public boolean insert_install(int user, String install_type, String install_name){
		ContentValues values = new ContentValues();
		values.put(DB_USER_ID, user);
		values.put(DB_INSTALL_TYPE, install_type);
		values.put(DB_INSTALL_NAME, install_name);
		long kq = db.insert(DB_TABLE_INSTALL, null, values);
		if (kq == -1)
			return false;
		return true;
	}
	
	public boolean delete_install(String install_name)
	{
		int kq = db.delete(DB_TABLE_INSTALL, DB_INSTALL_NAME + "= "+'?'+"", new String[]{install_name});
		if (kq > 0)
			return true;
		return false;
	}
	public ArrayList<install> getListInstall(String sql, String... arg0){
		ArrayList<install> install = new ArrayList<install>();
		Cursor cursor = db.rawQuery(sql, arg0);
		cursor.moveToFirst();
		while (!cursor.isAfterLast())
		{
			int install_id = cursor.getInt(0);
			int user_id = cursor.getInt(1);
			String install_type = cursor.getString(2);
			String install_name = cursor.getString(3);
			install.add(new install(user_id, install_type, install_name));
			cursor.moveToNext();
		}
		cursor.close();
		return install;
		
	}
}
