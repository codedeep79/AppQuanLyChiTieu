package com.example.adapter;

import java.util.ArrayList;

import com.example.entity.GiaoDich;
import com.example.quanlychitieu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterThongTinLoaiUser extends ArrayAdapter<GiaoDich>{
	Context context;
	int resource;
	ArrayList<GiaoDich> items;
	public AdapterThongTinLoaiUser(Context context, int resource, ArrayList<GiaoDich> items) {
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
			LayoutInflater layoutInflater 
						= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = layoutInflater.inflate(resource,parent, false);
		}
		GiaoDich giaodich  = items.get(position);
		if (giaodich != null)
		{
			TextView txtTransitionName = (TextView) v.findViewById(R.id.txtTransitionName);
			txtTransitionName.setText("Tên giao dịch: " + giaodich.getTransition_name());
			TextView txtDate		   = (TextView) v.findViewById(R.id.txtDate);
			txtDate.setText("Thời gian: " + giaodich.getTransition_date());
		}
		return v;
	}

}
