package com.swiftkitchen.android.maps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FirstPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("MAPS-RAJ", "Just On Create FirstPage...");
		
		setContentView(R.layout.main);
		
		/*
		TextView orderButton = (TextView) findViewById(R.id.tv_guide);
		orderButton.setText("TvGuide");
		orderButton.setFocusable(true);
		orderButton.requestFocus();
		
		TextView orderButton4 = (TextView) findViewById(R.id.vod_text);
		orderButton4.setText("VoD");

		TextView orderButton1 = (TextView) findViewById(R.id.favorite);
		orderButton1.setText("Favorite");

		TextView orderButton2 = (TextView) findViewById(R.id.setting_text);
		orderButton2.setText("Settings"); 
		*/
	}
	
}
