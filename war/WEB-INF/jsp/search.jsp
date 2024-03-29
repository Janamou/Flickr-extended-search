<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
<link rel="stylesheet" media="aural" type="text/css" href="css/aural.css" />
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css" type="text/css" media="all" />
<link rel="stylesheet" href="http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/ui.stepper.css" type="text/css" media="all" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"
	type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/i18n/jquery-ui-i18n.min.js"
	type="text/javascript"></script>
<script src="js/jquery.ui.datepicker-cs.js" type="text/javascript"></script>
<script src="js/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/init.js" type="text/javascript"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

<title>Semestrální práce VMW - Flickr - metadata based reranking</title>
</head>
<body>
	<div id="main" class="box">
		<%@ include file="include/header.jsp"%>

		<div id="page" class="box">
			<div id="page-in" class="box">
				<div id="content">
					<form action="search" method="post" id="search">
						<div class="intro">
							<div id="container-search">
								<div id="col_search" class="noprint">
									<div id="col-in_search">

										<h3 id="word">
											<span>Klíčová slova</span>
										</h3>
										<div id="keywords_">
											<p>Vyhledání obrázků podle daných klíčových slov nebo tagů.</p>
											<table>
												<tbody>
													<tr>
														<!-- <th><label for="keywords">Klíčová slova:</label></th> -->
														<td><input type="text" name="keywords" id="keywords" />
														</td>
													</tr>
													<tr>
														<td><select name="tags_mode" id="tags_mode">
																<option value="any">Některá slova</option>
																<option value="all">Všechna slova</option>
														</select></td>
													</tr>
													<tr>
														<td><label for="text">text</label><input type="radio"
															name="text_selection" id="text" value="text"
															checked="checked" /> <label for="tags">tagy</label><input
															type="radio" name="text_selection" id="tags" value="tagy" />
														</td>
													</tr>
												</tbody>
											</table>
										</div>

										<h3>
											<span>Vyhledávání podle data</span>
										</h3>
										<div id="date_">
											<p>Omezení obrázků na zvolený rozsah data pořízení.</p>
											<table>
												<tbody>
													<tr>
														<td><label for="min_date">Od:</label> <input
															type="text" name="min_date" id="min_date"
															class="datepicker" />
														</td>
													</tr>
													<tr>
														<td><label for="max_date">Do:</label> <input
															type="text" name="max_date" id="max_date"
															class="datepicker" /></td>
													</tr>
												</tbody>
											</table>
										</div>

										<h3>
											<span>Vyhledávání podle lokace</span>
										</h3>
										<div id="lat-long_">
											<p>Omezení na obrázky v blízkosti dané lokace.</p>
											<table>
												<tbody>
													<tr>
														<td><label for="latitude">Latitude:</label>
														</td>
														<td><input type="text" name="latitude" id="latitude" />
														</td>
													</tr>
													<tr>
														<td><label for="longitude">Longitude:</label>
														</td>
														<td><input type="text" name="longitude"
															id="longitude" />
														</td>
													</tr>
													<tr>
														<td><label for="radius">Poloměr:</label>
														</td>
														<td><input type="text" name="radius" id="radius"
															value="5" />
															<span class="arrow-buttons">
																<input class="arrow-top" type="button" value=" /\ "															
																style="font-size:7px;margin:0;padding:0;width:20px;height:13px;">
																<input class="arrow-bottom" type=button value=" \/ "														
																style="font-size:7px;margin:0;padding:0;width:20px;height:12px;">
															</span>
															<span class="kilometers">km</span>													
														</td>
													</tr>												
												</tbody>
											</table>					
					          			</div>
					          	
					          			<h3><span>Maximální počet výsledků</span></h3>		
							          	<div id="results_">	
							          		<p>Maximální počet výsledků, které budou vráceny.</p>								
											<label for="search_results"></label> 
											<select name="search_results" id="search_results">
												<option value="10">10</option>
												<option value="25">25</option>
												<option value="50">50</option>
												<option value="100">100</option>
											</select>
										</div>		
					          						          	       		          			          
					          			<hr class="noscreen" />					                    
					        		</div>                
			      				</div>
						
						<p>Zadejte parametry vyhledávání, podle kterých chcete vyhledávat.</p>
						<h2>
									<span>Vyhledávání s lokací</span>
								</h2>						
						<p>Klikněte do mapy na místo pro získání dat lokace.</p>
						<div id="map_canvas" style="width: 590px; height: 750px"></div>						
					</div>					
					</div>
					
					<div class="intro">
					<div id="container-rerank">
					<h2>
						<span>Reranking</span>
					</h2>		
						<p>Při nastavení priority některému z parametrů se výsledné obrázky přetřídí podle vzdálenosti k danému parametru. Nastavte prioritu 0 až 10, kde 10 je <strong>maximální</strong> a 0 <strong>žádná</strong>. Reranking s prioritou 0 nebude použit.</p>
						<table>
							<tbody>
								<tr>
									<th>Autor</th>
									<td>
										<label for="rerank_string">Přezdívka autora: </label>
									</td>
									<td>	
										<input type="text" name="rerank_string" id="rerank_string" />
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
									<th>Velikost obrázku</th>
									<td>
										<label for="rerank_size_type">Typ třídění:</label>
									</td>
									<td>	
										<select name="rerank_size_type" id="rerank_size_type">
											<option value="desc_width">Sestupně podle šířky</option>
											<option value="asc_width">Vzestupně podle šířky</option>
											<option value="desc_height">Sestupně podle výšky</option>
											<option value="asc_height">Vzestupně podle výšky</option>
										</select>
									</td>
									<td>
										<label for="rerank_priority_size">Priorita: </label>
										<select name="rerank_priority_size" id="rerank_priority_size" >
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
									<th>Datum</th>
									<td>
										<label for="rerank_date">Datum: </label>
									</td>
									<td>	
										<input type="text" name="rerank_date" id="rerank_date" class="datepicker" />
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
									<th>GEO lokace</th>
									<td>
									</td>
									<td>
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
							</tbody>
						</table>
					</div>
					</div>
					<div id="col_search_button" class="noprint">			        	
						<input type="submit" value="Vyhledat" />											
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="include/footer.jsp"%>
</div>
</body>
</html>