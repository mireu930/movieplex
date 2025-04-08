<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<link rel="stylesheet" href="/resources/css/reviewNestDetail.css">
</head>
<body>
	<div class="container">
	<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
			
	<main>
				<div class="container-detail"  style="width: 100%; height: 450px; overflow: hidden;">
				     <img src="https://image.tmdb.org/t/p/w1280/${content.longPoster}"  class="img-fluid"  style="object-fit: cover; object-position: center top; width: 100%; height: 100%;">
			   </div>
			   <div class="container-body" >
				  <img src="https://image.tmdb.org/t/p/w200/${content.shortPoster}" >
				
				  <div class="text" style="flex: 1;">
				    <h2 style="margin-bottom: 10px; font-weight: bold;">${content.contentTitle}</h2>
				    <p style="color: #666; margin-bottom: 50px;">${content.releaseDate}</p>
				    <p>${content.overView}</p>
				    
				    <button type="button" class="btn-modal" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
						  리뷰 남기기
						</button>
						
						<!-- Modal -->
						<form id="reviewForm" action="addReview" method="post">
							    <input type="hidden" name="contentId" value="${content.contentId}">
							    <input type="hidden" name="kind" value="${content.kind}">
							
							    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
							        <div class="modal-dialog">
							            <div class="modal-content">
							                <div class="modal-header">
							                    <h1 class="modal-title fs-5" id="staticBackdropLabel">${content.contentTitle}</h1>
							                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							                </div>
							
							                <!-- 새로운 리뷰 작성 폼 -->
							                <div class="modal-body">
							                    <label for="reviewRate">나의 별점</label>
							                    <select name="reviewRate" id="reviewRate" class="form-select">
							                        <c:forEach var="i" begin="1" end="5">
							                            <option value="${i}">${i} ★</option>
							                        </c:forEach>
							                    </select>
												<br>
							                    <label for="reviewText">나의 리뷰</label>
							                    <textarea class="form-control" name="reviewContents" id="reviewText" rows="4" placeholder="이 작품에 대한 평가를 자유롭게 글로 남겨보세요"></textarea>
							                </div>
							
							                <div class="modal-footer">
							                    <button type="button" class="btn-save" onclick="submitReview()">저장</button>
							                </div>
							            </div>
							        </div>
							    </div>
							</form>
						
				    
				  </div>
				</div>
				
				<div class="container-custom mt-4">
						<strong class="hot_rank d-inline-block mb-2 text-success-emphasis">리뷰</strong>
						<div class="row">
						    <c:forEach var="review" items="${reviewList}">
						        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
						        	<div class="card border border-secondary">
						            <div class="card-body">
						                <strong class="card-title">${review.userName}</strong>
						                <hr class="special-hr">
						                <p class="card-text"></p>
						                    <h6>${review.userName}님의 별점:</h6>
						                    <c:forEach var="i" begin="1" end="${review.reviewRate}">★</c:forEach>
						                    <c:forEach var="i" begin="1" end="${5 - review.reviewRate}">☆</c:forEach>
						                <p class="card-text">
						                	<a href="/reviewNest/getReviewDetail?reviewId=${review.reviewId}" class="text-decoration-none text-dark">
										   <c:choose>
										        <c:when test="${fn:length(review.reviewContents) >30}">
										            <span class="short-review">${fn:substring(review.reviewContents, 0, 30)}...</span>
										            <span class="full-text" style="display: none;">${review.reviewContents}</span>
										        </c:when>
										        <c:otherwise>
										            ${review.reviewContents}
										        </c:otherwise>
										    </c:choose> 
										    </a>
										</p>
						                <hr class="special-hr">
						           		<button class="like-button"
									        data-usernum="${userNum}"
									        data-review-id="${review.reviewId}"
									        data-kind="0">
									    <span class="heart-icon">
									        <c:choose>
									            <c:when test="${likedReviewIds != null && likedReviewIds.contains(review.reviewId)}">
									                ❤️
									            </c:when>
									            <c:otherwise>
									                🤍
									            </c:otherwise>
									        </c:choose>
									    </span>
									</button>
						            </div>
						   
						        </div>
						        </div>
						    </c:forEach>
						</div>
						</div>
			
	</main>
	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script src="/resources/js/getContentsDetail.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function() {
	  document.querySelectorAll(".like-button").forEach(function(button) {
	    button.addEventListener("click", function() {
	      const reviewId = button.dataset.reviewId;
	      const userNum = button.dataset.usernum;
	      const kind = button.dataset.kind;

	      console.log("! 리뷰 좋아요 클릭됨");
	      console.log("reviewId:", reviewId);
	      console.log("userNum:", userNum);
	      console.log("kind:", kind); 
	      
	      if (!userNum) {
	            alert("로그인이 필요합니다.");
	            location.href = "/users/login";
	            return;
	        }

	      fetch("/reviewNest/toggleReviewLike", {
	        method: "POST",
	        headers: {
	          "Content-Type": "application/json"
	        },
	        body: JSON.stringify({
	          reviewId: parseInt(reviewId),
	          userNum: parseInt(userNum),
	          kind: parseInt(kind)
	        })
	      })
	      .then(response => response.json())
	      .then(data => {
	        const heartIcon = button.querySelector(".heart-icon");
	        if (data.liked) {
	          heartIcon.textContent = "❤️";
	        } else {
	          heartIcon.textContent = "🤍";
	        }
	      })
	      .catch(error => {
	        console.error("리뷰 좋아요 오류:", error);
	        alert("좋아요 처리 중 오류가 발생했습니다.");
	      });
	    });
	  });
	});
</script>
</body>
</html>