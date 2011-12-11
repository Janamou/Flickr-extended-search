<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
<link rel="stylesheet" media="aural" type="text/css" href="css/aural.css" />

<link rel="stylesheet" media="screen,projection" type="text/css" href="css/colorbox.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/colorbox2.js"></script>

<script>
	$(document).ready(function(){			
		$(this).attr('rel','image_rel'); 
		var imgSrc = $(this).children().attr('src');		
	});	
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
						
							<a class="image_" rel="image_rel" href="
								<c:choose>																
									<c:when test="${image.photo.mediumUrl != null}">${image.photo.mediumUrl}</c:when>
									<c:otherwise>${image.photo.smallUrl}</c:otherwise>									
								</c:choose>" 
							 	title="<c:if test="${image.photo.title != null}">${image.photo.title}</c:if>">

								<img src="${image.photo.thumbnailUrl}" alt="" />
															 
							 </a>
							 <a class="moreinfo" href="#inline_content${status.index}">i</a>
							 <div style="display: none">
	  							 <div class="img_properties" id="inline_content${status.index}" style='padding:10px; background:#fff;'>				    																		
									<img class="preview" src="${image.photo.smallUrl}"  alt="" />
									<table class="info_table">
										<tr><td class="bold">Celková cena (reranking): </td><td>${image.cost}</td></tr>
										<c:if test="${image.photo.title != null}"><tr><td class="bold">Titulek: </td><td>${image.photo.title}</td></tr></c:if>
										<c:if test="${image.photo.owner.username != null}"><tr><td class="bold">Autor: </td><td>${image.photo.owner.username}</td></tr></c:if>
										<c:if test="${image.photo.description != null}"><tr><td class="bold">Popis: </td><td>${image.photo.description}</td></tr></c:if>
										<c:if test="${image.photo.dateAdded != null}"><tr><td class="bold">Datum přidání: </td><td>${image.photo.dateAdded}</td></tr></c:if>
										<c:if test="${image.photo.dateTaken != null}"><tr><td class="bold">Datum pořízení: </td><td>${image.photo.dateTaken}</td></tr></c:if>
										<c:if test="${image.photo.geoData.longitude != null && image.photo.geoData.latitude != null}">
											<tr>
												<td class="bold">Geo data - latitude: </td>
												<td>${image.photo.geoData.latitude}</td>
											</tr>
											<tr>
												<td class="bold">Geo data - longitude: </td>
												<td>${image.photo.geoData.longitude}</td>
											</tr>
										</c:if>
										<c:if test="${image.photo.tags != null}">									
									 		<tr><td class="bold">Tagy: </td>
									 			<td>
										 			<c:forEach var="tag" items="${image.photo.tags}">
											 			<c:if test="${tag.value != ''}">
											 				${tag.value},								 			
											 			</c:if>
										 			</c:forEach>
									 			</td>
									 		</tr>
										</c:if>
											
										<tr><td class="bold">Šířka: </td><td> ${image.photo.originalWidth} px</td></tr>
										<tr><td class="bold">Výška: </td><td>${image.photo.originalHeight} px</td></tr>
										
									</table>
			
  							 	</div>
  							 </div>  														

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