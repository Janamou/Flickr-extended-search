jQuery(document).ready(function($){
	$(".gallery").each(function(index, obj){
		var galleryid = Math.floor(Math.random()*10000);
		$(obj).find("a.image_").colorbox({rel:galleryid, maxWidth:"100%", maxHeight:"100%"});
	});
	
  $(".gallery").each(function(index, obj){
		galleryid2 = Math.floor(Math.random()*10000);
		$(obj).find("a.moreinfo").colorbox({rel:galleryid2, inline:true, width:"800px", height:"500px"});
	});
	
	$("a[href$='.jpg']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.JPG']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.gif']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.GIF']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.png']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.PNG']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.jpeg']").colorbox({maxWidth:"100%", maxHeight:"100%"});
	$("a[href$='.JPEG']").colorbox({maxWidth:"100%", maxHeight:"100%"});
});