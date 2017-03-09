package com.example.quanlychitieu;

import java.util.ArrayList;

import com.example.adapter.AdapterQuanTriAdmin;
import com.example.entity.GiaoDich;
import com.example.entity.nguoidungdangky;
import com.example.nguoidung.GiaoDichDAO;
import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class QuanTriAdmin extends Activity {
	ListView listUser;
	NguoidungDao nguoidung;
	GiaoDichDAO giaodichnguoidung;
	ArrayList<nguoidungdangky> quantri ;
	ArrayList<GiaoDich> giaodich ;
	AdapterQuanTriAdmin adapter;
	Intent intent = null;
	String username = "";
	String address = "";
	String phonenumber = "";
	int vt = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quan_tri_admin);
		listUser = (ListView) findViewById(R.id.listUser);
		nguoidung = new NguoidungDao(QuanTriAdmin.this);
		giaodichnguoidung = new GiaoDichDAO(QuanTriAdmin.this);
		
		giaodich = new ArrayList<GiaoDich>();
		quantri = nguoidung.getListUser();
		adapter = new AdapterQuanTriAdmin(QuanTriAdmin.this, R.layout.layout_list_user, quantri);
		listUser.setAdapter(adapter);
		
		listUser.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
				int user_id = quantri.get(position).getId();
				intent = new Intent(QuanTriAdmin.this, ThongTinLoaiGiaoUser.class);
				Bundle b = new Bundle();
				b.putInt("user_id", user_id);
				intent.putExtras(b);
				startActivity(intent);
				overridePendingTransition(R.anim.transition1, R.anim.transition2);
			}
		});
		
		listUser.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				vt = position;
				return false;
			}
		});
		registerForContextMenu(listUser);
		
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.quantriadmin, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
			case R.id.update:
				// Them sua xoa user, loại giao dịch
				Toast.makeText(QuanTriAdmin.this, "Đang cập nhật...", Toast.LENGTH_SHORT).show();
				break;
			case R.id.delete:
				boolean kq = nguoidung.delete_user(quantri.get(vt).getEmail());
				if (kq)
					Toast.makeText(getApplicationContext()
							, "Delete success", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getApplicationContext()
							, "Delete failed. Please try again", Toast.LENGTH_LONG).show();
				
				quantri = nguoidung.getListUser();
				adapter = new AdapterQuanTriAdmin(QuanTriAdmin.this
						, R.layout.layout_list_user, quantri);
				listUser.setAdapter(adapter);
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
