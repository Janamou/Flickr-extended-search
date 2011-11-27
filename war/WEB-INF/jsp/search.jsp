<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css" type="text/css" media="all" />
<link rel="stylesheet" href="http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/ui.stepper.css" type="text/css" media="all" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/i18n/jquery-ui-i18n.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.datepicker-cs.js" type="text/javascript"></script>
<script src="js/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/ui.spinner.js" type="text/javascript"></script>
<script src="js/init.js" type="text/javascript"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

<title>Semestrální práce VMW - Flickr - metadata based reranking</title>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="container">
		<h1>Vyhledávání</h1>
		<p>Zadejte parametry, podle kterých chcete vyhledávat.</p>
		TODO: vytvorit ruzne typy razeni
		reagovat na errory
		<form action="search" method="post" id="search">
			<h2>Základní vyhledávání</h2>
			<table>
				<tbody>
					<tr>
						<th><label for="keywords">Klíčová slova:</label></th>
						<td>
							<input type="text" name="keywords" id="keywords" />
							<select name="tags_mode" id="tags_mode">
								<option value="any">Některé slova</option>
								<option value="all">Všechna slova</option>
							</select>
							<br />
							<label for="text">text</label><input type="radio" name="text_selection" id="text" value="text" checked="checked" />
							<label for="tags">tagy</label><input type="radio" name="text_selection" id="tags" value="tagy"/>	
						</td>
					</tr>		
				</tbody>
			</table>
			<h2>Vyhledávání s datem</h2>
			<table>
				<tbody>
					<tr>
						<th>Datum:</th>
						<td>
							<label for="min_date">Od:</label>
							<input type="text" name="min_date" id="min_date" class="datepicker" />
							<label for="max_date">Do:</label>
							<input type="text" name="max_date" id="max_date" class="datepicker" />
							<br />
							<label for="upload_date">nahrání</label><input type="radio" name="date_selection" id="upload_date" value="upload" checked="checked" />
							<label for="taken_date">pořízení</label><input type="radio" name="date_selection" id="taken_date" value="taken"/>	
						</td>
					</tr>			
				</tbody>
			</table>
			<h2>Vyhledávání s lokací</h2>
			<div id="map_canvas" style="width:600px; height:600px"></div>
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
								<option value="none">-----</option>
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
						<td>
							<input type="text" name="radius" id="radius" value="5" />
						</td>
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
			<label for="search_results">Počet výsledků na stránku</label>
			<select name="search_results" id="search_results">
				<option value="10">10</option>
				<option value="25">25</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<input type="submit" value="Vyhledat" />
		</form>
	</div>
	<%@ include file="include/footer.jsp" %>
</body>
</html>