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
			
			String latitude = req.getParameter("latitude");
			String longitude = req.getParameter("longitude");
			String radius = req.getParameter("radius");
			String searchResults = req.getParameter("search_results");
			
			//Reranking options
			boolean isRerankString = StringUtils.isStringTrue(req.getParameter("rerank_string"));
			boolean isRerankGeo = StringUtils.isStringTrue(req.getParameter("rerank_geo"));
			boolean isRerankDate = StringUtils.isStringTrue(req.getParameter("rerank_date"));
			boolean isRerankSize = StringUtils.isStringTrue(req.getParameter("rerank_size"));
			
			String rerankPriorityString = req.getParameter("rerank_priority_string");
			String rerankPriorityGeo = req.getParameter("rerank_priority_geo");
			String rerankPriorityDate = req.getParameter("rerank_priority_date");
			String rerankPrioritySize = req.getParameter("rerank_priority_size");
			
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
	
	private RankedPhoto[] generateRankedPhotos(PhotoList images){
		RankedPhoto[] photoArray = new RankedPhoto[images.size()];
		
		double cost = 0; //TODO
		
		for (int i = 0;i < photoArray.length; i++){
			photoArray[i] = new RankedPhoto(images.get(i), Math.random());
		}
		
		Arrays.sort(photoArray, Collections.reverseOrder());
		
		return photoArray;
	}
}
