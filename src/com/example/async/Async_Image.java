package com.example.async;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class Async_Image extends AsyncTask<String, Void, Bitmap>{
	ImageView img;
	public Async_Image(ImageView img) {
		this.img = img;
	}
	@Override
	protected Bitmap doInBackground(String... params) {
		String url = params[0];
		URL urlDownload = null;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		Bitmap bitmap = null;
		try {
			urlDownload = new URL(url);
			connection = (HttpURLConnection) urlDownload.openConnection();
			connection.setConnectTimeout(5000);
			connection.connect();
			inputStream = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bitmap;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		img.setImageBitmap(result);
	}

}
