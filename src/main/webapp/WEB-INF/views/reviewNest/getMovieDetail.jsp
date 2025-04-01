<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>ReviewNest</title>

<link href="/resources/image/reviewnest_title.png" rel="shortcut icon" type="image/x-icon">

<link rel="stylesheet" href="/resources/css/reviewNestDetail.css">
</head>
<body>
	<div class="container">
	<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
			
	<main>
				<div class="container-ban"  >
				     <img src="https://image.tmdb.org/t/p/w1280/${content.longPoster}"  class="img-fluid">
			   </div>
			   
			   <div class="container-body" >
				  <img src="https://image.tmdb.org/t/p/w200/${content.shortPoster}" >
				
				  <div class="text" style="flex: 1;">
				    <h2 style="margin-bottom: 10px; font-weight: bold;">${content.contentTitle}</h2>
				    <p style="color: #666; margin-bottom: 50px;">${content.releaseDate}</p>
				    <p>${content.overView}</p>
				 
				<div class="star-container">
				    <div class="star-rating">
				        <span>★</span>
				        <span>★</span>
				        <span>★</span>
				        <span>★</span>
				        <span>★</span>
				    </div>
					<p id="selected-rating">나의 평점: 0</p>
				</div>
				</div>
			 </div>
					<p class="d-inline-flex gap-1">
						  <a class="btn-review" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
						    리뷰 남기기
						  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					     <textarea class="form-control" id="reviewText" rows="4" placeholder="이 작품에 대한 평가를 자유롭게 글로 남겨보세요ㅇㅇㅇ"></textarea>
						    <button class="btn mt-2"  type="submit">저장</button>
						 </div>
					  </div>
				

				<div class="container-review">
						<strong class="hot_rank d-inline-block mb-2 text-success-emphasis">리뷰</strong>
							<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-3">
							  <div class="col">
							    <div class="card" style="width: 100%;">
							      <div class="card-body">
							        <h5 class="card-title">Special title treatment 1</h5>
							        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
							        <a href="#" class="btn btn-primary">Go somewhere</a>
							      </div>
							    </div>
							  </div>
							
							  <div class="col">
							    <div class="card" style="width: 100%;">
							      <div class="card-body">
							        <h5 class="card-title">Special title treatment 2</h5>
							        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
							        <a href="#" class="btn btn-primary">Go somewhere</a>
							      </div>
							    </div>
							  </div>
							
							  <div class="col">
							    <div class="card" style="width: 100%;">
							      <div class="card-body">
							        <h5 class="card-title">Special title treatment 3</h5>
							        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
							        <a href="#" class="btn btn-primary">Go somewhere</a>
							      </div>
							    </div>
							  </div>
							
							  <div class="col">
							    <div class="card" style="width: 100%;">
							      <div class="card-body">
							        <h5 class="card-title">Special title treatment 4</h5>
							        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
							        <a href="#" class="btn btn-primary">Go somewhere</a>
							      </div>
							    </div>
							  </div>
							</div>
							
					</div>
	</main>
	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script>
document.addEventListener("DOMContentLoaded", function () {
    const stars = document.querySelectorAll(".star-rating span");
    let selectedRating = 0; // 클릭으로 선택된 평점 저장

    // 별점 클릭 (선택한 값 고정)
    stars.forEach((star, index) => {
        star.addEventListener("click", function () {
            selectedRating = index + 1; // 선택한 값 저장
            updateStars(selectedRating);
        });

        // 마우스 오버 (hover한 별까지만 색칠)
        star.addEventListener("mouseover", function () {
            updateStars(index + 1, true); // hover 모드 활성화
        });

        // 마우스 아웃 (기존 선택한 값 유지)
        star.addEventListener("mouseout", function () {
            updateStars(selectedRating, false); // 선택한 값 유지
        });
    });

    // ⭐ 별 색칠 함수 (클릭 or hover)
    function updateStars(value, isHover = false) {
        stars.forEach((s, i) => {
            if (isHover) {
                s.classList.toggle("hovered", i < value); // hover한 별까지만 색칠
            } else {
                s.classList.remove("hovered"); // hover 효과 제거
                s.classList.toggle("selected", i < value); // 클릭한 값만 색칠
            }
        });

        // 선택한 평점 표시
        document.getElementById("selected-rating").innerText = "선택한 평점: " + (value > 0 ? value : "없음");
    }
    
    function saveReview() {
        const review = document.getElementById("reviewText").value;
        if (review.trim() === "") {
          alert("리뷰를 입력하세요!");
          return;
        }
        alert("리뷰가 저장되었습니다: " + review);
      }
});



</script>

</body>
</html>