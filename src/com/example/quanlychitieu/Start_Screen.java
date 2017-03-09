package com.example.quanlychitieu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class Start_Screen extends Activity {
	Handler handler;
	ProgressBar progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start__screen);
		progress = (ProgressBar) findViewById(R.id.progress);
		handler = new Handler();
		progress.setProgress(0);
		progress.setIndeterminate(false);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				progress.setProgress(progress.getProgress() + 1);
				handler.postDelayed(this, 70);	
				if (progress.getProgress() == 100)
				{
					Intent intent = new Intent(Start_Screen.this, MainActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.scale2, R.anim.scale1);
					handler.removeCallbacks(this);
				}
						
			}
		}, 300);
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		progress.setProgress(0);
		progress.setIndeterminate(false);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				progress.setProgress(progress.getProgress() + 1);
				handler.postDelayed(this, 70);	
				if (progress.getProgress() == 100)
				{
					Intent intent = new Intent(Start_Screen.this, MainActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.scale2, R.anim.scale1);
					handler.removeCallbacks(this);
				}
						
			}
		}, 300);
	}
}
