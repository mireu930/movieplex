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
<link rel="stylesheet" href="/resources/css/moviesList.css">
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
<div class="album py-5 movieAlbum">
    <div class="container movieListSection">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-2 justify-content-center" id="moviesSection">
            <c:forEach items="${topMovieList}" var="top" varStatus="status">
                <div class="col">
                    <div class="card shadow-sm movie-card" data-movie-id="${top.movieId}">
                        <div class="image-wrapper">
                            <img src="https://image.tmdb.org/t/p/w500/${top.shortPoster}" class="card-img-top" alt="${top.movieTitle}">
                        </div>
                        <div class="card-body d-flex flex-column justify-content-between" >
                            <p class="card-text text-center movie-title">${top.movieTitle}</p>
                            <div class="d-flex justify-content-between align-items-center mt-auto">
                                <button type="button" class="btn btn-sm btn-outline-primary">예매</button>
                                <small class="text-body-secondary">${status.index + 1} 위</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

	</main>

	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
	<script src="/resources/js/moviesList.js"></script>
</body>
</html>