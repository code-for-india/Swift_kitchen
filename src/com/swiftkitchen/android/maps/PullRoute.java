package com.swiftkitchen.android.maps;

	import android.app.Service;
	import android.content.Context;
	import android.content.Intent;
	import android.os.AsyncTask;
	import android.os.IBinder;
	import android.util.Log;

	public class PullRoute extends Service {

		// Context a = this;
		Context mContext;
		asyn WorkinBackGround;
		RestService restServiceG;

		public static String srvr = "swiftkitchen.herokuapp.com/optimize/device001";


		@Override
		public IBinder onBind(Intent arg0) {
			return null;
		} 

		@Override
		public void onCreate() {
			super.onCreate();
			Log.d("PULL Service", "@inside oncreate intent and thread id is..."
					+ Thread.currentThread().getId());
			mContext = this;
			WorkinBackGround = new asyn();
			WorkinBackGround.execute();

		}

		public class asyn extends AsyncTask<Void, Void, Void> {

			@Override
			protected Void doInBackground(Void... params) {

				// String url = "http://192.168.0.130/channels.txt";
				String url = "http://" + srvr ;
				Log.d("PullRoute",
						"url://"+url);
				restServiceG = new RestService(mContext, url, RestService.GET,
						"channels");
				restServiceG.addParam("lat", "40764917"); // Add params to request
				restServiceG.addParam("lng", "-73983130");// Add headers to request
				restServiceG.addParam("range", "10");
				restServiceG.addHeader("Content-Type", "application/json");
				try {
					Log.d("PullRoute",
							"calling rest execute");
					restServiceG.execute(); // Executes the request with the HTTP
											// GET
											// verb
					Log.d("PullRoute",
							"calling rest execute");
					
				} catch (Exception e) {
					Log.d("PullRoute",
							"calling rest execute At Exception");
					e.printStackTrace();
				}

				Log.d("madilservice",
						"@#@ inside onhandle intent and thread id is..."
								+ Thread.currentThread().getId());
				return null;

			}
		}
	}