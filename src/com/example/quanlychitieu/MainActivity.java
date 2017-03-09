package com.example.quanlychitieu;

import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	EditText edtUser, edtPass;
	Button btnLogin, btnSignup, btnforgotPass;
	CheckBox rememberPass;
	NguoidungDao nguoidung;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	public void init(){
		edtUser = (EditText) findViewById(R.id.edtUser);
		edtPass = (EditText) findViewById(R.id.edtPass);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnSignup = (Button) findViewById(R.id.btnSignup);
		btnforgotPass = (Button) findViewById(R.id.btnforgotPass);
		rememberPass = (CheckBox) findViewById(R.id.rememberPass);
		/*-----setOnClickListener------------*/
		btnLogin.setOnClickListener(this);
		btnSignup.setOnClickListener(this);
		btnforgotPass.setOnClickListener(this);
		
		nguoidung = new NguoidungDao(MainActivity.this);
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
			case R.id.btnSignup:
				Intent intent1 = new Intent(MainActivity.this, Signup.class);
				startActivity(intent1);
				break;
			case R.id.btnLogin: 
				if(nguoidung.checklogin(edtUser.getText().toString().toLowerCase().trim()
								, edtPass.getText().toString())){
					Intent intent = new Intent(MainActivity.this, HomeScreen.class);
					Bundle b = new Bundle();
					b.putString("user", edtUser.getText().toString());
					b.putString("pass", edtPass.getText().toString());
					intent.putExtras(b);
					startActivity(intent);
				}	
				else
					Toast.makeText(getApplicationContext(), "Login failed. Please try again.\n You can wrong password or username. ", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnforgotPass:
				Intent intent2 = new Intent(MainActivity.this, ForgotPassword.class);
				startActivity(intent2);
				break;
		}
		overridePendingTransition(R.anim.transition1, R.anim.transition2);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// Behavior as home press
		moveTaskToBack(true);
		
	}
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
