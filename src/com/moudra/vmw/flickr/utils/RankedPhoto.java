package com.moudra.vmw.flickr.utils;

import com.gmail.yuyang226.flickr.photos.Photo;

public class RankedPhoto implements Comparable<RankedPhoto> {
	private Photo photo;
	private double cost;
	
	public RankedPhoto(Photo photo, double cost) {
		this.photo = photo;
		this.cost = cost;
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
}
