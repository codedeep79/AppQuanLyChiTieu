package com.example.adapter;

import java.util.ArrayList;

import com.example.entity.nguoidungdangky;
import com.example.quanlychitieu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterQuanTriAdmin extends ArrayAdapter<nguoidungdangky>{

	Context context;
	int resource;
	ArrayList<nguoidungdangky> items;
	public AdapterQuanTriAdmin(Context context, int resource, ArrayList<nguoidungdangky> items) {
		super(context, resource, items);
		this.context = context;
		this.resource = resource;
		this.items = items;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = layoutInflater.inflate(resource, parent, false);
		}
		nguoidungdangky danhsachuser = items.get(position);
		if (danhsachuser != null)
		{
			TextView txtUserName = (TextView) v.findViewById(R.id.txtUserName);
			txtUserName.setText("Tên user: " + danhsachuser.getEmail()); // Tên user là email
			TextView txtAddress = (TextView) v.findViewById(R.id.txtAddress);
			txtAddress.setText("Địa chỉ: " + danhsachuser.getaddress());
			TextView txtPhone = (TextView) v.findViewById(R.id.txtPhone);
			txtPhone.setText("Điện thoại: " + danhsachuser.getphonenumber());
			TextView txtPass = (TextView) v.findViewById(R.id.txtPass);
			txtPass.setText("Password: " + danhsachuser.getPassword());
			TextView txtLastName = (TextView) v.findViewById(R.id.txtLastName);
			txtLastName.setText("Họ: " + danhsachuser.getLastname());
			TextView txtFirstName = (TextView) v.findViewById(R.id.txtFirstName);
			txtFirstName.setText("Tên: " + danhsachuser.getFirstname());
		}
		return v;
	}

}
