package com.example.quanlychitieu;

import java.util.ArrayList;

import com.example.entity.GiaoDich;
import com.example.entity.LoaiGiaoDich;
import com.example.nguoidung.GiaoDichDAO;
import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DeNghiChiTieu extends Activity {
	TextView txtDeNghi;
	GiaoDichDAO giaoDich;
	// Số dư
	double thu = 0;
	double chi = 0;
	double sodu = 0;
	int user_id = 0;
	ArrayList<GiaoDich> listGiaoDich = null;
	ArrayList<LoaiGiaoDich> loaiGiaoDich = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_de_nghi_chi_tieu);
		txtDeNghi = (TextView) findViewById(R.id.txtDeNghi);
		giaoDich = new GiaoDichDAO(DeNghiChiTieu.this);
		
		user_id = getIntent().getExtras().getInt("user_id");
		
		listGiaoDich = giaoDich.getAccount(user_id);
		loaiGiaoDich = giaoDich.getMoney(user_id);
		for (GiaoDich soGiaoDich : listGiaoDich)
		{
			loaiGiaoDich = giaoDich.getMoney(Integer.parseInt(soGiaoDich.getType_id()));
			for (LoaiGiaoDich loaigiaodich: loaiGiaoDich)
			{
				thu += loaigiaodich.getThu();
				chi += loaigiaodich.getChi();
			}
		}
		sodu = thu - chi;
		if (sodu > 0)
		txtDeNghi.setText("Số dư hiện tại của bạn: " +sodu+ 
				"\nỨng dụng đề nghị tháng này chi thêm phí với con số dưới: " + (sodu + sodu/2) 
					+ "\nLưu ý: Đề nghị chỉ mang tính chất tham khảo");
		else
			txtDeNghi.setText("Số dư bạn chưa có. Bạn vui lòng nhập khoản thu."
					+ "\nLưu ý: Đề nghị chỉ mang tính chất tham khảo");
		
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
