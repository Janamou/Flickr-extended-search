package com.moudra.vmw.flickr.utils;

import java.util.Date;

public class Distances {
	
	/**
	 * Returns distance between two Strings.
	 * 
	 * @param inputText - text which was found in search results
	 * @param desiredText - desired/searched text
	 * */
	public static int computeLevensteinDistance(String desiredText, String inputText){
		if (inputText == null || desiredText == null) {
		    throw new IllegalArgumentException("Strings must not be null");
		}
		
		int desiredTextSize = desiredText.length();
		int inputTextSize = inputText.length();
		int[][] distance = new int[desiredText.length() + 1][inputText.length() + 1];
		
		if (inputTextSize == 0) {
		    return desiredTextSize;
		} else if (desiredTextSize == 0) {
		    return inputTextSize;
		}
		
		for (int i = 0; i <= desiredText.length(); i++) {
			distance[i][0] = i;
		}
		
		for (int j = 0; j <= inputText.length(); j++) {
            distance[0][j] = j;
		} 
		
		for (int i = 1; i <= desiredText.length(); i++) {
            for (int j = 1; j <= inputText.length(); j++) {
                distance[i][j] = computeMin(distance[i - 1][j] + 1, distance[i][j - 1] + 1, distance[i - 1][j - 1] + ((desiredText.charAt(i - 1) == inputText.charAt(j - 1)) ? 0 : 1));
            }
        }
		
		return distance[desiredText.length()][inputText.length()];
	}
	
	private static int computeMin(int a, int b, int c){
		return Math.min(a, Math.min(b, c));
	}
	
	/**
	 * Returns distance between two dates.
	 * 
	 * @param startFormDate - start Date from form
	 * @param endFormDate - end Date from form
	 * @param photoDate - Date of actual photo
	 * @param asc - sorting asc/desc
	 * 
	 * */
	public static long computeDateDistance(Date startFormDate, Date endFormDate, Date photoDate, boolean asc){
		if (photoDate == null) {
		    throw new IllegalArgumentException("Date must not be null");
		}
		
		long cost = 0;
		
		if (asc) {
			if (startFormDate == null) {
				cost = computeDateDistance(photoDate, new Date(0)); //we set boundaries from beginning
			} else {
				cost = computeDateDistance(photoDate, startFormDate);
			}			
		} else {
			if (endFormDate == null) {
				cost = computeDateDistance(new Date(), photoDate); //we set it to this date
			} else {
				cost = computeDateDistance(endFormDate, photoDate);
			}
		}
		
		return cost;
	}
	
	private static long computeDateDistance(Date start, Date end){
		return Long.parseLong(start.toString()) - Integer.parseInt(end.toString());
	}
	
	/**
	 * Returns cost of image size (the bigger the image is bigger)
	 * */
	public static long computeSizeDistance(int width, int height){
		return width * height;
	}
	
	
	/**
	 * Returns distance between 2 points
	 * */
	public static double computeGeoDistance(double sourceLatitude, double sourceLongitude, double photoLatitude, double photoLongitude ){
		double radiansTheta = Math.toRadians(sourceLongitude - photoLongitude);
		
		double radiansSourceLatitude = Math.toRadians(sourceLatitude);
		double radiansPhotoLatitude = Math.toRadians(photoLatitude);
		

	    double dist = Math.sin(radiansSourceLatitude) * Math.sin(radiansPhotoLatitude) + Math.cos(radiansSourceLatitude) * Math.cos(radiansPhotoLatitude) * Math.cos(radiansTheta);
	    dist = Math.acos(dist);
	    dist = Math.toDegrees(dist);
	    dist = dist * 60 * 1.1515 * 1.609344;

	    return dist;
	}
}
