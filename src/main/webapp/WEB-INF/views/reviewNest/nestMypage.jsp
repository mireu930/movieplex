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

<link rel="stylesheet" href="/resources/css/reviewNestMypage.css">
</head>
<body>
	<!-- header -->
	<div class="container">
	<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
	<main>
	
	<div class="container mt-4">
	<div class="user-info">
    	<h5><strong>${userName}</strong> 님, 환영합니다 😊</h5>
	</div>
    <hr style="border-color: orange; opacity: 0.7;">
	<c:if test="${not empty errorMessage}">
	    <div class="alert" style="background-color: #ffe5b4;">${errorMessage}</div>
	</c:if>
		
		<br>
		<div class="section">
		    <h4><strong>📝 내가 쓴 리뷰</strong></h4>
		    <c:choose>
		        <c:when test="${not empty myReviews}">
		            <c:forEach var="review" items="${myReviews}">
						  <div class="review-item">
						    <p>${review.reviewContents}</p>
						
						    <div class="d-flex gap-2 justify-content-end mt-2">
							    <!-- 수정 버튼 -->
							    <button type="button" class="btn-mypage btn-edit" data-bs-toggle="modal" data-bs-target="#editModal-${review.reviewId}">
							        수정
							    </button>
							
							    <!-- 삭제 버튼 -->
							    <form action="/reviewNest/deleteReview" method="post">
								    <input type="hidden" name="reviewId" value="${review.reviewId}">
								    <button type="submit" class="btn-mypage btn-delete" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
								</form>
							    
							</div>

						
						    <div class="modal fade" id="editModal-${review.reviewId}" tabindex="-1" aria-labelledby="editModalLabel-${review.reviewId}" aria-hidden="true">
							  <div class="modal-dialog">
							    <form action="/reviewNest/updateReview" method="post">
							      <input type="hidden" name="reviewId" value="${review.reviewId}" />
							      <input type="hidden" name="contentId" value="${review.contentId}" />
							      <input type="hidden" name="userNum" value="${review.userNum}" />
							
							      <div class="modal-content">
							        <div class="modal-header">
							          <h5 class="modal-title" id="editModalLabel-${review.reviewId}">리뷰 수정</h5>
							          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
							        </div>
							        <div class="modal-body">
							          <label for="reviewRate">별점</label>
							          <select class="form-select" name="reviewRate">
							            <c:forEach var="i" begin="1" end="5">
							              <option value="${i}" ${i == review.reviewRate ? 'selected' : ''}>${i} ★</option>
							            </c:forEach>
							          </select>
							
							          <label class="mt-3" for="reviewContents">리뷰 내용</label>
							          <textarea class="form-control" name="reviewContents" rows="4">${review.reviewContents}</textarea>
							        </div>
							        <div class="modal-footer">
							          <button type="submit" class="btn-save">저장</button>
							        </div>
							      </div>
							    </form>
							  </div>
							</div>
						    </div>
						</c:forEach>

		        </c:when>
		        <c:otherwise>
		            <p class="empty">작성한 리뷰가 없습니다.</p>
		        </c:otherwise>
		    </c:choose>
		   
		</div>
		<br>
			
			<div class="section">
		    <h4><strong>💬 내가 쓴 댓글 </strong></h4>
		    <c:choose>
		        <c:when test="${not empty myComments}">
		            <c:forEach var="comment" items="${myComments}">
						  <div class="comment-item">
						    <p>${comment.commentContents}</p>
						
						    <div class="d-flex gap-2 justify-content-end mt-2">
							    <!-- 수정 버튼 -->
							    <button type="button" class="btn-mypage btn-edit" data-bs-toggle="modal" data-bs-target="#editModal-${comment.commentId}">
							        수정
							    </button>
							
							    <!-- 삭제 버튼 -->
							    <form action="/reviewNest/deleteComment" method="post">
								    <input type="hidden" name="commentId" value="${comment.commentId}">
								    <button type="submit" class="btn-mypage btn-delete" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
								</form>
							    
							</div>

						
						    <div class="modal fade" id="editModal-${comment.commentId}" tabindex="-1" aria-labelledby="editModalLabel-${comment.commentId}" aria-hidden="true">
							  <div class="modal-dialog">
							    <form action="/reviewNest/updateComment" method="post">
							      <input type="hidden" name="commentId" value="${comment.commentId}" />
							      <input type="hidden" name="commentId" value="${comment.commentId}" />
							      <input type="hidden" name="userNum" value="${comment.userNum}" />
							
							      <div class="modal-content">
							        <div class="modal-header">
							          <h5 class="modal-title" id="editModalLabel-${comment.commentId}">댓글 수정</h5>
							          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
							        </div>
							        <div class="modal-body">						          
							          <label class="mt-3" for="commentContents">댓글 내용</label>
							          <textarea class="form-control" name="commentContents" rows="4">${comment.commentContents}</textarea>
							        </div>
							        <div class="modal-footer">
							          <button type="submit" class="btn-save">저장</button>
							        </div>
							      </div>
							    </form>
							  </div>
							</div>
						    </div>
						</c:forEach>

		        </c:when>
		        <c:otherwise>
		            <p class="empty">작성한 리뷰가 없습니다.</p>
		        </c:otherwise>
		    </c:choose>
		   
		</div>
			
			<br>
			<div class="section">
			    <h4><strong>❤️ 내가 좋아요 누른 작품</strong></h4>
			    <c:choose>
			        <c:when test="${not empty likedContents}">
			            <c:forEach var="like" items="${likedContents}">
			                <div class="item">${like.contentTitle}</div>
			            </c:forEach>
			        </c:when>
			        <c:otherwise>
			            <p class="empty">좋아요한 콘텐츠가 없습니다.</p>
			        </c:otherwise>
			    </c:choose>
			</div>
			<br>
			<br>
			<br>
			<div class="section">
			    <h4><strong>❤️ 내가 좋아요 누른 리뷰</strong></h4>
			    <c:choose>
			        <c:when test="${not empty likedReviews}">
			            <c:forEach var="review" items="${likedReviews}">
			                <div class="item">${review.reviewContents}</div>
			            </c:forEach>
			        </c:when>
			        <c:otherwise>
			            <p class="empty">좋아요한 리뷰가 없습니다.</p>
			        </c:otherwise>
			    </c:choose>
			</div>
		</div>
		</div>
		
		
	
	
	</main>
	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script>
function openModal(id) {
    document.getElementById("modal-" + id).style.display = "block";
}

function closeModal(id) {
    document.getElementById("modal-" + id).style.display = "none";
}

// 바깥 영역 클릭 시 모달 닫기
window.onclick = function(event) {
    document.querySelectorAll(".modal").forEach(modal => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
}
</script>
</body>
</html>