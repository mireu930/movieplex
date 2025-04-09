<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
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
<link rel="stylesheet" href="/resources/css/board.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	
	<main>
	

		<div class="content-wrapper">
		<c:if test="${kind eq 'notice'}">
			<h2>공지사항</h2>
		</c:if>
		<c:if test="${kind eq 'qna'}">
			<h2>QNA</h2>
		</c:if>
		<c:if test="${kind eq 'faq'}">
			<h2>FAQ</h2>
		</c:if>
		
		<form id="list_form" action="./list" method="get">
	    	<input type="hidden" name="page" id="pageNum">
	    	<div class="row mb-3">
	    	 <div class="col-md-3">
			    <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
			    <select class="form-select" name="kind" id="inlineFormSelectPref">
			      <option value="k1">제목</option>
			      <option value="k2">작성자</option>
			      <option value="k3">제목+작성자</option>
			    </select>
			  </div>
			  <div class="col-md-6">
			    <label class="visually-hidden" for="inlineFormInputGroupUsername"></label>
			      <input type="text" name="search" value="${pager.search}" class="form-control" id="inlineFormInputGroupUsername" placeholder="검색어를 입력하세요">
			  </div>
			
			  <div class="col-md-3">
			    <button type="submit" class="btn btn-primary">검색</button>
			  </div>
	    	
	    	</div>
		</form>
		
		<table class="table table-striped table-hover">
 		<thead>
	    <tr>
	      <th scope="col">번호</th>
	      <th scope="col">제목</th>
	      <th scope="col">작성자</th>
	      <th scope="col">등록날짜</th>
	      <th scope="col">조회수</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${list}" var ="ar">
	  
	    <tr>
	    <c:if test="${kind eq 'notice'}">
	      <th scope="row"><a href="./detail?noticeNum=${ar.noticeNum}">${ar.noticeNum}</a></th>	    
	    </c:if>
	    <c:if test="${kind eq 'faq'}">
	    	<th scope="row"><a href="./detail?faqNum=${ar.faqNum}">${ar.faqNum}</a></th>	
	    </c:if>
	    <c:if test="${kind eq 'qna'}">
	    	<th scope="row"><a href="./detail?qnaNum=${ar.qnaNum}">${ar.qnaNum}</a></th>	
	    </c:if>
	      <td>
	      <c:catch>
	      <c:forEach begin ="1" end="${ar.boardDepth}" varStatus="ar2">
	      
	      <c:if test="${ar2.last}">
	      	<ion-icon name="return-down-forward-outline"></ion-icon>
	      </c:if>
	      </c:forEach> 
	      </c:catch>
	      ${ar.boardTitle}
	      </td>
	      <td>${ar.userDTO.userName}</td>
	      <td>${ar.boardDate}</td>
	      <td>${ar.boardHit}</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
	</div>
	<div class="pagination-wrapper">
		<nav aria-label="Page navigation example">
  		<ul class="pagination">
  			
		    <li class="page-item"><button class="page-link pages" data-page-num="${pager.start-1}">Previous</button></li>
  			<c:forEach begin="${pager.start}" end="${pager.end}" var="i">
		    	<li class="page-item"><button class="page-link pages" data-page-num="${i}">${i}</button></li>
  			</c:forEach>
		   <li class="page-item ${pager.endCheck?'disabled':''}"><button class="page-link pages" data-page-num="${pager.end+1}">Next</button></li>
	  	</ul>
	</nav>	
		<a href ="./add" class ="btn btn-primary add_button">추가</a>
	</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<script src="/resources/js/boardList.js"></script>
	<script type="module" src="https://cdn.jsdelivr.net/npm/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://cdn.jsdelivr.net/npm/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>