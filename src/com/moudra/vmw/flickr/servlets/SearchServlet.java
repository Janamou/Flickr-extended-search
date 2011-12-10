package com.moudra.vmw.flickr.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.google.appengine.api.quota.QuotaService;
import com.google.appengine.api.quota.QuotaServiceFactory;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.moudra.vmw.flickr.utils.Constants;
import com.moudra.vmw.flickr.utils.Distances;
import com.moudra.vmw.flickr.utils.RankedPhoto;
import com.moudra.vmw.flickr.utils.StringUtils;

public class SearchServlet extends HttpServlet {
	protected static final Logger logger = Logger.getLogger(SearchServlet.class.getName());
	
	static boolean imageAsc, rankAccordingWidth;
	
	static String text, minDate, maxDate, 
	latitude, longitude, radius, rerankPriorityString, rerankPriorityGeo, 
	rerankPriorityDate, rerankPrioritySize, rerankSizeType, rerankAutor;
	
	static double distanceString, distanceGeo, distanceDate;
	
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
			
			rerankAutor = req.getParameter("rerank_string");
			rerankSizeType = req.getParameter("rerank_size_type");
			
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
			
			if(!StringUtils.isStringEmpty(minDate)){
				searchParams.setMinTakenDate(StringUtils.createDateFromString(minDate));
			}
			
			if(!StringUtils.isStringEmpty(maxDate)){
				searchParams.setMaxTakenDate(StringUtils.createDateFromString(maxDate));
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
			
			searchParams.setMedia(Constants.MEDIA_TYPE); //sets media searched
			searchParams.setRadiusUnits(Constants.GEO_UNITS); //sets units for geo used
			searchParams.setExtras(Extras.ALL_EXTRAS); //sets extra information used in search results
			searchParams.setSort(SearchParameters.RELEVANCE);
			
			//quota
			QuotaService qs = QuotaServiceFactory.getQuotaService();
			long startCPU = 0, startAPI = 0;
			if(qs.supports(QuotaService.DataType.CPU_TIME_IN_MEGACYCLES)) {
				startAPI = qs.getApiTimeInMegaCycles();
				startCPU = qs.getCpuTimeInMegaCycles();
			} else {
				startCPU = System.currentTimeMillis();
			}
			
			PhotoList images = iface.search(searchParams, Integer.parseInt(searchResults), 0);
			processPrioritySize(rerankSizeType);			
			RankedPhoto[] rankedPhotos = generateRankedPhotos(images);
			
			if(qs.supports(QuotaService.DataType.CPU_TIME_IN_MEGACYCLES)) {
				long endCPU = qs.getCpuTimeInMegaCycles();
				long endAPI = qs.getApiTimeInMegaCycles();
				double cpuSeconds = qs.convertMegacyclesToCpuSeconds(endCPU - startCPU);
				double apiSeconds = qs.convertMegacyclesToCpuSeconds(endAPI - startAPI);
	
				logger.log(Level.WARNING,"Search: cpu = " + cpuSeconds + ", api = " + apiSeconds + ", sum = " + (cpuSeconds + apiSeconds));
			} else {
				long endCPU = System.currentTimeMillis();
			   double cpuSeconds = (endCPU - startCPU);
			   System.out.println("Search: cpu = " + cpuSeconds);
			}
			
			req.setAttribute("images", rankedPhotos);	
			req.setAttribute("size", images.size());
			req.setAttribute("distanceString", distanceString);
			req.setAttribute("distanceGeo", distanceGeo);
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
			photoArray[i] = new RankedPhoto(images.get(i), cost, distanceString, distanceGeo, distanceDate);
		}
		
		Arrays.sort(photoArray, Collections.reverseOrder());
		
		return photoArray;
	}
	
	private static double computeCostForImage(Photo image) {
		double distance = 0;
		double side = 0;
		distanceString = distanceGeo = distanceDate = 0;
		
		if (!rerankAutor.equals("")){
			distance = Distances.computeLevensteinDistance(rerankAutor, image.getOwner().getUsername());					
			distanceString = Distances.recomputeDistance(0.09, 0.1, distance);
		}
		
		if (!latitude.equals("") && !longitude.equals("")){
			distance = Distances.computeGeoDistance(Double.parseDouble(latitude), Double.parseDouble(longitude), image.getGeoData().getLatitude(), image.getGeoData().getLongitude()); 
			distanceGeo = Distances.recomputeDistance(0.09, 0.1, distance);
		}

		if (!maxDate.equals("") || !minDate.equals("")){
			distance = Distances.computeDateDistance(StringUtils.createDateFromString(minDate), StringUtils.createDateFromString(maxDate), image.getDateTaken(), true); //TODO
			distanceDate = Distances.recomputeDistance(0.0009, 0.1, distance);
		}
		
		side = Distances.setSide(image.getOriginalWidth(), image.getOriginalHeight(), rankAccordingWidth);		
		double imageSide = Distances.recomputeDistance(0.00009, 0.1, side);
		
		int priorityString = Integer.parseInt(rerankPriorityString);
		int priorityGeo = Integer.parseInt(rerankPriorityGeo); 
		int priorityDate = Integer.parseInt(rerankPriorityDate);
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
