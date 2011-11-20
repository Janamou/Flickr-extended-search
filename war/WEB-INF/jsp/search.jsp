<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Semestrální práce VMW - Flickr - metadata based reranking</title>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="container">
		<h1>Vyhledávání</h1>
		<p>Zadejte parametry, podle kterých chcete vyhledávat.</p>
		TODO: vytvorit ruzne typy razeni
		reagovat na errory
		<form action="/search-results" method="post">
			<h2>Základní vyhledávání</h2>
			<table>
				<tbody>
					<tr>
						<th><label for="user">Jmeno uzivatele:</label></th>
						<td><input type="text" name="user" id="user" /></td>
					</tr>
					<tr>
						<th><label for="tags">Tagy:</label></th>
						<td>
							<input type="text" name="tags" id="tags" />
							<select name="tags_mode" id="tags_mode">
								<option value="or">Některé</option>
								<option value="and">Všechny</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="text">Text:</label></th>
						<td><input type="text" name="text" id="text" /></td>
					</tr>
					<tr>
						<th><label for="min_upload_date">Min upload date:</label></th>
						<td><input type="text" name="min_upload_date" id="min_upload_date" /></td>
					</tr>
					<tr>
						<th><label for="max_upload_date">Max upload date:</label></th>
						<td><input type="text" name="max_upload_date" id="max_upload_date" /></td>
					</tr>
					<tr>
						<th><label for="min_taken_date">Min taken date:</label></th>
						<td><input type="text" name="min_taken_date" id="min_taken_date" /></td>
					</tr>
					<tr>
						<th><label for="max_taken_date">Max taken date:</label></th>
						<td><input type="text" name="max_taken_date" id="max_taken_date" /></td>
					</tr>				
				</tbody>
			</table>
			<h2>Vyhledávání s datem</h2>
			<table>
				<tbody>
					<tr>
						<th><label for="min_upload_date">Min upload date:</label></th>
						<td><input type="text" name="min_upload_date" id="min_upload_date" /></td>
					</tr>
					<tr>
						<th><label for="max_upload_date">Max upload date:</label></th>
						<td><input type="text" name="max_upload_date" id="max_upload_date" /></td>
					</tr>
					<tr>
						<th><label for="min_taken_date">Min taken date:</label></th>
						<td><input type="text" name="min_taken_date" id="min_taken_date" /></td>
					</tr>
					<tr>
						<th><label for="max_taken_date">Max taken date:</label></th>
						<td><input type="text" name="max_taken_date" id="max_taken_date" /></td>
					</tr>				
				</tbody>
			</table>
			<h2>Vyhledávání s lokací</h2>
			<table>
				<tbody>
					<tr>
						<th>Longitude, Latitude</th>
						<td>
							<input type="text" name="min_longitude" id="min_longitude" />
							<input type="text" name="max_longitude" id="max_longitude" />
							<input type="text" name="min_latitude" id="min_latitude" />
							<input type="text" name="max_latitude" id="max_latitude" />
						</td>
					</tr>
					<tr>
						<th><label for="accuracy">Přesnost:</label></th>
						<td>
							<select name="accuracy" id="accuracy">
								<option value="1">Světová úroveň</option>
								<option value="3">Úroveň země</option>
								<option value="6">Úroveň regionu</option>
								<option value="11">Úroveň města</option>
								<option value="16">Úroveň ulice</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="latitude">Latitude:</label></th>
						<td><input type="text" name="latitude" id="latitude" /></td>
					</tr>
					<tr>
						<th><label for="longitude">Longitude:</label></th>
						<td><input type="text" name="longitude" id="longitude" /></td>
					</tr>
					<tr>
						<th><label for="radius">Poloměr:</label></th>
						<td><input type="text" name="radius" id="radius" /></td>
					</tr>
					<tr>
						<th><label for="radius_units">Jednotky:</label></th>
						<td>
							<select name="radius_units" id="radius_units">
								<option value="km">Kilometry</option>
								<option value="mi">Míle</option>
							</select>
						</td>
					</tr>				
				</tbody>
			</table>
			<h2>Počet výsledků na stránku</h2>
			<input type="text" name="search_results" id="search_results" />
			<input type="submit" value="Vyhledat" />
		</form>
	</div>
	<%@ include file="include/footer.jsp" %>
</body>
</html>