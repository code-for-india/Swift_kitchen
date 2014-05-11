package com.swiftkitchen.android.maps;

import java.util.ArrayList;
import java.util.List;

public class RoutePoints {

	private int count;
	private List<Point> pointsLists;
	private int route_id;

	public RoutePoints() {
		super();
		// TODO Auto-generated constructor stub
		pointsLists = new ArrayList<Point>();
	}

	public void addPoint(Point _pnt) {
		if (pointsLists != null) {
			pointsLists.add(_pnt);
			count = pointsLists.size();
		}
	}

}