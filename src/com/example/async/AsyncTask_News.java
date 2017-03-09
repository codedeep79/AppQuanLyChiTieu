package com.example.async;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.adapter.AdapterNews;
import com.example.entity.News;
import com.example.quanlychitieu.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

public class AsyncTask_News extends AsyncTask<String, Void, ArrayList<News>>{
	Activity activity;
	ListView listNews;
	AdapterNews adapter = null;
	TextView txtLoading;
	ArrayList<News> item;
	public AsyncTask_News(Activity activity) {
		this.activity = activity;
		listNews = (ListView) activity.findViewById(R.id.listNews);
		
		item = new ArrayList<News>();
		adapter = new AdapterNews(activity, R.layout.activity_adapter_news, item);
		listNews.setAdapter(adapter);
		txtLoading = (TextView) activity.findViewById(R.id.txtLoading);
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		txtLoading.setText("Loading...");
	}
	@Override
	protected ArrayList<News> doInBackground(String... params) {
		String url = params[0];
		ArrayList<News> listNews = new ArrayList<News>();
		URL urlDownload = null;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		try {
			urlDownload = new URL(url);
			connection  = (HttpURLConnection) urlDownload.openConnection();
			connection.setConnectTimeout(5000);
			connection.connect();
			int respondCode = connection.getResponseCode();
			if (respondCode == connection.HTTP_OK)
			{
				inputStream = connection.getInputStream();
				listNews    = readData(inputStream);
			}
			inputStream.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listNews;
	}

	@Override
	protected void onPostExecute(ArrayList<News> result) {
		super.onPostExecute(result);
		adapter.addAll(result);
		adapter.notifyDataSetChanged();
		txtLoading.setText("");
	}
	private ArrayList<News> readData(InputStream... inputStream) {
		ArrayList<News> listNews = new ArrayList<News>();
		String tagName 	  = null;
		String tabContent = null;
		News banTin       = null;
		boolean flag	  = false;
		InputStream in 	  = inputStream[0];
		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser 		 parser 			  = xmlPullParserFactory.newPullParser();
			parser.setInput(in, null);
			
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT)
			{
				switch(event)
				{
				case XmlPullParser.START_TAG:
					tagName = parser.getName();
					if (tagName.equals("item"))
					{
						banTin = new News();
						flag   = true;
					}
					break;
				case XmlPullParser.TEXT:
					tabContent = parser.getText();
					break;
				case XmlPullParser.END_TAG:
					tagName = parser.getName();
					if (tagName.equals("title") && flag)
						banTin.setTitle(tabContent);
					if (tagName.equals("pubDate") && flag)
						banTin.setPubDate(tabContent);
					if (tagName.equals("link") && flag)
						banTin.setLink(tabContent);
					if (tagName.equals("description") && flag)
					{
						String srcImage = null;
						String[] arrDes = tabContent.split("</br>");
						Pattern pattern = Pattern.compile("(.*)\\s*src\\s*=\\s*\"(.*\\.jpg)\"(.*)");
						Matcher matcher = pattern.matcher(arrDes[0]);
						if (matcher.matches())
						{
							srcImage = matcher.group(2);
						}
						banTin.setLinkImage(srcImage);
						banTin.setDescription(arrDes[1]);
						
						listNews.add(banTin);
					}
					break;
				}
				event = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listNews;
	}

}
