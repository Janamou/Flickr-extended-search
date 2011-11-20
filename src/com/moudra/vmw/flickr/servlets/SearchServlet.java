package com.moudra.vmw.flickr.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gmail.yuyang226.flickr.Flickr;
import com.gmail.yuyang226.flickr.REST;
import com.gmail.yuyang226.flickr.photos.PhotoList;
import com.gmail.yuyang226.flickr.photos.PhotosInterface;
import com.gmail.yuyang226.flickr.photos.SearchParameters;
import com.moudra.vmw.flickr.classes.Constants;

public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException { 
			
		try {
			
			REST rest = new REST();
			Flickr flickr = new Flickr(Constants.API_KEY, Constants.API_SECRET, rest);
			PhotosInterface iface = flickr.getPhotosInterface();
			SearchParameters searchParams = new SearchParameters();
			
			String user = req.getParameter("user");
			String tags[] = processTags(req.getParameter("tags").split(","));
			String tagsMode = req.getParameter("tags_mode");
			String text = req.getParameter("text");
			String minUploadDate = req.getParameter("min_upload_date");
			String maxUploadDate = req.getParameter("max_upload_date");
			String minTakenDate = req.getParameter("min_taken_date");
			String maxTakenDate = req.getParameter("max_taken_date");
			String minLongitude = req.getParameter("min_longitude");
			String maxLongitude = req.getParameter("max_longitude");
			String minLatitude = req.getParameter("min_latitude");
			String maxLatitude = req.getParameter("max_latitude");
			String accuracy = req.getParameter("accuracy");
			String latitude = req.getParameter("latitude");
			String longitude = req.getParameter("longitude");
			String radius = req.getParameter("radius");
			String radiusUnits = req.getParameter("radius_units");
			String searchResults = req.getParameter("search_results");
			int perPage = 50;
			
			if(!isStringEmpty(user)){
				searchParams.setUserId(user);
			}
			
			
			if(tags != null){
				searchParams.setTags(tags);
			}
						
			if(!isSeletBoxEmpty(tagsMode)){
				searchParams.setTagMode(tagsMode);
			}
						
			
			if(!isStringEmpty(text)){
				searchParams.setText(text);
			}
			
			if(!isStringEmpty(minUploadDate)){
				searchParams.setMinUploadDate(new Date(minUploadDate));
			}
			
			if(!isStringEmpty(maxUploadDate)){
				searchParams.setMaxUploadDate(new Date(maxUploadDate));
			}
			
			if(!isStringEmpty(minTakenDate)){
				searchParams.setMinTakenDate(new Date(minTakenDate));
			}
			
			if(!isStringEmpty(maxTakenDate)){
				searchParams.setMaxTakenDate(new Date(maxTakenDate));
			}
						
			if(!isStringEmpty(minLongitude) && !isStringEmpty(maxLongitude) && !isStringEmpty(minLatitude) && !isStringEmpty(maxLatitude)){
				searchParams.setBBox(minLongitude, minLatitude, maxLongitude, maxLatitude);
			}
			
			/*
			if(!isSeletBoxEmpty(accuracy)){
				searchParams.setAccuracy(Integer.parseInt(accuracy));
			}*/
						
			if(!isStringEmpty(latitude)){
				searchParams.setLatitude(latitude);
			}
			
			if(!isStringEmpty(longitude)){
				searchParams.setLongitude(longitude);
			}
						
			if(!isStringEmpty(radius)){
				searchParams.setRadius(Integer.parseInt(radius));
			}
			
			if(!isSeletBoxEmpty(radiusUnits)){
				searchParams.setRadiusUnits(radiusUnits);
			}
			
			if(!isStringEmpty(searchResults)){
				perPage = Integer.parseInt(searchResults);
			}
			
			
			//images.get(0).get
			
			PhotoList images = iface.search(searchParams, perPage, 0);
			
			System.out.println(images.get(0));
			System.out.println(images.size());
			System.out.println("ahoj2");
			
			req.setAttribute("images", images);			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/search-results.jsp").forward(req, resp);
	        
		} catch (Exception e) {
			
		}
	}
	
	private boolean isStringEmpty(String s){
		return (s == null || s.equals(""));
	}
	
	private boolean isSeletBoxEmpty(String s){
		return s == "none";
	}
	
	private String[] processTags(String[] sArray){
		for(int i = 0; i< sArray.length; i++){
			sArray[i] = sArray[i].trim();
		}
		
		return sArray;
	}
}
