package com.swiftkitchen.android.maps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	static LatLng school1; // = new LatLng(53.558, 9.927);
	static LatLng school2;// = new LatLng(53.551, 9.993);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("MAPS-RAJ", "Just On Create nothing...");

		setContentView(R.layout.activity_main);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		try {
			school1 = new LatLng(13.051405, 77.487560);
			school2 = new LatLng(13.051405, 77.487560);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Marker hamburg = map.addMarker(new MarkerOptions().position(school1).title("school1"));
			
		Marker kiel = map.addMarker(new MarkerOptions()
        .position(school2)
        .title("school2")
        .snippet("school2 is cool")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(school1, 15));
		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}