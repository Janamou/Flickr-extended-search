<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Semestrální práce VMW - Flickr - metadata based reranking</title>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="container">
		<h1>Výsledky vyhledávání</h1>
		<p>Nalezeno: ${size} výsledků.</p>
		<c:forEach var="image" items="${images}">
			<div class="image">
				Cena: ${image.cost}
				 <a href="${image.photo.url}">	
				 	<img src="${image.photo.thumbnailUrl}" />
				 </a>
				 <h3><c:if test="${image.photo.title != null}">${image.photo.title}</c:if></h3>
				 <p><c:if test="${image.photo.description != null}">${image.photo.description}</c:if></p>
				 <p>
				 	<c:if test="${image.photo.dateAdded != null}">Datum přidání: <fmt:formatDate value="${image.photo.dateAdded}" pattern="dd.MM.yyyy HH:mm"/>, </c:if>
				 	<c:if test="${image.photo.dateTaken != null}">Datum pořízení: <fmt:formatDate value="${image.photo.dateTaken}" pattern="dd.MM.yyyy HH:mm"/></c:if>
				 </p>
				 <p>
				 	<c:if test="${image.photo.geoData.longitude != null && image.photo.geoData.latitude != null}">Geo data: lat: ${image.photo.geoData.latitude}, lon: ${image.photo.geoData.longitude}</c:if>
				 </p>
				 <p>
				 	<c:if test="${image.photo.tags != null && image.photo.tags[0].value != ''}">Tagy: 				 
				 		<c:forEach var="tag" items="${image.photo.tags}">
				 			<c:if test="${tag.value != ''}">
				 				${tag.value},
				 			</c:if>
				 		 </c:forEach>	
				 	</c:if>				 
				 </p>
				 <p>Šířka: ${image.photo.originalWidth}px, Výška: ${image.photo.originalHeight}px</p>
			</div>	      
	    </c:forEach>
	</div>
	<%@ include file="include/footer.jsp" %>
</body>
</html>