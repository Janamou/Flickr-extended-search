<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css"
	type="text/css" media="all" />
<link rel="stylesheet"
	href="http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css"
	type="text/css" media="all" />
<link rel="stylesheet" href="css/ui.stepper.css" type="text/css"
	media="all" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/i18n/jquery-ui-i18n.min.js"
	type="text/javascript"></script>
<script src="js/jquery.ui.datepicker-cs.js" type="text/javascript"></script>
<script src="js/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/ui.spinner.js" type="text/javascript"></script>
<script src="js/init.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

<title>Semestrální práce VMW - Flickr - metadata based reranking</title>
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<div id="container">
		<h1>Vyhledávání s rerankingem</h1>
		<form action="search" method="post" id="search">
			<div id="container-search">
				<h2>Vyhledávání</h2>
				<p>Zadejte parametry vyhledávání, podle kterých chcete
					vyhledávat.</p>
				<h3>Základní vyhledávání</h3>
				<table>
					<tbody>
						<tr>
							<th><label for="keywords">Klíčová slova:</label></th>
							<td><input type="text" name="keywords" id="keywords" /> <select
								name="tags_mode" id="tags_mode">
									<option value="any">Některé slova</option>
									<option value="all">Všechna slova</option>
							</select> <br /> <label for="text">text</label><input type="radio"
								name="text_selection" id="text" value="text" checked="checked" />
								<label for="tags">tagy</label><input type="radio"
								name="text_selection" id="tags" value="tagy" />
							</td>
						</tr>
					</tbody>
				</table>
				<h3>Vyhledávání s datem</h3>
				<table>
					<tbody>
						<tr>
							<th>Datum:</th>
							<td><label for="min_date">Od:</label> <input type="text"
								name="min_date" id="min_date" class="datepicker" /> <label
								for="max_date">Do:</label> <input type="text" name="max_date"
								id="max_date" class="datepicker" /> <br /> <label
								for="upload_date">nahrání</label><input type="radio"
								name="date_selection" id="upload_date" value="upload"
								checked="checked" /> <label for="taken_date">pořízení</label><input
								type="radio" name="date_selection" id="taken_date" value="taken" />
							</td>
						</tr>
					</tbody>
				</table>
				<h3>Vyhledávání s lokací</h3>
				<p>Klikněte na mapě pro získání dat lokace.</p>
				<div id="map_canvas" style="width: 600px; height: 600px"></div>
				<table>
					<tbody>
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
							<td><input type="text" name="radius" id="radius" value="5" />
								km</td>
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
			</div>
			<div id="container-rerank">
				<h2>Reranking</h2>
				<p>Zaškrtněte, který reranking chcete použít.</p>
				<p>Ke každému rerankingu přiřaďte prioritu, kde 1 je <strong>nejvyšší</strong> a 4 <strong>nejnižší</strong>.</p>
				<p>Pokud daný reranking nebude mít prioritu nastavenou, nepoužije se.</p>
				<table>
					<tbody>
						<tr>
							<td>
								<input type="checkbox" id="rerank_string" name="rerank_string" />
								<label for="rerank_string">Autor</label>
							</td>
							<td>
								<label for="rerank_priority_string">Priorita: </label>
								<select name="rerank_priority_string" id="rerank_priority_string">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="rerank_geo" name="rerank_geo" />
								<label for="rerank_geo">GEO</label>
							</td>
							<td>
								<label for="rerank_priority_geo">Priorita: </label>
								<select name="rerank_priority_geo" id="rerank_priority_geo">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="rerank_date" name="rerank_date" />
								<label for="rerank_date">Datum</label>
							</td>
							<td>
								<label for="rerank_priority_date">Priorita: </label>
								<select name="rerank_priority_date" id="rerank_priority_date">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="rerank_size" name="rerank_size" />
								<label for="rerank_size">Velikost obrázku</label>
							</td>
							<td>
								<label for="rerank_priority_size">Priorita: </label>
								<select name="rerank_priority_size" id="rerank_priority_size">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<input type="submit" value="Vyhledat" />
		</form>
	</div>
	<%@ include file="include/footer.jsp"%>
</body>
</html>