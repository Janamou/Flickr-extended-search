package com.moudra.vmw.flickr.classes;

import java.util.Date;

public class StringUtils {
	public static boolean isStringEmpty(String s){
		return (s == null || s.equals(""));
	}
	
	public static boolean isSeletBoxEmpty(String s){
		return s == "none";
	}
	
	public static String[] processTags(String[] sArray){
		for(int i = 0; i< sArray.length; i++){
			sArray[i] = sArray[i].trim();
		}		
		return sArray;
	}
	
	public static Date createDate(String stringDate){
		String[] stringDateArray = stringDate.split("\\.");
		Date date = new Date(Integer.parseInt(stringDateArray[2]) - 1900, Integer.parseInt(stringDateArray[1]) - 1, Integer.parseInt(stringDateArray[0]));		
		return date;
	}
}
