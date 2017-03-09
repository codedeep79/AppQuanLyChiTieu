package com.example.adapter;

import java.util.ArrayList;

import com.example.entity.accountMoney;
import com.example.quanlychitieu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class thuchiAdapter extends ArrayAdapter<accountMoney>{
	Context context;
	int resource;
	ArrayList<accountMoney> items;
	public thuchiAdapter(Context context, int resource, ArrayList<accountMoney> items) {
		super(context, resource, items);
		this.context = context;
		this.resource = resource;
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null){
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = layoutInflater.inflate(resource, parent, false);
		}
		accountMoney money = items.get(position);
		if (money != null)
		{
			TextView txtTypeMoney = (TextView) v.findViewById(R.id.txtTypeMoney);
			txtTypeMoney.setText(money.getType());
			TextView txtMoney = (TextView) v.findViewById(R.id.txtMoney);
			txtMoney.setText(money.getTien() + "");
		}
		return v;
	}

}
