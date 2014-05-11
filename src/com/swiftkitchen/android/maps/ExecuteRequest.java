package com.swiftkitchen.android.maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ExecuteRequest extends IntentService {

	int responseCode;
	int method;
	String message, entity, parsingClass;
	ArrayList<ParcelableNameValuePair> params;
	ArrayList<ParcelableNameValuePair> headers;
	HttpRequestBase request;

	public static boolean isFinished = false;

/*	parsingChannel obj0;
	parsingEvents obj1;
	parsingGenre obj3;
	List<All_Channels> channelList = new ArrayList<All_Channels>();
	List<All_Event> eList = new ArrayList<All_Event>();
	List<All_Event> updatelist = new ArrayList<All_Event>();
	List<Genre> genreList = new ArrayList<Genre>();*/

	private String url;

	public ExecuteRequest() {
		super("executeRestRequest");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		params = intent.getParcelableArrayListExtra("params");
		headers = intent.getParcelableArrayListExtra("headers");
		url = intent.getStringExtra("url");
		parsingClass = intent.getStringExtra("parsingClass");
		method = intent.getIntExtra("method", 1);
		entity = intent.getStringExtra("entity");
		try {
			execute(method);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void execute(int method) throws Exception {
		switch (method) {
		case RestService.GET: {
			String combinedParams = "";
			if (!params.isEmpty()) {
				combinedParams += "?";
				for (NameValuePair p : params) {
					String paramString = p.getName() + "="
							+ URLEncoder.encode(p.getValue(), "UTF-8");
					if (combinedParams.length() > 1) {
						combinedParams += "&" + paramString;
					} else {
						combinedParams += paramString;
					}
				}
			}
			request = new HttpGet(url + combinedParams);

			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}

			commit();
			break;
		}
		case RestService.POST: {
			request = new HttpPost(url);

			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}

			if (!params.isEmpty()) {
				((HttpPost) request).setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			}
			if (entity != null && entity != "") {
				StringEntity se = new StringEntity(entity);
				((HttpPost) request).setEntity(se);
			}
			commit();
			break;
		}
		case RestService.PUT: {
			request = new HttpPut(url);

			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}

			if (!params.isEmpty()) {
				((HttpPut) request).setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			}
			commit();
			break;
		}
		case RestService.DELETE: {
			request = new HttpDelete(url);

			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}
			commit();
		}

		}
	}

	private void commit() {
		HttpClient client = new DefaultHttpClient();
		Log.i("App V1", "4..Inside the Execute Request.java commit..");
		HttpResponse httpResponse;

		try {
			
			httpResponse = client.execute(request);
			responseCode = httpResponse.getStatusLine().getStatusCode();
			message = httpResponse.getStatusLine().getReasonPhrase();
			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				String response = convertStreamToString(instream);
				
				Log.d("Execute Request", "HTTP Response: "+response);

				//putInDB(response, parsingClass);

				Bundle responseBundle = new Bundle();
				responseBundle.putString("result", response);
				
				try {
				ParseRoutePoints pRP = new ParseRoutePoints(response);
				GlobalConstant.routePoints = pRP.parseJSON();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Closing the input stream will trigger connection release
				instream.close();
			}

		} catch (ClientProtocolException e) {
			client.getConnectionManager().shutdown();
			e.printStackTrace();
		} catch (IOException e) {
			client.getConnectionManager().shutdown();
			e.printStackTrace();
		}
	}


	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		Log.i("App V1", "8..Inside the Execute Request.java commit..");
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
