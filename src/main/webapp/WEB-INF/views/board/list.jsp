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
<title>Album example · Bootstrap v5.3</title>

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
	      -
	      <c:if test="${ar2.last}">
	      Re
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
	<div class="btn-container">
		<a href ="./add" class ="btn btn-primary">추가</a>
	</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>