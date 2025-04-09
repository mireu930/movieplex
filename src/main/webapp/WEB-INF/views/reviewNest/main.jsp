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

<link rel="stylesheet" href="/resources/css/reviewNestMain.css">

</head>
<body>
	<div class="container">
		<c:import
			url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
	<div>
	<img src="/resources/image/ban.jpg" width="100%" height="100px">
	</div>
	<main class="container">
		<div class ="p-5 p-md-6 mb-5 w-100 rounded text-body-emphasisbg-body-secondary">
			<div id="carouselExampleAutoplaying" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner" width="960px">
					<div class="carousel-item active">
						<img src="/resources/image/stella.jpg" class="d-block w-100"
							alt="...">
					</div>
					<div class="carousel-item">
						<img src="/resources/image/un.jpg" class="d-block w-100"
							alt="...">
					</div>
					<div class="carousel-item">
						<img src="/resources/image/top.jpg" class="d-block w-100"
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
		
		<strong class="hot_rank d-inline-block mb-2 text-success-emphasis">리뷰네스트 HOT 랭킹</strong>
			
	
		
		<div class="row g-4 align-items-stretch">
			<div class="col-md-6">
				<div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col-md-8 p-4 d-flex flex-column position-static">
						<div class="mb-1 text-body-secondary">TODAY'S MOVIE</div>
						<h5 class="mb-0"><strong>미키 17</strong></h5>
						<br>
						<p class="card-text mb-auto">위험한 일에 투입되는 소모품(익스펜더블)으로, 죽으면 다시 프린트되는 ‘미키’가 17번째 죽음의 위기를 겪던 중, 그가 죽은 줄 알고 
						‘미키 18’이 프린트되면서 벌어지는 예측불허의 이야기를 그리는 영화.</p>
						
					</div>
					<div class="col-md-4 d-none d-lg-block">
						<img src="/resources/image/17.jpg"  width="100%" height="100%" style="object-fit: cover;">
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col-md-8 p-4 d-flex flex-column position-static">
						<div class="mb-1 text-body-secondary">TODAY'S MOVIE</div>
						<h5 class="mb-0"><strong>폭싹 속았수다</strong></h5>
						<br>
						<p class="card-text mb-auto">당차고 야무진 소녀와 우직하고 헌신적인 소년. 제주 바닷가 작은 마을에서 한 뼘씩 자라온 두 사람의 인생은 어디로 향할까. 
						넘어지고 좌절해도 다시 일어서며, 세월을 뛰어넘어 피어나는 사랑 이야기.</p>
						
					</div>
					<div class="col-md-4 d-none d-lg-block">
						<img src="/resources/image/iu.jpg"  width="100%" height="100%" style="object-fit: cover;">
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col-md-8 p-4 d-flex flex-column position-static">
						<div class="mb-1 text-body-secondary">TODAY'S MOVIE</div>
						<h5 class="mb-0"><strong>인턴</strong></h5>
						<br>
						<p class="card-text mb-auto">창업 1년 반 만에 직원 220명의 성공신화를 이룬 열정적인 젊은 여성 CEO 줄스(앤 해서웨이)는 원치않는
						수십 년 직장생활에서 비롯된 노하우와 나이만큼 풍부한 인생경험이 무기인 만능 70세의 벤(로버트 드 니로)을 인턴으로 채용하게 되는데...</p>
						
					</div>
					<div class="col-md-4 d-none d-lg-block">
						<img src="/resources/image/in.jpg"  width="100%" height="100%" style="object-fit: cover;">
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col-md-8 p-4 d-flex flex-column position-static">
						<div class="mb-1 text-body-secondary">TODAY'S MOVIE</div>
						<h5 class="mb-0"><strong>협상의 기술</strong></h5>
						<br>
						<p class="card-text mb-auto">전설의 협상가로 불리는 대기업의 M&A 전문가와 그 팀들의 기업 간의 인수 합병 체결 프로젝트의 활약상을 담은 드라마</p>
						
					</div>
					<div class="col-md-4 d-none d-lg-block">
						<img src="/resources/image/nego.jpg"  width="100%" height="100%" style="object-fit: cover;">
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