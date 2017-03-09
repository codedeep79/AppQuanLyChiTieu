package com.example.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.entity.News;
import com.example.entity.ngoaite;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

public class Chuyendoingoaite extends AsyncTask<String,Void, String>{
	Activity activity;
	public Chuyendoingoaite(Activity activity) {
		this.activity = activity;
	}
	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		ArrayList<News> listNews = null;
		URL urlDownload = null;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		String str = null;
		try {
			urlDownload = new URL(url);
			connection  = (HttpURLConnection) urlDownload.openConnection();
			connection.setConnectTimeout(5000);
			connection.connect();
			int respondCode = connection.getResponseCode();
			if (respondCode == connection.HTTP_OK)
			{
				inputStream = connection.getInputStream();
				str = convertStreamToString(inputStream);
			}
			inputStream.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
//		Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
		Matcher matcher = Pattern.compile("(.*)code=\"(.*)\".*Name=\"(.*)\".*Buy=\"(.*)\".*Transfer=\"(.*)\".*Sell=\"(.*)\"").matcher(result);
		while (matcher.matches())
		{
			Toast.makeText(activity, matcher.group(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public String convertStreamToString(InputStream inputStream)
	{
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while ((line = reader.readLine()) != null)
			{
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

}
