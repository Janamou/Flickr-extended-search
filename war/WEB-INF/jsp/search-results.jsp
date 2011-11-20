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
		<c:forEach var="image" items="${images}">
	      <a href="${image.url}" rel="lightbox">
	      	<img src="${image.thumbnailUrl}" width="${image.thumbnailSize.width}" width="${image.thumbnailSize.height}" />
	      	<h3><c:if test="${image.title != null}">${image.title}</c:if>, 
	      	date added: ${image.dateAdded}, date posted: ${image.datePosted}, 
	      	date taken: ${image.dateTaken}, Lat: ${image.geoData.latitude}, 
	      	Long: ${image.geoData.longitude}, Přesnost: ${image.geoData.accuracy}
	      	Tagy: ${image.tags}</h3>
	      </a>
	    </c:forEach>
	</div>
	<%@ include file="include/footer.jsp" %>
</body>
</html>