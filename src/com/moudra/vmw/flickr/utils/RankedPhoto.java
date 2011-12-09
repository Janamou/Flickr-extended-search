package com.moudra.vmw.flickr.utils;

import com.gmail.yuyang226.flickr.photos.Photo;

public class RankedPhoto implements Comparable<RankedPhoto> {
	private Photo photo;
	private double cost;
	private double distanceGeo;
	private double distanceString;
	private double distanceDate;
	
	public RankedPhoto(Photo photo, double cost, double distanceString, double distanceGeo, double distanceDate) {
		this.photo = photo;
		this.cost = cost;
		this.distanceString = distanceString;
		this.distanceGeo = distanceGeo;
		this.distanceDate = distanceDate;
	}
	
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(RankedPhoto o) {
		if (this.cost  > o.cost) {
			return +1;
		} else if (this.cost == o.cost) {
			return 0;
		} else {
			return -1;
		}
	}

	public double getDistanceGeo() {
		return distanceGeo;
	}

	public void setDistanceGeo(double distanceGeo) {
		this.distanceGeo = distanceGeo;
	}

	public double getDistanceString() {
		return distanceString;
	}

	public void setDistanceString(double distanceString) {
		this.distanceString = distanceString;
	}

	public double getDistanceDate() {
		return distanceDate;
	}

	public void setDistanceDate(double distanceDate) {
		this.distanceDate = distanceDate;
	}
}
