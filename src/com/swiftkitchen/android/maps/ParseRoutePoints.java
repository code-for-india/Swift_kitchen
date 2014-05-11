package com.swiftkitchen.android.maps;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ParseRoutePoints {

	private JSONObject jsonObject;
	String strJSONValue;
	String strParsedValue = null;
	RoutePoints wayPointList = null;

	public ParseRoutePoints(String str) {
		Log.i("App V1", "1..Inside the Channel Parsing class..");
		System.out.println(str);
		strJSONValue = str;
	}

	public RoutePoints parseJSON() throws JSONException {
		RoutePoints wayPoints = new RoutePoints();
		//strJSONValue = "{\"test\":[{\"id\":17,\"name\":\"MALLIKARJUNA HPS-A K COLONY (1-5)\",\"longitude\":\"77.611350\",\"latitude\":\"13.008345\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.091Z\",\"updated_at\":\"2014-05-10T16:57:45.091Z\"},{\"id\":18,\"name\":\"MALLIKARJUNA HS-A K COLONY (9-10)\",\"longitude\":\"77.611350\",\"latitude\":\"13.008345\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.097Z\",\"updated_at\":\"2014-05-10T16:57:45.097Z\"},{\"id\":19,\"name\":\"GTAHPS-PERIYAR NAGAR (1-5)\",\"longitude\":\"77.610882\",\"latitude\":\"13.008860\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.106Z\",\"updated_at\":\"2014-05-10T16:57:45.106Z\"},{\"id\":20,\"name\":\"GUHPS-ZIA STREET, D J HALLI (1-5)\",\"longitude\":\"77.609743\",\"latitude\":\"13.009242\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.111Z\",\"updated_at\":\"2014-05-10T16:57:45.111Z\"},{\"id\":21,\"name\":\"GUHS-MAIN BULDING, D J HALLI (9-10)\",\"longitude\":\"77.605800\",\"latitude\":\"13.010393\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.117Z\",\"updated_at\":\"2014-05-10T16:57:45.117Z\"},{\"id\":22,\"name\":\"GUHPS-MAIN BULDING, D J HALLI (1-5)\",\"longitude\":\"77.605800\",\"latitude\":\"13.010393\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.124Z\",\"updated_at\":\"2014-05-10T16:57:45.124Z\"},{\"id\":23,\"name\":\"GULPS-MODI ROAD D J HALLI (1-5)\",\"longitude\":\"77.606017\",\"latitude\":\"13.012182\",\"route_id\":2,\"created_at\":\"2014-05-10T16:57:45.140Z\",\"updated_at\":\"2014-05-10T16:57:45.140Z\"}]}";
		if (strJSONValue != null) {
			jsonObject = new JSONObject(strJSONValue);
			Log.d("Parsing", "Inside Parsing Function");
			JSONArray subArray = jsonObject.getJSONArray("routes");
			//jsonObject.getJSONArray("");

			for (int i = 0; i < subArray.length(); i++) {
				// Log.d("Parsing", "1Inside for loop length.."+i);
				int id = 0;
				String name = null;
				double longitude = 00.00;
				double latitude = 00.00;
				int route_id = 0;
				String created_at = null;
				String updated_at = null;

				if (!subArray.getJSONObject(i).isNull("id"))
					id = subArray.getJSONObject(i).getInt("id");
				if (!subArray.getJSONObject(i).isNull("name"))
					name = subArray.getJSONObject(i).getString("name")
							.toString();
				if (!subArray.getJSONObject(i).isNull("longitude"))
					longitude = subArray.getJSONObject(i)
							.getDouble("longitude");
				if (!subArray.getJSONObject(i).isNull("latitude"))
					latitude = subArray.getJSONObject(i).getDouble("latitude");
				if (!subArray.getJSONObject(i).isNull("route_id"))
					route_id = subArray.getJSONObject(i).getInt("route_id");
				if (!subArray.getJSONObject(i).isNull("created_at"))
					created_at = subArray.getJSONObject(i)
							.getString("created_at").toString();
				if (!subArray.getJSONObject(i).isNull("updated_at"))
					updated_at = subArray.getJSONObject(i)
							.getString("updated_at").toString();
				Log.d("Parsing", "Id: " + id + ", Name: " + name + ", long: "
						+ longitude + ", latitute: " + latitude);
				if (wayPoints != null) {
					wayPoints.addPoint(new Point(id, name, longitude, latitude,
						route_id));
					Log.d("Parsing", "Adding points");
				}
			}
			Log.d("Parsing", "returning from parsing class");
		}
		return wayPoints;
	}
}
