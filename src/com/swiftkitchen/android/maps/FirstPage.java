package com.swiftkitchen.android.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstPage extends Activity {

	static Button prevbutton = null;
	
	private OnClickListener myClickListener = new OnClickListener() {
	    
		public void onClick(View v) {
			int id = v.getId();
			Button button = (Button) findViewById(id);
			
			if(prevbutton != null) {
				prevbutton.setBackgroundColor(0xffffffff);
				prevbutton.setTextColor(0xff000000);
			}

			switch (id) {
			case R.id.pullRoute:
				button.setBackgroundColor(0xff000000);
				button.setTextColor(0xffffffff);
				Intent intent = new Intent(FirstPage.this, PullRoute.class);
				startService(intent);
				break;
			case R.id.startNav:
				button.setBackgroundColor(0xff000000);
				button.setTextColor(0xffffffff);
				break;
			case R.id.setting:
				button.setBackgroundColor(0xff000000);
				button.setTextColor(0xffffffff);
				break;
			}
			prevbutton = button;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("MAPS-RAJ", "Just On Create FirstPage...");

		setContentView(R.layout.main);

		Button buttonPR = (Button) findViewById(R.id.pullRoute);
		buttonPR.setOnClickListener(myClickListener);
		
		Button buttonSN = (Button) findViewById(R.id.startNav);
		buttonSN.setOnClickListener(myClickListener);
		
		Button buttonSettings = (Button) findViewById(R.id.setting);
		buttonSettings.setOnClickListener(myClickListener);

		/*
		 * TextView orderButton = (TextView) findViewById(R.id.tv_guide);
		 * orderButton.setText("TvGuide"); orderButton.setFocusable(true);
		 * orderButton.requestFocus();
		 * 
		 * TextView orderButton4 = (TextView) findViewById(R.id.vod_text);
		 * orderButton4.setText("VoD");
		 * 
		 * TextView orderButton1 = (TextView) findViewById(R.id.favorite);
		 * orderButton1.setText("Favorite");
		 * 
		 * TextView orderButton2 = (TextView) findViewById(R.id.setting_text);
		 * orderButton2.setText("Settings");
		 */
	}

}
