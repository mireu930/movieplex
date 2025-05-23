<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script src="/docs/5.3/assets/js/color-modes.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
	<main>
	<!-- 컨텐츠 넣는 부분 -->
			<div class="container">
				<div class="titleWrapper mt-3">
					<div class="row justify-content-between align-items-center gx-0">
						<!-- 왼쪽 -->
						<div class="col-auto">
							<strong class="hot_rank d-inline-block mb-2 text-success-emphasis">리뷰네스트 영화 인기 순위</strong>
						</div>
						<!-- 오른쪽: 검색 폼 -->
						<div class="col-auto">
							<form class="d-flex align-items-center">
								<div class="input-group">
									<input name="search" type="text" class="form-control"
										placeholder="영화명 검색">
									<button type="submit" class="btn-search">🔍</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<c:choose>
				  <c:when test="${empty movieList}">
				    <div class="text-center mt-5">
				      <c:choose>
				        <c:when test="${not empty search}">
				         <div class="alert text-center mt-4"  style="background-color: #ffe5b4;" role="alert">
						아쉽게도 '${search}'은 아직 등록되어 있지 않아요.<br> <strong>대신 인기 있는 다른 작품들을 확인해보세요!</strong>
						</div>
				        </c:when>
				        <c:otherwise>
				          <h5>현재 등록된 작품이 없습니다.</h5>
				        </c:otherwise>
				      </c:choose>
				    </div>
				  </c:when>
				
				  <c:otherwise>
				
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-5 g-3">
					<c:forEach items="${movieList}" var="content" varStatus="status">
						<div class="col">
							<div class="card shadow-sm">
								<a href="/reviewNest/getMovieDetail?contentId=${content.contentId}">
									<img src="https://image.tmdb.org/t/p/w500/${content.shortPoster }"  id="poster">
							 	</a>
								<div class="card-body">
									<p class="card-text">${content.contentTitle}</p>
									<div class="d-flex justify-content-end">
										<button class="like-button"
									        data-usernum="${userNum}"
									        data-contentid="${content.contentId}"
									        data-kind="0">
									    <span class="heart-icon">
									        <c:choose>
									            <c:when test="${likedContentIds != null && likedContentIds.contains(content.contentId)}">
									                ❤️
									            </c:when>
									            <c:otherwise>
									                🤍
									            </c:otherwise>
									        </c:choose>
									    </span>
									</button>
									</div>
									<div class="d-flex justify-content-between align-items-center">
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
			</c:choose>
			
			<nav aria-label="Page navigation example" class="mb-5">
			  <ul class="pagination d-flex justify-content-center mt-5">
			    <!-- Previous Button -->
			    <li class="page-item ${pager.page <= 1 ? 'disabled' : ''}">
			      <a class="page-link pages arrow text-white" 
			         href="?page=${pager.page - 1}&search=${pager.search}" 
			         aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    
			    <!-- Page Number Buttons -->
			    <c:if test="${pager.start > 0 && pager.end > 0}">
				    <c:forEach begin="${pager.start}" end="${pager.end}" var="i">
				      <li class="page-item ${pager.page == i ? 'active' : ''}">
				        <a class="page-link pages page-number" 
				           href="?page=${i}" 
				           data-page-num="${i}"> ${i}</a>
				      </li>
				    </c:forEach>
				  </c:if> 
			
			    <!-- Next Button -->
			    <li class="page-item ${pager.endCheck ? 'disabled' : ''}">
			      <a class="page-link pages arrow text-white" 
			         href="?page=${pager.page + 1}" 
			         aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			
			  </ul>
			</nav> 

</main>
			

	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script>
$(document).ready(function () {
    $(".like-button").click(function () {
        const button = $(this);
        const userNum = button.data("usernum");
        const contentId = button.data("contentid");
        const kind = button.data("kind");
        
        console.log("🧪 리뷰 좋아요 클릭됨");
	      console.log("contentId:", contentId);
	      console.log("userNum:", userNum);
	      console.log("kind:", kind); 

        // 로그인 안했을 경우 처리
        if (!userNum) {
            alert("로그인이 필요합니다.");
            location.href = "/reviewNest/login";
            return;
        }

        $.ajax({
            type: "POST",
            url: "/reviewNest/toggleLike",
            contentType: "application/json",
            data: JSON.stringify({
                userNum: userNum,
                contentId: contentId,
                kind: kind
            }),
            success: function (res) {
                const icon = button.find(".heart-icon");
                if (res.liked) {
                    icon.text("❤️");
                } else {
                    icon.text("🤍");
                }
            },
            error: function () {
                alert("좋아요 처리 중 오류가 발생했습니다.");
            }
        });
    });
});
</script>
</body>
</html>