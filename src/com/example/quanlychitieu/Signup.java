package com.example.quanlychitieu;

import com.example.database.database;
import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Signup extends Activity implements OnClickListener{
	EditText edtLastName, edtFirstName, edtEmail, edtPass, edtAddress, edtPhone;
	Button btnSave, btnBack;
	database dbUser;
	String username, password, firstname, lastname, email, address, phonenumber;
	NguoidungDao nguoidung;
	RadioButton rdNam, rdNu;
	String gender;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		init();
		controlEvent();
	}
	
	
	public void infoSignup(){
		// username là địa chỉ email
		lastname 	= edtLastName.getText().toString().toLowerCase();
		firstname 	= edtFirstName.getText().toString().toLowerCase();
		username 	= edtEmail.getText().toString().toLowerCase();
		email 		= edtEmail.getText().toString().toLowerCase();
		password 	= edtPass.getText().toString().toLowerCase();
		address 	= edtAddress.getText().toString().toLowerCase();
		phonenumber = edtPhone.getText().toString().toLowerCase();
		if(rdNam.isChecked())
			gender = rdNam.getText().toString();
		else
			gender = rdNu.getText().toString();
		
	}
	public void controlEvent(){
		btnSave.setOnClickListener(this);
		btnBack.setOnClickListener(this);
	}
	public void init(){
		edtLastName = (EditText) findViewById(R.id.edtLastName);
		edtFirstName = (EditText) findViewById(R.id.edtFirstName);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtPass = (EditText) findViewById(R.id.edtPass);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		edtPhone = (EditText) findViewById(R.id.edtPhone);
		btnSave =(Button) findViewById(R.id.btnSave);
		btnBack = (Button) findViewById(R.id.btnBack);
		rdNam   = (RadioButton) findViewById(R.id.rdNam);
		rdNu    = (RadioButton) findViewById(R.id.rdNu);
		dbUser = new database(Signup.this);
		nguoidung = new NguoidungDao(Signup.this);
	}


	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btnSave:
				infoSignup();
				if (!lastname.matches("[A-z\\s]+") || !firstname.matches("[A-z\\s]+") || !phonenumber.matches("[0-9]{1,13}"))
				{
					String notification = "Please try again.\nLast name and firstname "
							+ "is not contain number and special character.\n Phone number have to contain number 0-9"
							+ "	and maximun is 11 number ";
					Toast.makeText(getApplicationContext(), notification, Toast.LENGTH_LONG).show();
					return;
				}
				else
				{
					boolean result = nguoidung.insert(username, password, firstname, lastname, email, address, phonenumber, gender);
					if (result) 
						Toast.makeText(Signup.this, "Signup succcess", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(Signup.this, "Signup error. Email is entered distinct. Please try again", Toast.LENGTH_SHORT).show();
				}
				
			break;
		case R.id.btnBack:
			finish();
			break;
		}
		
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
