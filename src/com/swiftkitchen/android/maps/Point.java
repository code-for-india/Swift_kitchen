package com.swiftkitchen.android.maps;

public class Point {

	int id = 0;
	String name = null;
	double longitude = 00.00;
	double latitude = 00.00;
	int route_id = 0;

	public Point(int id, String name, double longitude, double latitude,
			int route_id) {
		super();
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.route_id = route_id;
	}
}
