<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script src="/docs/5.3/assets/js/color-modes.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>ReviewNest</title>

<link href="/resources/image/reviewnest_title.png" rel="shortcut icon" type="image/x-icon">

<link rel="stylesheet" href="/resources/css/reviewNestDetail.css">
</head>
<body>
	<div class="container">
	<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
			
	<main>
				<div class="container-detail"  style="width: 100%; height: 450px; overflow: hidden;">
				     <img src="https://image.tmdb.org/t/p/w1280/${content.longPoster}"  class="img-fluid"  style="object-fit: cover; object-position: center top; width: 100%; height: 100%;">
			   </div>
			   <div class="container-body" >
				  <img src="https://image.tmdb.org/t/p/w200/${content.shortPoster}" >
				
				  <div class="text" style="flex: 1;">
				    <h2 style="margin-bottom: 10px; font-weight: bold;">${content.contentTitle}</h2>
				    <p style="color: #666; margin-bottom: 50px;">${content.releaseDate}</p>
				    <p>${content.overView}</p>
				  </div>
				</div>
			
	</main>
	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>