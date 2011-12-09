jQuery(document).ready(function($){
	$(".gallery").each(function(index, obj){
		var galleryid = Math.floor(Math.random()*10000);
		$(obj).find("a.image").colorbox({rel:galleryid, maxWidth:"90%", maxHeight:"90%"});
	});
	
  $(".gallery").each(function(index, obj){
		galleryid2 = Math.floor(Math.random()*10000);
		$(obj).find("a.moreinfo").colorbox({rel:galleryid2, inline:true, width:"700px", height:"500px"});
	});
	
	$("a[href$='.jpg']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.JPG']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.gif']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.GIF']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.png']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.PNG']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.jpeg']").colorbox({maxWidth:"90%", maxHeight:"90%"});
	$("a[href$='.JPEG']").colorbox({maxWidth:"90%", maxHeight:"90%"});
});