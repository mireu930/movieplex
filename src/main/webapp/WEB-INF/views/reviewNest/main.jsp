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

<link rel="stylesheet" href="/resources/css/reviewNestMain.css">

</head>
<body>
	<div class="container">
		<c:import
			url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>

	<main class="container">
		<div class ="p-4 p-md-5 mb-4 rounded text-body-emphasisbg-body-secondary">
			<div id="carouselExampleAutoplaying" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="/resources/image/test_nest1.jpg" class="d-block w-100"
							alt="...">
					</div>
					<div class="carousel-item">
						<img src="/resources/image/test_nest2.jpg" class="d-block w-100"
							alt="...">
					</div>
					<div class="carousel-item">
						<img src="/resources/image/test_nest4.jpeg" class="d-block w-100"
							alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>

		<div class="row mb-2">
			<div class="col-md-6">
				<div
					class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col p-4 d-flex flex-column position-static">
						<strong class="d-inline-block mb-2 text-primary-emphasis">World</strong>
						<h3 class="mb-0">Featured post</h3>
						<div class="mb-1 text-body-secondary">Nov 12</div>
						<p class="card-text mb-auto">This is a wider card with
							supporting text below as a natural lead-in to additional content.</p>
						<a href="#" class="icon-link gap-1 icon-link-hover stretched-link">
							Continue reading <svg class="bi">
								<use xlink:href="#chevron-right"></use></svg>
						</a>
					</div>
					<div class="col-auto d-none d-lg-block">
						<svg class="bd-placeholder-img" width="200" height="250"
							xmlns="http://www.w3.org/2000/svg" role="img"
							aria-label="Placeholder: Thumbnail"
							preserveAspectRatio="xMidYMid slice" focusable="false">
							<title>Placeholder</title><rect width="100%" height="100%"
								fill="#55595c"></rect>
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div
					class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col p-4 d-flex flex-column position-static">
						<strong class="d-inline-block mb-2 text-success-emphasis">Design</strong>
						<h3 class="mb-0">Post title</h3>
						<div class="mb-1 text-body-secondary">Nov 11</div>
						<p class="mb-auto">This is a wider card with supporting text
							below as a natural lead-in to additional content.</p>
						<a href="#" class="icon-link gap-1 icon-link-hover stretched-link">
							Continue reading <svg class="bi">
								<use xlink:href="#chevron-right"></use></svg>
						</a>
					</div>
					<div class="col-auto d-none d-lg-block">
						<svg class="bd-placeholder-img" width="200" height="250"
							xmlns="http://www.w3.org/2000/svg" role="img"
							aria-label="Placeholder: Thumbnail"
							preserveAspectRatio="xMidYMid slice" focusable="false">
							<title>Placeholder</title><rect width="100%" height="100%"
								fill="#55595c"></rect>
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
					</div>
				</div>
			</div>
		</div>
	</main>
	<c:import
		url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

	<script src="/docs/5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>


	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>