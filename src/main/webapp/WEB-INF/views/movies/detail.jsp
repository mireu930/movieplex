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
	<main class="main">

		<div class="movie-detail-wrapper">
			<div class="movie-hero-section">
				<div class="movie-info-section">
					<div class="movie-info">
						<h4 class="movie-title">${movieDTO.movieTitle }</h4>
					</div>
					<div class="movie-poster">

						<c:if test="${not empty movieDTO.shortPoster }">
							<img src="https://image.tmdb.org/t/p/w500${movieDTO.shortPoster}"
								alt="포스터">
						</c:if>
						<c:if test="${empty movieDTO.shortPoster}">
							<div class=empty></div>
						</c:if>
						<a href="/movieBooks/booking" class="reserve-button btn btn-info">예매</a>
					</div>
				</div>
				<c:if test="${not empty movieDTO.longPoster }">
					<img class="movie-bg"
						src="https://image.tmdb.org/t/p/original${movieDTO.longPoster}"
						alt="배경 이미지">
				</c:if>
			</div>

			<div class="movie-summary-section">
				<div class="summary-wrapper">
					<div class="movie-summary">
						<h3>주요정보</h3>
						<p>${movieDTO.overView}</p>
					</div>
					<div class="movie-releaseDate">
						<h3>개봉일</h3>
						<p>${movieDTO.releaseDate}</p>
					</div>

					<c:if test="${not empty movieDTO.video}">
						<div class="movie-trailer">
							<h3>예고편</h3>
							<iframe width="100%" height="315"
								src="https://www.youtube.com/embed/${movieDTO.video}"
								title="예고편" frameborder="0"
								allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
								allowfullscreen> </iframe>
						</div>
					</c:if>
					<c:if
						test="${empty movieDTO.video && not empty movieDTO.longPoster}">
						<div class="movie-trailer">
							<h3>스틸컷</h3>
							<img class="summary-img"
								src="https://image.tmdb.org/t/p/original${movieDTO.longPoster}"
								alt="배경 이미지">
						</div>
					</c:if>
				</div>
			</div>
		</div>


	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>