package com.moudra.vmw.flickr.utils;

import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

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
	
	public static Date createDateFromString(String stringDate){
		String[] stringDateArray = stringDate.split("\\.");
		Date date = new Date(Integer.parseInt(stringDateArray[2]) - 1900, Integer.parseInt(stringDateArray[1]) - 1, Integer.parseInt(stringDateArray[0]));		
		System.out.println(date.getTime());
		return date;
	}
	
	public static boolean isStringTrue(String textString){
		return Boolean.parseBoolean(textString);
	}
}
