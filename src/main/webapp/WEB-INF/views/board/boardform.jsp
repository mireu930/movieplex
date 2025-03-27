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
		<div class = "row col-md-8 offset-md-2">
			<form class="row g-3" action = "" method = "post" enctype="multipart/form-data">

			    
			    	<c:if test="${kind eq 'notice'}">
			            <c:if test="${not empty dto.noticeNum}">
        					<input type="hidden" name="noticeNum" value="${dto.noticeNum}" id="noticeNum">
    					</c:if>			    	
			    	</c:if>
			    	<c:if test="${kind eq 'faq'}">
    					<c:if test="${not empty dto.faqNum}">
        					<input type="hidden" name="faqNum" value="${dto.faqNum}" id="faqNum">
    					</c:if>			    	
			    	</c:if>
    				<c:if test="${kind eq 'qna'}">
    					<c:if test="${not empty dto.qnaNum}">
        					<input type="hidden" name="qnaNum" value="${dto.qnaNum}" id="qnaNum">
   						 </c:if>			    	
			    	</c:if>
			    
			    
			   <div class="col-md-12">
			     <label for="boardTitle" class="form-label">제목</label>
			      <input type="text" name="boardTitle" value="${dto.boardTitle}"  class="form-control is-invalid" id="boardTitle" aria-describedby="boardTitleFeedback">
			      <div id="boardTitleFeedback" class="invalid-feedback">
			       	Please choose a detail.
			      </div>
			   </div>

			  <div class="col-md-12">
			    <label for="boardContent" class="form-label">내용</label>
			      <textarea name="boardContents" class="form-control is-invalid" id="boardContents" rows="5" aria-describedby="boardContentsFeedback">${dto.boardContents}</textarea>
			      <div id="boardContentsFeedback" class="invalid-feedback">
			        Please choose a detail.
			      </div>
			  </div>

			   <input type="hidden" name="userNum" value="${user.userNum}" class="form-control" id="userNum" required>
			  
			  <div class="btn-container d-flex justify-content-end gap-2">
			    <button class="btn btn-primary" type="submit">등록</button>
			    <a href="./list" class ="btn btn-danger">취소</a>	
			  </div>
			  
			</form>
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>