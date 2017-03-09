package com.example.quanlychitieu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import com.example.adapter.thuchiAdapter;
import com.example.entity.GiaoDich;
import com.example.entity.LoaiGiaoDich;
import com.example.entity.accountMoney;
import com.example.entity.install;
import com.example.entity.nguoidungdangky;
import com.example.nguoidung.GiaoDichDAO;
import com.example.nguoidung.NguoidungDao;
import com.example.nguoidung.User_install;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class QuanLyChiTieu extends Activity implements OnClickListener{
	TabHost tabs;
	ListView listCaiDat;
	thuchiAdapter thuchi_adapter;
	ArrayAdapter<String> thongKeThuChiAdapter,caiDatAdapter, themCaiDatAdapter;
	ArrayList<accountMoney> account_money;
	ArrayList<nguoidungdangky> nguoidungdangky;
	ArrayList<String> caiDatPhanNhom, caiDatTaiKhoan, themCaiDat;
	EditText edtThemCaiDat;

	/*------------Thu chi thêm giao dịch-*/
	Button btnthemgiaodich, btnhomnay, btnTuan, btnThang, btnNam, btnDeNghi;
	TextView txtSoDu; 
	ListView listTypeMoney;
	// Thu chi spinner
	Dialog dialog;
	Spinner account, group, typeTransition, stateTransition;
	EditText edtMoney, edtTransitionFuture;
	Button btnDay, btnSave, btnCancel;
	TextView txtTime;
	ArrayList<install> listInstallSpinner = null;
	ArrayList<GiaoDich> listGiaoDich = null;
	String typeNameSpinner = "";
	ArrayAdapter<String> adapterSpinnerTaiKhoan = null;
	ArrayAdapter<String> adapterSpinnerGroup = null;
	ArrayAdapter<String> adapterSpinnertypeTransition = null;
	ArrayAdapter<String> adapterSpinnerstateTransition = null;
	ArrayAdapter<String> adapterSpinnerLoaiGiaoDich = null;
	ArrayList<String> caiDatPhanNhomSpinner, caiDatTaiKhoanSpinner, caiDatLoaiGiaoDichSpinner, caiDatTinhTrangGiaoDich;
	double tienmat = 0;
	double tientietkiem = 0;
	double thetindung = 0;
	
	String  type_id = null, transition_name = null
			, transition_account = null, transition_date = null,  transition_state = null, transition_type = null;
	double transition_money,transition_thuong;
	// csdl
	GiaoDichDAO giaoDich = null;
	
	Spinner  thongkeSpinner, caidatSpinner, spinnerThemCaiDat;
	Button btnThemCaiDat;
	/*------------Cài đặt--------------*/
	// csdl
	User_install userInstall;
	NguoidungDao nguoidung;
	
	 String tenCaiDat = "";
	 int viTriSpinner = 0;
	 int viTri = 0;
	 String user = "";
	 String pass = "";
	 int dem1 = 0;
	 int dem2 = 0;
	 int user_id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quan_ly_chi_tieu);
		//-------------------------------------------////
		user = getIntent().getExtras().getString("user");
		pass = getIntent().getExtras().getString("pass");
		deleteDatabaseBeforein();
		init();
	}

	public void init(){
		
		tabs 			= (TabHost) findViewById(R.id.myTabhost);
		listTypeMoney 	= (ListView) findViewById(R.id.listTypeMoney);
		listCaiDat 		= (ListView) findViewById(R.id.listCaiDat);
		
		/*--------Thêm giao dịch------------*/
		btnthemgiaodich = (Button) findViewById(R.id.btnthemgiaodich);
		btnhomnay 		= (Button) findViewById(R.id.btnhomnay);
		btnTuan 		= (Button) findViewById(R.id.btnTuan);
		btnThang 		= (Button) findViewById(R.id.btnThang);
		btnNam 			= (Button) findViewById(R.id.btnNam);
		btnDeNghi 		= (Button) findViewById(R.id.btnDeNghi);
		
		btnthemgiaodich.setOnClickListener(this);
		btnhomnay.setOnClickListener(this);
		btnTuan.setOnClickListener(this);
		btnThang.setOnClickListener(this);
		btnNam.setOnClickListener(this);
		btnDeNghi.setOnClickListener(this);
		txtSoDu = (TextView) findViewById(R.id.txtSoDu);
		
		
		/*-----------Thu chi----------------*/
		giaoDich = new GiaoDichDAO(QuanLyChiTieu.this);
		caiDatPhanNhomSpinner 		= new ArrayList<String>();
		caiDatTaiKhoanSpinner 		= new ArrayList<String>();
		caiDatLoaiGiaoDichSpinner 	= new ArrayList<String>();
		caiDatTinhTrangGiaoDich 	= new ArrayList<String>();

		
		ArrayList<GiaoDich> listGiaoDich = giaoDich.getAccount(user_id);
		for (GiaoDich soGiaoDich : listGiaoDich)
		{
			if (soGiaoDich.gettransition_account().equals("Tiền mặt"))
			{
				tienmat = tienmat + soGiaoDich.getTransition_money();
			}
			if (soGiaoDich.gettransition_account().equals("Tiền tiết kiệm"))
			{
				tientietkiem = tientietkiem + soGiaoDich.getTransition_money();
			}
			if (soGiaoDich.gettransition_account().equals("Thẻ tín dụng"))
			{
				thetindung = thetindung + soGiaoDich.getTransition_money();
			}
		}
		
		account_money = new ArrayList<accountMoney>();
		account_money.add(new accountMoney("Tiền mặt", tienmat));
		account_money.add(new accountMoney("Tiết kiệm", tientietkiem));
		account_money.add(new accountMoney("Thẻ tín dụng", thetindung));
		thuchi_adapter = new thuchiAdapter(QuanLyChiTieu.this, R.layout.activity_account_money, account_money);
		listTypeMoney.setAdapter(thuchi_adapter);
		
		//Thêm giao dịch
		/*-----------Cài đặt------------------*/
		nguoidung = new NguoidungDao(QuanLyChiTieu.this);
		nguoidungdangky = nguoidung.getIdUser(user);
		final int user_id = nguoidungdangky.get(0).getId();
		
		
		caidatSpinner 	= (Spinner) findViewById(R.id.caidatSpinner);
		ArrayList<String> caidatList = new ArrayList<String>();
		caidatList.add("PHÂN NHÓM");
		caidatList.add("TÀI KHOẢN");
		caiDatAdapter 	= new ArrayAdapter<String>(QuanLyChiTieu.this, android.R.layout.simple_spinner_item,caidatList);
		caiDatAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
		caidatSpinner.setAdapter(caiDatAdapter);
		
		userInstall = new User_install(QuanLyChiTieu.this);
		caiDatPhanNhom = new ArrayList<String>();
		caiDatTaiKhoan = new ArrayList<String>();
		caidatSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
				viTri = position;
				caiDatPhanNhom.removeAll(caiDatPhanNhom);
				caiDatTaiKhoan.removeAll(caiDatTaiKhoan);
				ArrayList<install> listInstall = null;
				String type_name = "";
				String sql = "";
				if (position == 0)
				{
					sql = "SELECT * FROM install WHERE install_type like 'PHAN NHOM' and user_id = "+user_id+"";
					listInstall = userInstall.getListInstall(sql);
						for(int i = 0;i < listInstall.size(); ++i)
						{
							install in = listInstall.get(i);
							type_name = in.getInstall_name();
							caiDatPhanNhom.add(type_name);
						}
						caiDatAdapter = new ArrayAdapter<String>(QuanLyChiTieu.this, android.R.layout.simple_list_item_1, caiDatPhanNhom);
						listCaiDat.setAdapter(caiDatAdapter);
				}
				else if (position == 1)
				{
					sql = "SELECT * FROM install WHERE install_type like 'TAI KHOAN' and user_id = "+user_id+"";
					listInstall = userInstall.getListInstall(sql);
					
						for(int i = 0;i < listInstall.size(); ++i)
						{
							install in = listInstall.get(i);
							type_name = in.getInstall_name();
							caiDatTaiKhoan.add(type_name);
						}
						caiDatAdapter = new ArrayAdapter<String>(QuanLyChiTieu.this, android.R.layout.simple_list_item_1, caiDatTaiKhoan);
						listCaiDat.setAdapter(caiDatAdapter);
				}
				
				btnThemCaiDat.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (!edtThemCaiDat.getText().toString().matches("[\\s]{1,}"))
						{
							userInstall = new User_install(QuanLyChiTieu.this);
							String install_type;
							
							if (position == 0)
								install_type = "PHAN NHOM";
							else
								install_type = "TAI KHOAN";
							
							boolean kq = userInstall.insert_install(user_id, install_type
									, edtThemCaiDat.getText().toString());
							if (kq)
								Toast.makeText(QuanLyChiTieu.this, "Add success", Toast.LENGTH_SHORT).show();
							else
								Toast.makeText(QuanLyChiTieu.this, "Add failed", Toast.LENGTH_SHORT).show();
							
							if (position == 0)
							{
								caiDatPhanNhom.add(edtThemCaiDat.getText().toString());
								caiDatAdapter.notifyDataSetChanged();
							}
							else
							{
								caiDatTaiKhoan.add(edtThemCaiDat.getText().toString());
								caiDatAdapter.notifyDataSetChanged();
							}
						}
						else
							Toast.makeText(QuanLyChiTieu.this, "Vui lòng không để trống", Toast.LENGTH_LONG).show();
					}
				});
				
				
				final int viTriSpinner = position;
				listCaiDat.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
						AlertDialog.Builder alert  = new AlertDialog.Builder(QuanLyChiTieu.this);
						alert.setTitle("Delete!");
						alert.setIcon(getResources().getDrawable(R.drawable.delete));
						alert.setMessage("Are you sure delete?");
						
						alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (viTriSpinner == 0){
									
									boolean kq = userInstall.delete_install(caiDatPhanNhom.get(position));
									if (kq)
										Toast.makeText(QuanLyChiTieu.this,"Delete done!", Toast.LENGTH_SHORT).show();
									else
										Toast.makeText(QuanLyChiTieu.this,"Delete failed!"
												, Toast.LENGTH_SHORT).show();

									caiDatPhanNhom.remove(position);
									caiDatAdapter.notifyDataSetChanged();
									
								}
								
								if (viTriSpinner == 1){
									
									boolean kq = userInstall.delete_install(caiDatTaiKhoan.get(position));
									if (kq)
										Toast.makeText(QuanLyChiTieu.this,"Delete done!", Toast.LENGTH_SHORT).show();
									else
										Toast.makeText(QuanLyChiTieu.this,"Delete failed!"
												, Toast.LENGTH_SHORT).show();
									
									caiDatTaiKhoan.remove(position);
									caiDatAdapter.notifyDataSetChanged();
								}
								
								
							}
						});

						alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						});
						
						alert.create().show();
					   }
				});
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
		// Phải xóa trước khi vào rồi mới tạo csdl nếu không group1 sẽ tự động thêm vào csdl
		/*---------------Thêm mặc định -----------------------*/
		String[] group1 = new String[]{"Tiền điện"
				,"Tiền điện thoại"
				,"Tiền nước"
				,"Ăn uống"
				,"Trả nợ","Nợ"
				,"Giải trí"
				, "Y tế sức khỏe"
				,"Từ thiện"
				+ ", quyên góp"
				,"Lương", "Lãi ngân hàng","Thu nhập khác" };
		
		for (int i = 0;i < group1.length; ++i)
			userInstall.insert_install(user_id, "PHAN NHOM", group1[i]);
		

		String[] group2 = new String[]{"Tiền mặt","Tiền tiết kiệm","Thẻ tín dụng"};
		for (int i = 0;i < group2.length; ++i)
			userInstall.insert_install(user_id, "TAI KHOAN", group2[i]);
		
		
		/*------------Thêm cài đặt----------------*/
		btnThemCaiDat 	= (Button) findViewById(R.id.btnThemCaiDat);
		edtThemCaiDat	= (EditText) findViewById(R.id.edtThemCaiDat);
		
		/*--------Create tab--------------*/
		createTab("THU CHI", R.id.thuchi);
		createTab("CÀI ĐẶT", R.id.caidat);
	}
	public void createTab(String title,int resource){
		tabs.setup();
		TabHost.TabSpec tab = tabs.newTabSpec(title);
		View v  = LayoutInflater.from(QuanLyChiTieu.this).inflate(R.layout.custom_tab, null);
		TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
		txtTitle.setText(title);
		tab.setContent(resource);
		tab.setIndicator(v);
		tabs.addTab(tab);
	}
	
	public void dialogThemGiaoDich(Context context){
		String sql = "";
		dialog = new Dialog(context);
		dialog.setTitle("Chọn thêm giao dịch");
		dialog.setContentView(R.layout.activity_themgiaodich);
		dialog.show();
		account 		    = (Spinner)dialog.findViewById(R.id.account);
		account.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				transition_account = caiDatTaiKhoanSpinner.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		group 				= (Spinner)dialog.findViewById(R.id.group);
		group.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				transition_name = caiDatPhanNhomSpinner.get(position); // tên phân nhóm làm tên giao dịch
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		typeTransition 		= (Spinner)dialog.findViewById(R.id.typeTransition);
		typeTransition.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				transition_type = caiDatLoaiGiaoDichSpinner.get(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		stateTransition 	= (Spinner)dialog.findViewById(R.id.stateTransition);
		stateTransition.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				transition_state = caiDatTinhTrangGiaoDich.get(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		edtMoney 			= (EditText) dialog.findViewById(R.id.edtMoney);
		edtTransitionFuture = (EditText) dialog.findViewById(R.id.edtTransitionFuture);
		btnDay 				= (Button) dialog.findViewById(R.id.btnDay);
		btnSave 			= (Button) dialog.findViewById(R.id.btnSave);
		btnCancel 			= (Button) dialog.findViewById(R.id.btnCancel);
		txtTime				= (TextView) dialog.findViewById(R.id.txtTime);
		btnDay.setOnClickListener(this);
		btnSave.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		// Phải xóa hết trước add spinner vì mỗi lần show 
		// là mỗi lần caiDatTaiKhoanSpinner cập nhật, làm cho dữ liệu trùng lặp
		caiDatLoaiGiaoDichSpinner.removeAll(caiDatLoaiGiaoDichSpinner);
		caiDatTinhTrangGiaoDich.removeAll(caiDatTinhTrangGiaoDich);
		caiDatPhanNhomSpinner.removeAll(caiDatPhanNhomSpinner);
		caiDatTaiKhoanSpinner.removeAll(caiDatTaiKhoanSpinner);
				
		nguoidung = new NguoidungDao(QuanLyChiTieu.this);
		nguoidungdangky = nguoidung.getIdUser(user);
		final int user_id = nguoidungdangky.get(0).getId();
		// Tài khoản
		sql = "SELECT * FROM install WHERE user_id = "+user_id+" AND install_type like 'TAI KHOAN'";
		listInstallSpinner = userInstall.getListInstall(sql);
		for(int i = 0;i < listInstallSpinner.size(); ++i)
		{
				install in = listInstallSpinner.get(i);
				typeNameSpinner = in.getInstall_name();
				caiDatTaiKhoanSpinner.add(typeNameSpinner);
		}
			adapterSpinnerTaiKhoan = new ArrayAdapter<String>(QuanLyChiTieu.this
					, android.R.layout.simple_spinner_item, caiDatTaiKhoanSpinner);
			adapterSpinnerTaiKhoan.setDropDownViewResource(android.R.layout.simple_list_item_checked);
			account.setAdapter(adapterSpinnerTaiKhoan);
		
		// Phân nhóm	
		sql = "SELECT * FROM install WHERE user_id = "+user_id+" AND install_type like 'PHAN NHOM'";
		listInstallSpinner = userInstall.getListInstall(sql);
		for(int i = 0;i < listInstallSpinner.size(); ++i)
		{
			install in = listInstallSpinner.get(i);
			typeNameSpinner = in.getInstall_name();
			caiDatPhanNhomSpinner.add(typeNameSpinner);
		}
		adapterSpinnerGroup = new ArrayAdapter<String>(QuanLyChiTieu.this
					, android.R.layout.simple_spinner_item, caiDatPhanNhomSpinner);
		adapterSpinnerGroup.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		group.setAdapter(adapterSpinnerGroup);	
		
		
		// Loại giao dịch
		caiDatLoaiGiaoDichSpinner.add("Khoản thu");
		caiDatLoaiGiaoDichSpinner.add("Khoản chi");
		adapterSpinnerLoaiGiaoDich = new ArrayAdapter<String>(QuanLyChiTieu.this
				, android.R.layout.simple_spinner_item, caiDatLoaiGiaoDichSpinner);
		adapterSpinnerLoaiGiaoDich.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		typeTransition.setAdapter(adapterSpinnerLoaiGiaoDich);
		// Tình trạng giao dịch
		caiDatTinhTrangGiaoDich.add("Đã trả");
		caiDatTinhTrangGiaoDich.add("Nợ");
		caiDatTinhTrangGiaoDich.add("Ghi nợ");
		caiDatTinhTrangGiaoDich.add("Chờ");
		caiDatTinhTrangGiaoDich.add("Thành công");
		caiDatTinhTrangGiaoDich.add("Không thành công");
		adapterSpinnerstateTransition = new ArrayAdapter<String>(QuanLyChiTieu.this
				, android.R.layout.simple_spinner_item, caiDatTinhTrangGiaoDich); 
		adapterSpinnerstateTransition.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		stateTransition.setAdapter(adapterSpinnerstateTransition);
		
	}
	@Override
	public void onClick(View v) {
		Intent intent;
		Bundle b;
		nguoidung = new NguoidungDao(QuanLyChiTieu.this);
		nguoidungdangky = nguoidung.getIdUser(user);
		final int id_user   = nguoidungdangky.get(0).getId();
		switch (v.getId())
		{
		case R.id.btnthemgiaodich:
			dialogThemGiaoDich(QuanLyChiTieu.this);
			break;
		case R.id.btnhomnay:
			intent = new Intent(QuanLyChiTieu.this, ThongTinGiaoDich.class);
			b = new Bundle();
			b.putInt("user_id", id_user);
			b.putString("user", user);
			b.putString("id", "homnay");
			intent.putExtras(b);
			startActivity(intent);
			
			break;
		case R.id.btnTuan:
			 intent = new Intent(QuanLyChiTieu.this, ThongTinGiaoDich.class);
			 b = new Bundle();
			 b.putInt("user_id", id_user);
			 b.putString("user", user);
			 b.putString("id", "tuan");
			intent.putExtras(b);
			startActivity(intent);
			
			break;
		case R.id.btnThang:
			intent = new Intent(QuanLyChiTieu.this, ThongTinGiaoDich.class);
			b = new Bundle();
			b.putInt("user_id", id_user);
			b.putString("user", user);
			b.putString("id", "thang");
			intent.putExtras(b);
			startActivity(intent);
			
			break;
		case R.id.btnNam:
			intent = new Intent(QuanLyChiTieu.this, ThongTinGiaoDich.class);
			b = new Bundle();
			b.putInt("user_id", id_user);
			b.putString("user", user);
			b.putString("id", "nam");
			intent.putExtras(b);
			startActivity(intent);
			
			break;
		case R.id.btnDeNghi:
			intent = new Intent(QuanLyChiTieu.this, DeNghiChiTieu.class);
			b = new Bundle();
			b.putInt("user_id", id_user);
//			b.putString("user", user);
//			b.putString("id", "nam");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.btnDay:
			final Calendar calendar 				  = Calendar.getInstance();
			final SimpleDateFormat simpleDateFormat   = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			final Date date								  = new Date();
			DatePickerDialog datePickerDialog 
						= new DatePickerDialog(QuanLyChiTieu.this, new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								Calendar calendarStart = Calendar.getInstance();
								calendarStart.set(year, monthOfYear, dayOfMonth);
								txtTime.setText(simpleDateFormat.format(calendarStart.getTime()).toString());
							}

						}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
			datePickerDialog.setTitle("Please choose time");
			datePickerDialog.show();
			break;
		case R.id.btnSave:
		 
			if (edtMoney.getText().toString().matches("[0-9]{1,}") && !txtTime.getText().toString().matches("")){
				Vector<Integer> vector = new Vector<Integer>();
				Random random1 = new Random();
				Random random2 = new Random();
				int type_id1 = random1.nextInt(1999999999);
				int type_id2 = random1.nextInt(1999999999);
				int typeId  = type_id1 + type_id2;
				
				nguoidung = new NguoidungDao(QuanLyChiTieu.this);
				nguoidungdangky = nguoidung.getIdUser(user);
				final int user_id = nguoidungdangky.get(0).getId();
				double transition_money = Double.valueOf(edtMoney.getText().toString());
				String future = edtTransitionFuture.getText().toString();
				String transition_date = txtTime.getText().toString();
				boolean kqtype = false;
				
				if (transition_type.equals("Khoản thu"))
					kqtype = giaoDich.insert_loaigiaodich(typeId, transition_money, 0.0, transition_date);
				else if (transition_type.equals("Khoản chi"))
					kqtype = giaoDich.insert_loaigiaodich(typeId, 0.0, transition_money, transition_date);
				
				boolean kq = giaoDich.insert_giaodich(user_id, typeId, transition_name,transition_account ,transition_date , transition_state, transition_money);
				
				if (kq && kqtype)
					Toast.makeText(getApplicationContext(), "Add success", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), "Add failed", Toast.LENGTH_SHORT).show();
				/*----------------------------------------------------------------*/
				// Kết quả giao dịch
				ArrayList<GiaoDich> listGiaoDich = giaoDich.getAccount(user_id);
				
				for (GiaoDich soGiaoDich : listGiaoDich)
				{
//					Toast.makeText(getApplicationContext(), soGiaoDich.getType_id() + "", Toast.LENGTH_SHORT).show();
					if (soGiaoDich.gettransition_account().equals("Tiền mặt"))
					{
						tienmat = tienmat + soGiaoDich.getTransition_money();
					}
					if (soGiaoDich.gettransition_account().equals("Tiền tiết kiệm"))
					{
						tientietkiem = tientietkiem + soGiaoDich.getTransition_money();
					}
					if (soGiaoDich.gettransition_account().equals("Thẻ tín dụng"))
					{
						thetindung = thetindung + soGiaoDich.getTransition_money();
					}
				}
				
				account_money = new ArrayList<accountMoney>();
				account_money.add(new accountMoney("Tiền mặt", tienmat));
				account_money.add(new accountMoney("Tiết kiệm", tientietkiem));
				account_money.add(new accountMoney("Thẻ tín dụng", thetindung));
				thuchi_adapter = new thuchiAdapter(QuanLyChiTieu.this, R.layout.activity_account_money, account_money);
				listTypeMoney.setAdapter(thuchi_adapter);
				
				
				// Số dư
				double thu = 0;
				double chi = 0;
				ArrayList<LoaiGiaoDich> loaiGiaoDich = null;
				for (GiaoDich soGiaoDich : listGiaoDich)
				{
					loaiGiaoDich = giaoDich.getMoney(Integer.parseInt(soGiaoDich.getType_id()));
					for (LoaiGiaoDich loaigiaodich: loaiGiaoDich)
					{
						thu += loaigiaodich.getThu();
						chi += loaigiaodich.getChi();
					}
				}
				txtSoDu.setText((thu - chi) +"");
				/*----------------------------------------------------------------*/
				dialog.dismiss();
			}
			else
				Toast.makeText(getApplicationContext(), "Vui lòng chọn ngày tháng và nhập số tiền", Toast.LENGTH_SHORT).show();
			
			
			
			
			break;
		case R.id.btnCancel:
			dialog.dismiss();
			break;
			
		}
		
		// transition2 sẽ thực hiện sau khi transition2 hoàn tắt
		overridePendingTransition(R.anim.transition1, R.anim.transition2);
	}
	
	public void deleteDatabaseBeforein(){
		userInstall = new User_install(QuanLyChiTieu.this);
		String[] group = new String[]{"Tiền điện"
				,"Tiền điện thoại"
				,"Tiền nước"
				,"Ăn uống"
				,"Trả nợ","Nợ"
				,"Giải trí"
				, "Y tế sức khỏe"
				,"Từ thiện"
				+ ", quyên góp"
				,"Lương", "Lãi ngân hàng"
				,"Thu nhập khác", "Tiền mặt"
				,"Tiền tiết kiệm","Thẻ tín dụng"};
		for (int i = 0;i < group.length; ++i)
			userInstall.delete_install(group[i]);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// Phải xóa khi nhấn back vì nếu không làm sẽ add thêm dữ liệu QuanLyChiTieu xuat hien
		deleteDatabaseBeforein();
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
	
}
