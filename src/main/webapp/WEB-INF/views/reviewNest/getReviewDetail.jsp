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

<link rel="stylesheet" href="/resources/css/reviewNestReviewDetail.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=arrow_back_ios" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=comment" />
</head>
<body>
	<!-- header -->
	<div class="container">
	<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
	
	<main class="gap">
	<div class="container mt-4">
    <h2>${reviewDetail.userName}님의 리뷰</h2>
    <hr style="border-color: orange; opacity: 0.7;">
    <p><strong>${content.contentTitle}</strong></p>
    <p><strong>작성 날짜:</strong> ${reviewDetail.reviewDate}</p>
    <p>
        <strong>별점:</strong>
        <c:forEach var="i" begin="1" end="${reviewDetail.reviewRate}">★</c:forEach>
        <c:forEach var="i" begin="1" end="${5 - reviewDetail.reviewRate}">☆</c:forEach>
    </p>
    <br>
    <p>${reviewDetail.reviewContents}</p>
    
    <hr>
    <a href="javascript:history.back()" class="btn-back btn-secondary">뒤로 가기</a>
   <button type="button" class="btn-modal" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
	<span class="material-symbols-outlined">comment</span>댓글 등록</button>
		
		<form id="reviewForm" action="addReview" method="post">
							    <input type="hidden" name="contentId" value="${content.contentId}">
							    <input type="hidden" name="kind" value="${content.kind}">
							
							    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
							        <div class="modal-dialog">
							            <div class="modal-content">
							                <div class="modal-header">
							                    <h1 class="modal-title fs-5" id="staticBackdropLabel">댓글</h1>
							                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							                </div>
							
							                <!-- 새로운 리뷰 작성 폼 -->
							                <div class="modal-body">
							                    <textarea class="form-control" name="reviewContents" id="reviewText" rows="4" placeholder="이 작품에 대한 생각을자유롭게 표현해주세요"></textarea>
							                </div>
							
							                <div class="modal-footer">
							                    <button type="button" class="btn-save" onclick="submitReview()">저장</button>
							                </div>
							            </div>
							        </div>
							    </div>
							</form>
	</div>
	
	<!-- 댓글 목록 -->
	<div id="commentList" class="mt-3"></div>

	
	
	</main>
	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>