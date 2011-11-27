<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				 <a href="${image.url}">	
				 	<img src="${image.thumbnailUrl}" />
				 </a>
				 <h3><c:if test="${image.title != null}">${image.title}</c:if></h3>
				 <p><c:if test="${image.description != null}">${image.description}</c:if></p>
				 <p>
				 	<c:if test="${image.dateAdded != null}">Datum přidání: ${image.dateAdded}, </c:if>
				 	<c:if test="${image.dateTaken != null}">Datum pořízení: ${image.dateTaken}</c:if>
				 </p>
				 <p>
				 	<c:if test="${image.geoData.longitude != null && image.geoData.latitude != null}">Geo data: lat: ${image.geoData.latitude}, lon: ${image.geoData.longitude}</c:if>
				 </p>
				 <p>
				 	<c:if test="${image.tags != null}">Tagy: 
				 		<c:forEach var="tag" items="${image.tags}">${tag.value}, </c:forEach>
				 	</c:if>				 
				 </p>
			</div>	      
	    </c:forEach>
	</div>
	<%@ include file="include/footer.jsp" %>
</body>
</html>