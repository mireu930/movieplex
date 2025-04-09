<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>MoviePlex</title>

<link href="/resources/image/movieplex_title.png" rel="shortcut icon"
	type="image/x-icon">

<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet" href="/resources/css/movieDetail.css">
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>

		<div class="movie-detail-wrapper">
			<div class="movie-hero-section">
				<div class="movie-info-section">
					<div class="movie-info">
						<h4 class="movie-title">${movieDTO.movieTitle }</h4>
					</div>
					<div class="movie-poster">
						<img src="https://image.tmdb.org/t/p/w500${movieDTO.shortPoster}"
							alt="포스터">
						<button class="reserve-button">예매</button>
					</div>
				</div>
				<img class="movie-bg"
					src="https://image.tmdb.org/t/p/original${movieDTO.longPoster}"
					alt="배경 이미지">
			</div>

			<div class="movie-summary-section">
				<div class="movie-summary">
					<h3>주요정보</h3>
					<p>${movieDTO.overView}</p>
				</div>
			</div>
		</div>


	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>