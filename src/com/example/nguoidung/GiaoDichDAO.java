package com.example.nguoidung;

import java.util.ArrayList;
import java.util.Date;

import com.example.database.database;
import com.example.entity.GiaoDich;
import com.example.entity.LoaiGiaoDich;
import com.example.entity.nguoidungdangky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.animation.BounceInterpolator;

public class GiaoDichDAO extends database{
	SQLiteDatabase db;
	database sqlHelper;
	final static String DB_TABLE_TYPE = "type";
	final static String DB_TYPE_THU = "thu";
	final static String DB_TYPE_CHI = "chi";
	
	final static String DB_TABLE_TRANSITION  = "transition";
	final static String DB_TRANSITION_ID = "transition_id";
	final static String DB_USER_ID = "user_id";
	final static String DB_TYPE_ID = "type_id";
	final static String DB_TRANSITION_NAME = "transition_name";
	final static String DB_TRANSITION_ACCOUNT = "transition_account";
	final static String DB_TRANSITION_DATE = "transition_date";
	final static String DB_TRANSITION_MONEY = "transition_money";
	final static String DB_TRANSITION_STATE = "transition_state";
	final static String DB_TRANSITION_THUONG = "transition_thuong";
	
	public GiaoDichDAO(Context context) {
		super(context);
		sqlHelper = new database(context);
		db = sqlHelper.getWritableDatabase();
	}
	
	
	public boolean insert_loaigiaodich(int type_id, double thu, double chi, String date)
			{
				ArrayList<LoaiGiaoDich> loaigiaodich = new ArrayList<LoaiGiaoDich>();
				ContentValues values = new ContentValues();
				values.put(DB_TYPE_ID, type_id);
				values.put(DB_TYPE_THU, thu);
				values.put(DB_TYPE_CHI, chi);
				values.put(DB_TRANSITION_DATE, date);
				long kq = db.insert(DB_TABLE_TYPE, null, values);
				if (kq == -1)
					return false;
				return true;
			}
	
	public boolean delete_transition(int user_id, String transition_name, String transition_date){
		int kq = db.delete(DB_TABLE_TRANSITION, DB_USER_ID + "= "+user_id+" AND "+DB_TRANSITION_NAME
						+" LIKE "+'?'+" AND "+DB_TRANSITION_DATE+" LIKE "+'?'+"",new String[]{transition_name, transition_date});
		if (kq > 0)
			return true;
		return false;
		
	}
	
	public boolean insert_giaodich(int user_id, int type_id, String transition_name, String transition_account,
	String transition_date, String transition_state, double transition_money)
	{
		ArrayList<GiaoDich> giaodich = new ArrayList<GiaoDich>();
		ContentValues values = new ContentValues();
		values.put(DB_USER_ID, user_id);
		values.put(DB_TYPE_ID, type_id);
		values.put(DB_TRANSITION_NAME, transition_name);
		values.put(DB_TRANSITION_ACCOUNT, transition_account);
		values.put(DB_TRANSITION_DATE, transition_date);
		values.put(DB_TRANSITION_MONEY, transition_money);
		values.put(DB_TRANSITION_STATE, transition_state);
		long kq = db.insert(DB_TABLE_TRANSITION, null, values);
		if (kq == -1)
			return false;
		return true;
	}
	public ArrayList<GiaoDich> getAccount(int user_id){
		ArrayList<GiaoDich> giaoDich = new ArrayList<GiaoDich>();
		String sql = "SELECT * FROM "+DB_TABLE_TRANSITION+" WHERE "+DB_USER_ID+" = "+user_id+"";
		giaoDich = getSQLGiaoDich(sql);
		return giaoDich;
	}
	
	public ArrayList<GiaoDich> getGiaoDichUser(int user_id){
		ArrayList<GiaoDich> giaoDich = new ArrayList<GiaoDich>();
		String sql = "SELECT * FROM "+DB_TABLE_TRANSITION+" WHERE user_id = "+user_id+"";
		giaoDich = getSQLGiaoDich(sql);
		return giaoDich;
	}
	
	public ArrayList<GiaoDich> getSQLGiaoDich(String sql,String...args){
		ArrayList<GiaoDich> giaoDich  = new ArrayList<GiaoDich>();
		Cursor cursor = db.rawQuery(sql, args);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			String user_id 				= cursor.getString(1);
			String type_id  			= cursor.getString(2);
			String transition_name 		= cursor.getString(3);
			String transition_account 	= cursor.getString(4);
			String transition_date 		= cursor.getString(5);
			String transition_state 	= cursor.getString(6);
			double transition_money 	= cursor.getDouble(7);
			giaoDich.add(new GiaoDich(user_id, type_id, transition_name, transition_account
					, transition_date, transition_state, transition_money));
			cursor.moveToNext();
		}
		cursor.close();
		return giaoDich;
	}
	
	public ArrayList<LoaiGiaoDich> getMoney(int type_id){
		ArrayList<LoaiGiaoDich> loaiGiaoDich = new ArrayList<LoaiGiaoDich>();
		String sql = "SELECT * FROM "+DB_TABLE_TYPE+" WHERE "+DB_TYPE_ID+" = "+type_id+" ";
		loaiGiaoDich = getSQLLoaiGiaoDich(sql);
		return loaiGiaoDich;
	}
	public ArrayList<LoaiGiaoDich> getSQLLoaiGiaoDich(String sql,String...args){
		ArrayList<LoaiGiaoDich> loaiGiaoDich  = new ArrayList<LoaiGiaoDich>();
		Cursor cursor = db.rawQuery(sql, args);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			int type_id = cursor.getInt(0);
			double thu = cursor.getDouble(1);
			double chi = cursor.getDouble(2);
			String transition_date = cursor.getString(3);
			loaiGiaoDich.add(new LoaiGiaoDich(thu, chi, transition_date));
			cursor.moveToNext();
		}
		cursor.close();
		return loaiGiaoDich;
	}
}
