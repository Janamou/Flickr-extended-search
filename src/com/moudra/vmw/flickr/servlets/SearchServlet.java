package com.moudra.vmw.flickr.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gmail.yuyang226.flickr.Flickr;
import com.gmail.yuyang226.flickr.REST;
import com.gmail.yuyang226.flickr.collections.Collection;
import com.gmail.yuyang226.flickr.photos.Extras;
import com.gmail.yuyang226.flickr.photos.Photo;
import com.gmail.yuyang226.flickr.photos.PhotoList;
import com.gmail.yuyang226.flickr.photos.PhotosInterface;
import com.gmail.yuyang226.flickr.photos.SearchParameters;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.moudra.vmw.flickr.utils.Constants;
import com.moudra.vmw.flickr.utils.Distances;
import com.moudra.vmw.flickr.utils.RankedPhoto;
import com.moudra.vmw.flickr.utils.StringUtils;

public class SearchServlet extends HttpServlet {
	static boolean imageAsc, rankAccordingWidth;
	
	static String text, minDate, maxDate, 
	latitude, longitude, radius, rerankPriorityString, rerankPriorityGeo, 
	rerankPriorityDate, rerankPrioritySize, rerankSizeType;
	
	
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
			String textSelection = req.getParameter("text_selection");
			String dateSelection = req.getParameter("date_selection");
			String searchResults = req.getParameter("search_results");
			
			text = req.getParameter("keywords");	
			
			minDate = req.getParameter("min_date");
			maxDate = req.getParameter("max_date");
						
			latitude = req.getParameter("latitude");
			longitude = req.getParameter("longitude");
			radius = req.getParameter("radius");
						
			rerankPriorityString = req.getParameter("rerank_priority_string");
			rerankPriorityGeo = req.getParameter("rerank_priority_geo");
			rerankPriorityDate = req.getParameter("rerank_priority_date");
			rerankPrioritySize = req.getParameter("rerank_priority_size");
			rerankSizeType = req.getParameter("rerank_size_type");
				
			processPrioritySize(rerankSizeType);
			
			int perPage = 100;
			
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
					searchParams.setMinUploadDate(StringUtils.createDateFromString(minDate));
				}
				
				if(!StringUtils.isStringEmpty(maxDate)){
					searchParams.setMaxUploadDate(StringUtils.createDateFromString(maxDate));
				}
			} else {
				if(!StringUtils.isStringEmpty(minDate)){
					searchParams.setMinTakenDate(StringUtils.createDateFromString(minDate));
				}
				
				if(!StringUtils.isStringEmpty(maxDate)){
					searchParams.setMaxTakenDate(StringUtils.createDateFromString(maxDate));
				}
			}
						
			if(!StringUtils.isStringEmpty(latitude)){
				searchParams.setLatitude(latitude);
			}
			
			if(!StringUtils.isStringEmpty(longitude)){
				searchParams.setLongitude(longitude);
			}
						
			if(!StringUtils.isStringEmpty(radius)){
				searchParams.setRadius(Integer.parseInt(radius));
			}
			
			if(!StringUtils.isStringEmpty(searchResults)){
				perPage = Integer.parseInt(searchResults);
			}
			
			searchParams.setMedia(Constants.MEDIA_TYPE); //sets media searched
			searchParams.setRadiusUnits(Constants.GEO_UNITS); //sets units for geo used
			searchParams.setExtras(Extras.ALL_EXTRAS); //sets extra information used in search results
			
			PhotoList images = iface.search(searchParams, 50, 0);
			RankedPhoto[] rankedPhotos = generateRankedPhotos(images);
			
			req.setAttribute("images", rankedPhotos);	
			req.setAttribute("size", images.size());	
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/search-results.jsp").forward(req, resp);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static RankedPhoto[] generateRankedPhotos(PhotoList images){
		double cost = 0;
		
		RankedPhoto[] photoArray = new RankedPhoto[images.size()];
				
		for (int i = 0;i < photoArray.length; i++){
			cost = computeCostForImage(images.get(i));
			photoArray[i] = new RankedPhoto(images.get(i), cost);
		}
		
		Arrays.sort(photoArray, Collections.reverseOrder());
		
		return photoArray;
	}
	
	private static double computeCostForImage(Photo image) {
		double distanceString = Distances.computeLevensteinDistance("ahoj", "ahoj"); //TODO
		double distanceGeo = Distances.computeGeoDistance(0, 0, 0, 0); //TODO
		//double distanceDate = Distances.computeDateDistance(new Date(0), new Date(0), new Date(0), true); //TODO
		double distanceDate = 0.1; //TODO
		int imageSide = Distances.setSide(image.getOriginalWidth(), image.getOriginalHeight(), rankAccordingWidth);
		
		int priorityString = 0; //TODO
		int priorityGeo = 0; //TODO
		int priorityDate = 0; //TODO
		int prioritySize = Integer.parseInt(rerankPrioritySize);
		
		double[] distances = Distances.setDistancesArray(distanceString, distanceGeo, distanceDate, imageSide);
		int[] priorities = Distances.setPrioritiesArray(priorityString, priorityGeo, priorityDate, prioritySize);
		
		return Distances.computeFinalImageCost(priorities, distances, imageAsc);
	}

	private static void processPrioritySize(String rerankSizeType){	
		if (rerankSizeType.equals("desc_width")) {
			imageAsc = false;
			rankAccordingWidth = true;
		} else if (rerankSizeType.equals("asc_width")) {
			imageAsc = true;
			rankAccordingWidth = true;
		} else if (rerankSizeType.equals("desc_height")) {
			imageAsc = false;
			rankAccordingWidth = false;
		} else {
			imageAsc = true;
			rankAccordingWidth = false;
		}
	}
}
