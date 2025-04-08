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
				<strong class="hot_rank d-inline-block mb-2 text-success-emphasis">리뷰네스트 시리즈 인기 순위</strong>
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-5 g-3">
					<c:forEach items="${tvList }" var="content" varStatus="status">
						<div class="col">
							<div class="card shadow-sm">
								<a href="/reviewNest/getTvDetail?contentId=${content.contentId}">
									<img src="https://image.tmdb.org/t/p/w500/${content.shortPoster }"  id="poster">
							 	</a>
								<div class="card-body">
									<p class="card-text">${content.contentTitle}</p>
									<div class="d-flex justify-content-end">
										<button class="like-button"
									        data-usernum="${userNum}"
									        data-contentid="${content.contentId}"
									        data-kind="1">
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
			</div>
			
			<nav aria-label="Page navigation example" class="mb-5">
			  <ul class="pagination d-flex justify-content-center mt-5">
			    <!-- Previous Button -->
			    <li class="page-item ${pager.page <= 1 ? 'disabled' : ''}">
			      <a class="page-link pages arrow text-white" 
			         href="?page=${pager.page - 1}" 
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

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script>
$(document).ready(function () {
    $(".like-button").click(function () {
        const button = $(this);
        const userNum = button.data("usernum");
        const contentId = button.data("contentid");
        const kind = button.data("kind");

        // 로그인 안했을 경우 처리
        if (!userNum) {
            alert("로그인이 필요합니다.");
            location.href = "/users/login";
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