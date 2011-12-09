<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" /> -->
<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
<link rel="stylesheet" media="aural" type="text/css" href="css/aural.css" />

<link rel="stylesheet" media="screen,projection" type="text/css" href="css/colorbox.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/colorbox2.js"></script>

<!--  --><script>
	$(document).ready(function(){
		//Examples of how to assign the ColorBox event to elements
		//$(".image_").colorbox({rel:'image__'});			
		$(this).attr('rel','image_rel'); 
		var imgSrc = $(this).children().attr('src');
		//$(this).attr('href',imgSrc.replace('_s.jpg','.jpg'));
		//$(this).attr('href',imgSrc.replace('_t.jpg','.jpg'));
	});
	//$(".inline_").colorbox({html:"<h1>Welcome</h1>"});
	//$(".inline").colorbox({inline:true, href:".moreinfo"});
	//$('a[rel*=image_rel]').colorbox({rel:'image_rel', transition:"none", width:"75%", height:"75%", title: "titulecek"});
	//$('a[rel*=image_rel]').colorbox({rel:'image_rel', transition:"none", width:"75%", height:"75%"});
</script>


<title>Semestrální práce VMW - Flickr - metadata based reranking</title>
</head>
<body>
<div id="main" class="box">
	<%@ include file="include/header.jsp" %>

	<div id="page_results" class="box">
		<div id="page-in" class="box">
			<div id="content">
				<div class="intro_results gallery">
					<h2><span>Výsledky vyhledávání</span></h2>
					<p>Nalezeno: ${size} výsledků.</p>					
					<c:forEach var="image" items="${images}" varStatus="status">
						<div class="image">
						<!-- Cena: ${image.cost}, Autor: ${image.photo.owner.username},
						Distance string: ${image.distanceString}, Distance geo: ${image.distanceGeo} -->

							<a class="image_" rel="image_rel" href="
								<c:choose>
									<c:when test="${image.photo.mediumUrl != null}">${image.photo.mediumUrl}</c:when>
									<c:otherwise>${image.photo.smallUrl}</c:otherwise>
								</c:choose>" 
							 	title="<c:if test="${image.photo.title != null}">${image.photo.title}</c:if>">

								<img src="${image.photo.thumbnailUrl}" alt="" />
								<a class="moreinfo" href="#inline_content${status.index}"><img src="css/images/next.gif"/></a>							 
							 </a>

							 <div style="display: none">
	  							 <div class="img_properties" id="inline_content${status.index}" style='padding:10px; background:#fff;'>				    																		
									<img class="preview" src="${image.photo.smallUrl}"  alt="" />
									<c:if test="${image.photo.dateTaken != null}">Datum pořízení: ${image.photo.dateTaken}</c:if>														 									 									 
  							 </div>
  							

							 <!-- <a class="image_ " rel="image_rel" title="<c:if test="${image.photo.title != null}"><p>${image.photo.title}</p></c:if>" href="
							 <c:choose>
								 <c:when test="${image.photo.mediumUrl != null}">${image.photo.mediumUrl}</c:when>
								 <c:otherwise>${image.photo.smallUrl}</c:otherwise>
							 </c:choose>">
							 	
							 <img src="${image.photo.thumbnailUrl}" />
							 	
							 <div>
							 	<span id="image_hidden">
								 <h3><c:if test="${image.photo.title != null}">${image.photo.title}</c:if></h3>
								 <p><c:if test="${image.photo.description != null}">${image.photo.description}</c:if></p>
								 <p>
								 	<c:if test="${image.photo.dateAdded != null}">Datum přidání: ${image.photo.dateAdded}, </c:if>
								 	<c:if test="${image.photo.dateTaken != null}">Datum pořízení: ${image.photo.dateTaken}</c:if>
								 </p>
								 <p>
								 	<c:if test="${image.photo.geoData.longitude != null && image.photo.geoData.latitude != null}">Geo data: lat: ${image.photo.geoData.latitude}, lon: ${image.photo.geoData.longitude}</c:if>
								 </p>
								 <p>
								 	<c:if test="${image.photo.tags != null}">Tagy: 
								 		<c:forEach var="tag" items="${image.photo.tags}">
									 		<c:if test="${tag.value != ''}">
									 			${tag.value},
									 		</c:if>
								 		</c:forEach>
								 	</c:if>				 
								 </p>
								 <p>
								 	Sirka: ${image.photo.originalWidth}px, Vyska: ${image.photo.originalHeight}px		 
								 </p>
								</span>
							 </div> 
							 
							 </a>-->

						</div>
						<script>
				    		jQuery('a.image_').colorbox();				    		
				    	</script>

				    </c:forEach>



			    </div>
			</div>
		</div>
	</div>	
	<%@ include file="include/footer1.jsp" %>
</div>
</body>
</html>