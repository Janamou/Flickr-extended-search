package com.moudra.vmw.flickr.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gmail.yuyang226.flickr.Flickr;
import com.gmail.yuyang226.flickr.REST;
import com.gmail.yuyang226.flickr.photos.PhotoList;
import com.gmail.yuyang226.flickr.photos.PhotosInterface;
import com.gmail.yuyang226.flickr.photos.SearchParameters;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.moudra.vmw.flickr.classes.Constants;
import com.moudra.vmw.flickr.classes.StringUtils;

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
						
			String tagsMode = req.getParameter("tags_mode");
			String text = req.getParameter("keywords");
			String textSelection = req.getParameter("text_selection");
			
			String minDate = req.getParameter("min_date");
			String maxDate = req.getParameter("max_date");
			String dateSelection = req.getParameter("date_selection");
			
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
			
			if(textSelection.equals("text")){
				if(!StringUtils.isStringEmpty(text)){
					searchParams.setText(text);
				}
			} else {
				if(!StringUtils.isStringEmpty(text)){
					String tags[] = StringUtils.processTags(text.split(" "));
					searchParams.setTags(tags);
				}
			}
			
			if(!StringUtils.isStringEmpty(tagsMode)){
				searchParams.setTagMode(tagsMode);
			}
			
			if(dateSelection.equals("upload")){
				if(!StringUtils.isStringEmpty(minDate)){
					searchParams.setMinUploadDate(StringUtils.createDate(minDate));
				}
				
				if(!StringUtils.isStringEmpty(maxDate)){
					searchParams.setMaxUploadDate(StringUtils.createDate(maxDate));
				}
			} else {
				if(!StringUtils.isStringEmpty(minDate)){
					searchParams.setMinTakenDate(StringUtils.createDate(minDate));
				}
				
				if(!StringUtils.isStringEmpty(maxDate)){
					searchParams.setMaxTakenDate(StringUtils.createDate(maxDate));
				}
			}
						
									
			if(!StringUtils.isStringEmpty(minLongitude) && !StringUtils.isStringEmpty(maxLongitude) && !StringUtils.isStringEmpty(minLatitude) && !StringUtils.isStringEmpty(maxLatitude)){
				searchParams.setBBox(minLongitude, minLatitude, maxLongitude, maxLatitude);
			}
			
			/*
			if(!isSeletBoxEmpty(accuracy)){
				searchParams.setAccuracy(Integer.parseInt(accuracy));
			}*/
						
			if(!StringUtils.isStringEmpty(latitude)){
				searchParams.setLatitude(latitude);
			}
			
			if(!StringUtils.isStringEmpty(longitude)){
				searchParams.setLongitude(longitude);
			}
						
			if(!StringUtils.isStringEmpty(radius)){
				System.out.println(Integer.parseInt(radius));
				searchParams.setRadius(Integer.parseInt(radius));
			}
			
			if(!StringUtils.isStringEmpty(radiusUnits)){
				searchParams.setRadiusUnits(radiusUnits);
			}
			
			if(!StringUtils.isStringEmpty(searchResults)){
				perPage = Integer.parseInt(searchResults);
			}
			
			searchParams.setMedia("photos");
			
			Set<String> extras = new HashSet();
			extras.add("description");
			extras.add("date_upload");
			extras.add("date_taken");
			extras.add("geo");
			extras.add("tags");
			
			searchParams.setExtras(extras);
			
			PhotoList images = iface.search(searchParams, 50, 0);
			
			req.setAttribute("images", images);	
			req.setAttribute("size", images.size());	
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/search-results.jsp").forward(req, resp);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
