package com.swiftkitchen.android.maps;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * This is an Android Service to provide REST functionality outside of the Application thread.
 */

public class RestService{

	private ArrayList <ParcelableNameValuePair> params;
	private ArrayList <ParcelableNameValuePair> headers;


	private String url;
	private Context mContext;
	private int RequestType;
	private String entity, parsingClass;

	public final static int GET = 1;
	public final static int POST = 2;
	public final static int PUT = 3;
	public final static int DELETE = 4;

	/**
	 * Class constructor. Requires a Handler (to receive a result), a Context (to launch the service), and
	 * a Url (to specify where to make the request).
	 * <p>
	 * In this implementation, name is written as a simple string and value as a UTF-8 Url Encoded Value.
	 *
	 * @param	mHandler	The handler used to receive a response. Response is given to the msg object accessed in an Overridden handleMessage.
	 * @param	mContext	The context used to execute the service. Suggested to use getApplicationContext() to prevent leaks.
	 * @param	url			The url on which to execute the request.
	 */

	public RestService(Context mContext, String url, int RequestType, String parsingClass){
		this.mContext = mContext;
		this.url = url;
		this.parsingClass = parsingClass;
		this.RequestType = RequestType;
		params = new ArrayList<ParcelableNameValuePair>();
		headers = new ArrayList<ParcelableNameValuePair>();
		Log.i("App V1", "2..Inside the Rest Service..");
	}

	/**
	 * Appends a parameter to the URL supplied by the constructor. 
	 * <p>
	 * In this implementation, name is written as a simple string and value as a UTF-8 Url Encoded Value.
	 *
	 * @param	name	The name associated with the name/value pair for the url argument
	 * @param	value	The value associated with the name/value pair for the url argument
	 */

	public void addParam(String name, String value){
		params.add(new ParcelableNameValuePair(name, value));
	}

	/**
	 * Appends a header to the URL supplied by the constructor
	 * <p>
	 * @param	name	The name associated with the name/value pair for the url header
	 * @param	value	The value associated with the name/value pair for the url header
	 */

	public void addHeader(String name, String value){
		headers.add(new ParcelableNameValuePair(name,value));
	}


	/**
	 * Directly sets the HTTPEntity of a POST Request. WARNING! This will override all params set by the request.
	 * <p>
	 * @param	entity	The string used to be set as the entity of the request
	 */
	public void setEntity(String entity){
		this.entity = entity;
	}

	/**
	 * Executes the current URL as a REST request in an IntentService. The method argument 
	 * specifies the HTTP Verb to use. Must use either RestService.GET or RestService.POST
	 * <p>
	 * The return string will be provided in the HandleMessage method of the handler provided by
	 * the constructor. It will be present as the object field of the incoming message. 
	 * <p>
	 * @param	method	The type of HTTP request to make. Can be either RestService.GET or RestService.POST
	 */



	public void execute() {
		Log.i("App V1", "2..Inside the Rest Service Execute method..");
//	     ResultReceiver receiver;
//	     receiver = new ResultReceiver(mHandler){
//    	    @Override
//    	    protected void onReceiveResult(int resultCode, Bundle resultData) {
//    	    	Log.d("restservice", "bhai andar aa raha hai ... ");
//    	    	if (mHandler == null)
//    	    		Log.d("restservice", "mHandler is null ... ");
//				else
//    	    		mHandler.obtainMessage(0,0,0,resultData.getString("result")).sendToTarget();
//    	    }
//	     };
	     Log.i("App V1", "3..Inside the Rest Service Execute method..");
	     final Intent intent = new Intent(mContext, ExecuteRequest.class);
	     intent.putParcelableArrayListExtra("headers", headers);
	     intent.putExtra("params", params);
	     intent.putExtra("url", url);
	     intent.putExtra("parsingClass", parsingClass);
	     intent.putExtra("method", RequestType);
	     intent.putExtra("entity", entity);
	     mContext.startService(intent);
	     Log.i("App V1", "........at the end of restService......");
	}
}

