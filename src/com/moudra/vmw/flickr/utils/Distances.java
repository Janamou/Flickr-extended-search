package com.moudra.vmw.flickr.utils;

import java.util.Date;

public class Distances {
	
	/**
	 * Returns distance between two Strings.
	 * 
	 * @param inputText - text which was found in search results
	 * @param desiredText - desired/searched text
	 * */
	public static double computeLevensteinDistance(String desiredText, String inputText){
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
	public static double computeDateDistance(Date startFormDate, Date endFormDate, Date photoDate, boolean asc){
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
	
	/**
	 * Recomputes distance according to function
	 * 
	 * @param a - linear function 'a' parameter
	 * @param b - linear function 'b' parameter
	 * @param distance - distance to be recomputed by function 
	 * 
	 * */
	public static double recomputeDistance(double a, double b, double distance){
		return a*distance + b;
	}
	
	/**
	 * Computes image cost. The bigger is, the better position of this image is.
	 * 
	 * */
	public static double computeFinalImageCost(int[] priorities, double[] distances, int[] imageSize, boolean imageAsc){
		if (priorities == null) {
			throw new IllegalArgumentException("Priorities must not be null");
		} else if (distances == null) {
			throw new IllegalArgumentException("Distances must not be null");
		}
		
		int priorityString = priorities[0];
		int priorityGeo = priorities[1];
		int priorityDate = priorities[2];
		int prioritySize = priorities[3];
		double distanceString = distances[0];
		double distanceGeo = distances[1];
		double distanceDate = distances[2];
		
		double imageSizeCost = (imageAsc)? (1 / (imageSize[0] + 1/imageSize[1] + imageSize[0]*imageSize[1])): (imageSize[0] + 1/imageSize[1] + imageSize[0]*imageSize[1]);
		
		return priorityString/distanceString + priorityGeo/distanceGeo + priorityDate/distanceDate + prioritySize*imageSizeCost;		
	}
	
	public static int[] setPrioritiesArray(int priorityString, int priorityGeo, int priorityDate, int prioritySize){
		return new int[] {priorityString, priorityGeo, priorityDate, prioritySize};
	}
	
	public static double[] setDistancesArray(double distanceString, double distanceGeo, double distanceDate){
		return new double[] {distanceString, distanceGeo, distanceDate};
	}
	
	/**
	 * Returns array of image dimensions. 
	 * @param width - width of image
	 * @param height - height of image
	 * @param rankAccordingWidth - if we are sorting according to width/height
	 * @return int[] - array of two values.
	 * */
	public static int[] setSizeArray(int width, int height, boolean rankAccordingWidth){
		if (rankAccordingWidth) {
			return new int[] {width, height};
		} else {
			return new int[] {height, width};
		}		
	}
}
