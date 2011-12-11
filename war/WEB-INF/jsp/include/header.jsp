<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<script type="text/javascript">
function setActive() {
	  aObj = document.getElementById('tab').getElementsByTagName('a');
	  for(i=0;i<aObj.length;i++) { 
	    if(document.location.href.indexOf(aObj[i].href)>=0) {
	      aObj[i].className='active';
	    }
	  }
	}

window.onload = setActive;
</script>

<div id="header">
	<div id="logo">
		<h1><a href="/index">Flickr: Metadata-based reranking</a></h1>
	</div>		   	   	  
</div>

<div id="tabs" class="noprint">
   	<h3 class="noscreen">Navigation</h3>
    <ul class="box menu menu-h" id="tab">	      	    
      <li><a href="/index">Domů<span class="tab-l"></span><span class="tab-r"></span></a></li>
      <li><a href="/search">Vyhledávání<span class="tab-l"></span><span class="tab-r"></span></a></li>
      <li><a href="/about">O projektu<span class="tab-l"></span><span class="tab-r"></span></a></li>      	      
    </ul>
 	<hr class="noscreen" />
</div>