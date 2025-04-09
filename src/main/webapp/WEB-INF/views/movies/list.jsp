<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="stylesheet" href="/resources/css/moviesList.css">
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>

<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
		<div class="titleWrapper mt-3">
			<div class="row justify-content-between align-items-center gx-0">
				<!-- 왼쪽: 현재 상영작 -->
				<div class="col-auto">
					<div id="title" class="p-2">현재 상영작</div>
				</div>
				<!-- 오른쪽: 검색 폼 -->
				<div class="col-auto">
					<form class="d-flex align-items-center">
						<div class="input-group">
							<input name="search" type="text" class="form-control"
								placeholder="영화명 검색">
							<button type="submit" class="btn btn-primary">🔍</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="album pb-5 pt-1 movieAlbum">
			<div class="container movieListSection">
				<div
					class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-2 justify-content-center"
					id="moviesSection">
					<c:forEach items="${movieList}" var="m" varStatus="status">
						<div class="col">
							<div class="card shadow-sm movie-card"
								data-movie-id="${m.movieId}">
								<div class="image-wrapper">
								<c:if test="${not empty m.shortPoster }">
									<img src="https://image.tmdb.org/t/p/w500/${m.shortPoster}"
										class="card-img-top" alt="${m.movieTitle}">
								</c:if>
								<c:if test="${empty m.shortPoster }">
									<img src="/resources/image/notFountImg.png"
										class="card-img-top" alt="${m.movieTitle}">
								</c:if>
									
								</div>
								<div
									class="card-body d-flex flex-column justify-content-between">
									<p class="card-text text-center movie-title">${m.movieTitle}</p>
									<div
										class="d-flex justify-content-between align-items-center mt-auto">
										<button type="button" id="bookBtn" class="btn btn-sm btn-outline-primary">예매</button>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link"
						href="./list?page=${pager.start -1}&search=${pager.search}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<c:forEach begin="${pager.start }" end="${pager.end}" var="i">
						<!-- i는 page 영역이기 때문에 EL로 작성해야함 -->
						<li class="page-item ${i == pager.page ? 'active' : ''}"><a class="page-link"
							href="./list?page=${i }&search=${pager.search}">${i}</a></li>
					</c:forEach>

					<li class="page-item ${pager.endCheck?'disabled':''}"><a
						class="page-link" href="./list?page=${pager.end + 1}&search=${pager.search}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
		</nav>
	</main>

	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
	<script src="/resources/js/moviesList.js"></script>
</body>

</html>