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
<title>Blog Template · Bootstrap v5.3</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/blog/">



<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

<link href="/docs/5.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<!-- Favicons -->
<link rel="apple-touch-icon"
	href="/docs/5.3/assets/img/favicons/apple-touch-icon.png"
	sizes="180x180">
<link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-32x32.png"
	sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-16x16.png"
	sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.3/assets/img/favicons/manifest.json">
<link rel="mask-icon"
	href="/docs/5.3/assets/img/favicons/safari-pinned-tab.svg"
	color="#712cf9">
<link rel="icon" href="/docs/5.3/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#712cf9">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	width: 100%;
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}

.btn-bd-primary { -
	-bd-violet-bg: #712cf9; -
	-bd-violet-rgb: 112.520718, 44.062154, 249.437846; -
	-bs-btn-font-weight: 600; -
	-bs-btn-color: var(- -bs-white); -
	-bs-btn-bg: var(- -bd-violet-bg); -
	-bs-btn-border-color: var(- -bd-violet-bg); -
	-bs-btn-hover-color: var(- -bs-white); -
	-bs-btn-hover-bg: #6528e0; -
	-bs-btn-hover-border-color: #6528e0; -
	-bs-btn-focus-shadow-rgb: var(- -bd-violet-rgb); -
	-bs-btn-active-color: var(- -bs-btn-hover-color); -
	-bs-btn-active-bg: #5a23c8; -
	-bs-btn-active-border-color: #5a23c8;
}

.bd-mode-toggle {
	z-index: 1500;
}

.bd-mode-toggle .dropdown-menu .active .bi {
	display: block !important;
}
</style>


<!-- Custom styles for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900&amp;display=swap"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="blog.css" rel="stylesheet">
</head>
<body>

	<svg xmlns="http://www.w3.org/2000/svg" class="d-none">
  <symbol id="aperture" fill="none" stroke="currentColor"
			stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
			viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10"></circle>
    <path
			d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"></path>
  </symbol>
  <symbol id="cart" viewBox="0 0 16 16">
    <path
			d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
  </symbol>
  <symbol id="chevron-right" viewBox="0 0 16 16">
    <path fill-rule="evenodd"
			d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"></path>
  </symbol>
</svg>

	<div class="container">
		<header class="border-bottom lh-1 py-3">
			<div
				class="row flex-nowrap justify-content-between align-items-center">
				<div class="col-4 pt-1">
					<a class="link-secondary" href="#">Subscribe</a>
				</div>
				<div class="col-4 text-center">
					<a class="blog-header-logo text-body-emphasis text-decoration-none"
						href="#">Large</a>
				</div>
				<div class="col-4 d-flex justify-content-end align-items-center">
					<a class="link-secondary" href="#" aria-label="Search"> <svg
							xmlns="http://www.w3.org/2000/svg" width="20" height="20"
							fill="none" stroke="currentColor" stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" class="mx-3" role="img"
							viewBox="0 0 24 24">
							<title>Search</title><circle cx="10.5" cy="10.5" r="7.5"></circle>
							<path d="M21 21l-5.2-5.2"></path></svg>
					</a> <a class="btn btn-sm btn-outline-secondary" href="#">Sign up</a>
				</div>
			</div>
		</header>

		<div class="nav-scroller py-1 mb-3 border-bottom">
			<nav class="nav nav-underline justify-content-between">
				<a class="nav-item nav-link link-body-emphasis active" href="#">World</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">U.S.</a> <a
					class="nav-item nav-link link-body-emphasis" href="#">Technology</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Design</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Culture</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Business</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Politics</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Opinion</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Science</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Health</a>
				<a class="nav-item nav-link link-body-emphasis" href="#">Style</a> <a
					class="nav-item nav-link link-body-emphasis" href="#">Travel</a>
			</nav>
		</div>
	</div>

	<main class="container">
		<div
			class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary">
			<div id="carouselExampleAutoplaying" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="/resources/image/test_poster1.jpg" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/resources/image/test_poster2.jpg" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/resources/image/test_poster3.jpg" class="d-block w-100" alt="...">
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

	<footer class="py-5 text-center text-body-secondary bg-body-tertiary">
		<p>
			Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a>
			by <a href="https://twitter.com/mdo">@mdo</a>.
		</p>
		<p class="mb-0">
			<a href="#">Back to top</a>
		</p>
	</footer>
	<script src="/docs/5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>


	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>