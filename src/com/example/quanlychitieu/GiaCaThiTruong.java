package com.example.quanlychitieu;

import com.example.async.AsyncTask_News;
import com.example.async.Chuyendoingoaite;
import com.example.entity.News;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class GiaCaThiTruong extends Activity {
	TabHost tab;
	ListView listNews;
	News banTin;
	WebView thongTinGiaCa, ngoaiTe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gia_ca_thi_truong);
		init();
		createTab("THÔNG TIN GIÁ CẢ THỊ TRƯỜNG", R.id.giacathitruong);
		createTab("TƯ VẤN & TIN TỨC", R.id.tuvantintuc);
		createTab("CHUYỂN ĐỔI NGOẠI TỆ", R.id.chuyendoingoaite);
		if (checkConnect())
		{
			new AsyncTask_News(GiaCaThiTruong.this).execute("http://vnexpress.net/rss/kinh-doanh.rss");
			listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					News banTin = (News) parent.getAdapter().getItem(position);
					Intent intent = new Intent(GiaCaThiTruong.this, WebBrowser.class);
					Bundle b = new Bundle();
					b.putString("link", banTin.getLink());
					intent.putExtras(b);
					startActivity(intent);
					overridePendingTransition(R.anim.transition1, R.anim.transition2);
				}
			});
		}
		else if (!checkConnect())
			Toast.makeText(GiaCaThiTruong.this, "Turn on Wifi to use this app", Toast.LENGTH_LONG).show();
	}
	public void init(){
		tab = (TabHost) findViewById(R.id.myTabhost);
		listNews = (ListView) findViewById(R.id.listNews);
		thongTinGiaCa 	 = (WebView) findViewById(R.id.thongTinGiaCa);
		thongTinGiaCa.loadUrl("http://vietbao.vn/vn/gia-ca-thi-truong/");
		ngoaiTe 		 = (WebView) findViewById(R.id.ngoaiTe);
		ngoaiTe.loadUrl("http://webgia.com/ty-gia/");
		WebSettings setting_ngoai_te = ngoaiTe.getSettings();
		setting_ngoai_te.setBuiltInZoomControls(true);
		setting_ngoai_te.setDisplayZoomControls(false);
		setting_ngoai_te.setJavaScriptEnabled(true);
	}

	public void createTab(String title, int resource){
		tab.setup();
		TabHost.TabSpec tabHost = tab.newTabSpec("");
		tabHost.setIndicator(title);
		tabHost.setContent(resource);
		tab.addTab(tabHost);
	}
	
	private boolean checkConnect(){
		ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity != null) {
          NetworkInfo info1 = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
          NetworkInfo info2 = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
          if (info1 != null || info2 != null) {
              if (info1.isConnected() || info2.isConnected()) {
                  return true;
              }
          }
      }
      return false;
  }
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
