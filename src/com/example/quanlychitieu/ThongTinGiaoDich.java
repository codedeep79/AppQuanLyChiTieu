package com.example.quanlychitieu;

import java.util.ArrayList;

import com.example.adapter.AdapterThongKe;
import com.example.entity.GiaoDich;
import com.example.entity.nguoidungdangky;
import com.example.nguoidung.GiaoDichDAO;
import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ThongTinGiaoDich extends Activity {
	ListView listTransition;
	TextView txt;
	String today, week, month, year;
	AdapterThongKe adapter;
	ArrayList<nguoidungdangky> nguoidungdangky;
	ArrayList<GiaoDich> giaoDich;
	int user_id = 0;
	NguoidungDao nguoidung;
	GiaoDichDAO giaodich;
	int vt = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_giao_dich);
		listTransition = (ListView) findViewById(R.id.listTransition);
		registerForContextMenu(listTransition);
		txt = (TextView) findViewById(R.id.txt);
		giaodich  = new GiaoDichDAO(ThongTinGiaoDich.this);
		nguoidung = new NguoidungDao(this);
		
		user_id = getIntent().getExtras().getInt("user_id");
		String user = getIntent().getExtras().getString("user");
		String id = getIntent().getExtras().getString("id");
		switch(id)
		{
		case "homnay":
			txt.setText("Danh sách thu chi hôm nay".toUpperCase());
			break;
		case "tuan":
			txt.setText("Danh sách thu chi tuần này".toUpperCase());
			break;
		case "thang":
			txt.setText("Danh sách thu chi tháng".toUpperCase());
			break;
		case "nam":
			txt.setText("Danh sách thu chi của năm".toUpperCase());
			break;
		}
		int user_id = getIntent().getExtras().getInt("user_id");
		
		giaoDich = giaodich.getGiaoDichUser(user_id);
		adapter = new AdapterThongKe(ThongTinGiaoDich.this, R.layout.activity_thong_ke_giao_dich, giaoDich);
		listTransition.setAdapter(adapter);
		
		listTransition.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				vt = position;
				return false;
			}
		});
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.thong_tin_thu_chi, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
		case R.id.delete:
			GiaoDich gd = new GiaoDich();
			gd          = giaoDich.get(vt);
			
			boolean kq = giaodich.delete_transition(user_id, gd.getTransition_name(), gd.getTransition_date());
			if (kq)
				Toast.makeText(getApplicationContext(), "Delete success", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getApplicationContext(), "Delete failed. Please try again", Toast.LENGTH_SHORT).show();
			giaoDich = giaodich.getGiaoDichUser(user_id);
			adapter = new AdapterThongKe(ThongTinGiaoDich.this, R.layout.activity_thong_ke_giao_dich, giaoDich);
			listTransition.setAdapter(adapter);
			break;
		case R.id.update:
			Toast.makeText(ThongTinGiaoDich.this, "Đang cập nhật...", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
