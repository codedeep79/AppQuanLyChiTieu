package com.example.quanlychitieu;

import com.example.nguoidung.NguoidungDao;
import com.example.quanlychitieu.ForgotPassword;
import com.example.quanlychitieu.UpdatePassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends Activity {
	EditText edtEmailAgain;
	Button btnEmailAgain;
	NguoidungDao nguoidung; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		nguoidung = new NguoidungDao(ForgotPassword.this);
		edtEmailAgain = (EditText) findViewById(R.id.edtEmailAgain);
		btnEmailAgain = (Button) findViewById(R.id.btnEmailAgain);
		btnEmailAgain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(getApplicationContext(), edtEmailAgain.getText().toString(), Toast.LENGTH_SHORT).show();
				boolean kq = nguoidung.checkemail(edtEmailAgain.getText().toString().toLowerCase());
				if (kq)
				{
					Intent intent = new Intent(ForgotPassword.this , UpdatePassword.class);
					intent.putExtra("email", edtEmailAgain.getText().toString());
					startActivity(intent);
					return;
				}
			}
		});
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}

	
}
