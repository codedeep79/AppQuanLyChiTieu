package com.example.quanlychitieu;

import com.example.nguoidung.NguoidungDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePassword extends Activity {
	EditText edtPass1, edtPass2, edtEmail;
	NguoidungDao nguoidung;
	Button btnConfirm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_password);
		init();

		final String email = getIntent().getStringExtra("email").toLowerCase();
		edtEmail.setText(email);
		
		btnConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean kq = nguoidung.updatePassword(email, edtPass1.getText().toString().toLowerCase());
				if (kq){
					Toast.makeText(getApplicationContext(), "Update success", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(UpdatePassword.this, MainActivity.class);
					startActivity(intent);
				}
				else
					Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void init(){
		edtPass1 = (EditText) findViewById(R.id.edtPass1);
		edtPass2 = (EditText) findViewById(R.id.edtPass2);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		nguoidung = new NguoidungDao(getApplicationContext());
	}
}
