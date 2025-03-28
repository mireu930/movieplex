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
			  <div class="table-container" style="width: 80%;">
			    <table class="table table-striped table-hover" style="width: 100%; font-size: 1.2em;">
			      <thead>
			        <tr>
			          <th scope="col">제목</th>
			          <th scope="col">작성자</th>
			          <th scope="col">조회수</th>
			        </tr>
			        <tr>
			          <td>${dto.boardTitle}</td>
			          <td>${dto.userDTO.userName}</td>
			          <td>${dto.boardHit}</td>
			        </tr>
			      </thead>
			    </table>
			    <div class="form-group" style="width: 100%;">
			      <label for="boardContents">내용</label>
			      <textarea id="boardContents" class="form-control" rows="20" style="width: 100%; font-size: 1.1em;" readonly>${dto.boardContents}</textarea>
			    </div>
			  </div>
			</div>
		<div class="btn-container">
			<c:if test="${kind eq 'faq'}">
				<a href="./update?faqNum=${dto.faqNum}" class ="btn btn-success">수정</a>	
				<a href="./delete?faqNum=${dto.faqNum}" class ="btn btn-danger">삭제</a>
			</c:if>	
			<c:if test="${kind eq 'notice'}">
				<a href="./update?noticeNum=${dto.noticeNum}" class ="btn btn-success">수정</a>	
				<a href="./delete?noticeNum=${dto.noticeNum}" class ="btn btn-danger">삭제</a>
			</c:if>	
			<c:if test="${kind eq 'qna'}">
				<a href="./update?qnaNum=${dto.qnaNum}" class ="btn btn-success">수정</a>	
				<a href="./delete?qnaNum=${dto.qnaNum}" class ="btn btn-danger">삭제</a>
				<a href="./reply?qnaNum=${dto.qnaNum}" class ="btn btn-primary">댓글쓰기</a>
			</c:if>		
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>