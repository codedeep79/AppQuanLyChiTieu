package com.example.quanlychitieu;

import java.util.ArrayList;

import com.example.adapter.AdapterThongTinLoaiUser;
import com.example.entity.GiaoDich;
import com.example.nguoidung.GiaoDichDAO;
import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ThongTinLoaiGiaoUser extends Activity {
	ListView listThongTinLoaiUser;
	ArrayList<GiaoDich> giaodich;
	AdapterThongTinLoaiUser adapter;
	GiaoDichDAO giaoDichNguoiDung;
	NguoidungDao nguoidung;
	TextView txtInfoData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_loai_giao_user);
		txtInfoData = (TextView) findViewById(R.id.txtInfoData);
		giaoDichNguoiDung = new GiaoDichDAO(ThongTinLoaiGiaoUser.this);
		listThongTinLoaiUser = (ListView) findViewById(R.id.listThongTinLoaiUser);
		int user_id 		   = getIntent().getExtras().getInt("user_id");
		
		giaodich = giaoDichNguoiDung.getSQLGiaoDich("SELECT * FROM transition  WHERE user_id = "+user_id+"");
		if (!giaodich.isEmpty())
		{
			adapter = new AdapterThongTinLoaiUser(ThongTinLoaiGiaoUser.this
					, R.layout.layout_list_loai_giao_dich_user, giaodich);
			listThongTinLoaiUser.setAdapter(adapter);
		}
		else
			txtInfoData.setText("Không tồn tại dữ liệu");
		
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
