<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Hugo 0.122.0">
	<title>MoviePlex</title>

	<link href="/resources/image/movieplex_title.png" rel="shortcut icon" type="image/x-icon">

	<link rel="stylesheet" href="/resources/css/main.css">
	<link rel="stylesheet" href="/resources/css/booking.css">
	<link rel="stylesheet" href="/resources/css/seatBook.css">
	<link rel="stylesheet" href="/resources/css/payment.css">
	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>

<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main id="main">
		<div class="container mt-4">

			<!-- 날짜 슬림 달력 -->
			<div class="date-nav mb-4">
				<div class="date-strip-wrapper w-100">
					<div class="date-strip" id="date-strip">
						<!-- JS로 날짜 생성 -->
					</div>
				</div>
			</div>

			<!-- 본문 3단 영역 -->
			<div class="row">
				<!-- 영화 목록 -->
				<div class="col-md-6">
					<div class="section movie-list">
						<h5>영화</h5>
						<hr>
						<ul id="movie-list">
							<c:forEach items="${list }" var="l">
								<li data-movie-id="${l.movieId }" class="movie-item">
									<strong class="mb-1">${l.movieTitle }</strong>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<!-- 시간 목록 -->
				<div class="col-md-6">
					<div class="section time-list">
						<h5>시간</h5>
						<hr>
						<ul id="theater-list" class="list-group list-group-flush">

						</ul>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
	<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
	<script src="/resources/js/booking.js"></script>
</body>

</html>