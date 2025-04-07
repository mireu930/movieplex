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
<title>Album example · Bootstrap v5.3</title>

<link rel="stylesheet" href="/resources/css/main.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>

		<section class="py-5 text-center container">
			<div class="row py-lg-5">
				<div class="col-lg-6 col-md-8 mx-auto">
					<img src="https://image.tmdb.org/t/p/w500${movieDTO.longPoster }">
				</div>
			</div>
		</section>

		<div class="album py-5 bg-body-tertiary">
			
		</div>

	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>