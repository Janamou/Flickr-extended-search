/*
  Created Jana Moudra, jana.moudra@gmail.com
 */

var marker;
var infoWindow;

// Google maps
function initialize() {
	var latlng = new google.maps.LatLng(49.71, 15.29);
	var myOptions = {
		zoom : 4,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	marker = new google.maps.Marker();
	infoWindow = new google.maps.InfoWindow();

	var map = new google.maps.Map(document.getElementById("map_canvas"),
			myOptions);

	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng, map);

		var latitude = $("#latitude");
		var longitude = $("#longitude");

		latitude.val(event.latLng.lat());
		longitude.val(event.latLng.lng());

		infoWindow.open(map, marker);
	});

	google.maps.event.addListener(marker, 'click', function(event) {
		infoWindow.open(map, marker);
	});
}

function placeMarker(position, map) {
	infoWindow.setContent('Latitude: ' + position.lat() + ',<br>Longitude: '
			+ position.lng());

	marker.setPosition(position);
	marker.setMap(map);
	// map.panTo(position);
}

$(document).ready(function() {
	initialize();

	// Datepicker init
	$.datepicker.setDefaults($.datepicker.regional[""]);
	$(".datepicker").datepicker($.datepicker.regional["cs"]);
	$(".datepicker").datepicker({
		dateFormat : 'dd.mm.yy'
	});

	// Form submit
	$("form").submit(function() {
		if ($("#keywords").val() == "") {
			// alert("Prosím vyplňte text nebo geolokaci, podle které budete
			// vyhledávat.");
			// return false;
		}
	});

	// Spinner
	$('.arrow-top').click(function() {
		var radiusValue = $('input#radius').val();
		if (radiusValue < 32) {
			$('input#radius').val(++radiusValue);
		}
		
		return false;
	});
	
	$('.arrow-bottom').click(function() {
		var radiusValue = $('input#radius').val();
		if (radiusValue > 0) {
			$('input#radius').val(--radiusValue);
		}
		
		return false;
	});
	
	$('#radius').blur(function() {
		var radiusValue = $(this).val();
		if (radiusValue > 32) {
			radiusValue = 32;
		} else if (radiusValue < 0) {
			radiusValue = 0;
		}
		
		$(this).val(radiusValue);
	});
});
