<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html data-bs-theme="light">
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

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
	<script type="module" src="https://cdn.jsdelivr.net/npm/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://cdn.jsdelivr.net/npm/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<style>
  .chat-button {
    position: fixed;
    bottom: 20px;
    right: 20px;
    background-color: #dc3545;
    color: white;
    border: none;
    border-radius: 50px;
    width: 60px;
    height: 60px;
    font-size: 14px;
    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2);
    cursor: pointer;
  }
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>

		<div>
			<input type="hidden" name="name" id="name" value="${user.userName}" class="short">
			<input type="hidden" name="usergrade" id="usergrade" value="${user.userGrade}" class="short">
			 <button type="button"  class="in chat-button" id="chatbtn"><ion-icon name="chatbubble-outline" size="large"></ion-icon></button>
		</div>


		<div id="myCarousel" class="carousel slide mb-6"
			data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#myCarousel"
					data-bs-slide-to="0" class="active" aria-label="Slide 1"
					aria-current="true"></button>
				<button type="button" data-bs-target="#myCarousel"
					data-bs-slide-to="1" aria-label="Slide 2" class=""></button>
				<button type="button" data-bs-target="#myCarousel"
					data-bs-slide-to="2" aria-label="Slide 3" class=""></button>
			</div>
			<div class="carousel-inner">
				<div id="carouselExampleAutoplaying" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="/resources/image/test_poster1.jpg"
								class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/resources/image/test_poster2.jpg"
								class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/resources/image/test_poster3.jpg"
								class="d-block w-100" alt="...">
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
			<button class="carousel-control-prev" type="button"
				data-bs-target="#myCarousel" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#myCarousel" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

		<div class="album py-5 bg-body-tertiary">
			<div class="container">

				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<c:forEach items="${topMovieList }" var="top" varStatus="status">
						<div class="col">
							<div class="card shadow-sm">
								<img class="card-img-top" src="https://image.tmdb.org/t/p/w500${top.shortPoster }">
								<div class="card-body">
									<p class="card-text">${top.movieTitle}</p>
									<div class="d-flex justify-content-between align-items-center">
										<div class="btn-group">
											<button type="button"
												class="btn btn-sm btn-outline-danger">예매하기</button>
										</div>
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
	<hr>

	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
	
	<script>
	
	document.querySelectorAll('.in').forEach(button => {
	    button.addEventListener('click', (e) => {
	        let name = document.getElementById('name').value;
			let usergrade = document.getElementById('usergrade').value;
	        
	        if (e.target.dataset.name && e.target.dataset.name.trim() && e.target.dataset.usergrade &&  e.target.dataset.usergrade.trim()  !== '') {
	            name = e.target.dataset.name;
				usergrade = e.target.dataset.usergrade;
	        }
	        
	        let child = window.open('/chat', 'chat', 'width=405,height=510');
	        
	        child.addEventListener('load', function() {
	            // 자식 창이 모두 로드된 후 실행
	            child.connect(name);
	        });

			let checkClosed = setInterval(() => {
            if (child.closed) {
                clearInterval(checkClosed); // 반복 중지
                enableButtons(); 
            }
        }, 500);
	        
	        // 버튼 비활성화 및 입력 필드 읽기 전용 설정
	        document.querySelectorAll('.in').forEach(btn => {
	            btn.style.opacity = 0.5;
	            btn.disabled = true;
	        });
	        document.getElementById('name').readOnly = true;
	    });
	});

	function enableButtons() {
    document.querySelectorAll('.in').forEach(btn => {
        btn.style.opacity = 1;
        btn.disabled = false;
    });
    document.getElementById('name').readOnly = false;
}
	
	</script>
	<script src="/docs/5.3/assets/js/color-modes.js"></script>
</body>
</html>