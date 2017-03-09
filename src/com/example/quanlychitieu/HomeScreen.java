package com.example.quanlychitieu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeScreen extends Activity implements OnClickListener{
	ImageButton btnquanlychitieu, btngiacathitruong, btnquantriadmin; 
	ImageView imgSlide;
	AnimationDrawable animationDrawable;
	String user = "";
	String pass = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		init();
		user = getIntent().getExtras().getString("user").trim();
		pass = getIntent().getExtras().getString("pass");
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}
	public void init(){
		imgSlide = (ImageView) findViewById(R.id.slide);
		imgSlide.setBackgroundResource(R.drawable.slide);
		animationDrawable = (AnimationDrawable) imgSlide.getBackground();
		if (!animationDrawable.isRunning()){
			animationDrawable.start();
		}
		
		btnquanlychitieu  = (ImageButton) findViewById(R.id.btnquanlychitieu);
		btngiacathitruong =(ImageButton) findViewById(R.id.btngiacathitruong);
		btnquantriadmin   =(ImageButton) findViewById(R.id.btnquantriadmin);
		btnquanlychitieu.setOnClickListener(this);
		btngiacathitruong.setOnClickListener(this);
		btnquantriadmin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
			case R.id.btnquanlychitieu:
				intent = new Intent(HomeScreen.this, QuanLyChiTieu.class);
				Bundle b = new Bundle();
				b.putString("user", user);
				b.putString("pass", pass);
				intent.putExtras(b);
				startActivity(intent);
				break;
			case R.id.btngiacathitruong:
				intent = new Intent(HomeScreen.this, GiaCaThiTruong.class);
				startActivity(intent);
				break;
			case R.id.btnquantriadmin:
				if (user.equalsIgnoreCase("admin@gmail.com") 
							&& pass.equals("admin"))
				{
					intent = new Intent(HomeScreen.this, QuanTriAdmin.class);
					startActivity(intent);
				}		
				else
					Toast.makeText(HomeScreen.this, 
							"Bạn phải đăng nhập quyền admin. Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
				break;
		}
		
		overridePendingTransition(R.anim.transition1, R.anim.transition2);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
