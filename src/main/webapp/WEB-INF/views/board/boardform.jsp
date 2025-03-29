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
					<c:if test="${not empty reply.qnaNum}">
						<input type="hidden" name="qnaNum" value="${reply.qnaNum}" id="qnaNum">
					</c:if>
			    
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
			    
			    
			   <div class="col-md-9">
			     <label for="boardTitle" class="form-label">제목</label>
			      <input type="text" name="boardTitle" value="${dto.boardTitle}"  class="form-control is-invalid" id="boardTitle" aria-describedby="boardTitleFeedback">
			      <div id="boardTitleFeedback" class="invalid-feedback">
			       	Please choose a detail.
			      </div>
			   </div>

			  <div class="col-md-9">
			    <label for="boardContent" class="form-label">내용</label>
			      <textarea name="boardContents" class="form-control is-invalid" id="boardContents" rows="15" aria-describedby="boardContentsFeedback">${dto.boardContents}</textarea>
			      <div id="boardContentsFeedback" class="invalid-feedback">
			        Please choose a detail.
			      </div>
			  </div>
			  
			  <div class="mb-3">
				  <c:if test="${kind eq 'faq'}">
				  	<c:forEach items="${dto.faqFilesDTOs}" var="f">
				  		<div class="alert alert-secondary" role="alert">
				  		${f.oldName} <span data-file-num="${f.fileNum}" data-kind="${kind}" class="badge text-bg-secondary files-delete">x</span>
						</div>
				  	</c:forEach>				  
					<div class="files-container">
						<div id ="files" data-files-size="${dto.faqFilesDTOs.size()}">					
							<button type="button" id="btn1">파일추가</button>	
						</div>
						<div class="btn-container">
							<button class="btn btn-primary" type="submit">등록</button>
							<a href="./list" class ="btn btn-danger">취소</a>	
						</div>
					</div>
				  </c:if>
				  <c:if test="${kind eq 'notice'}">
				  	<c:forEach items="${dto.noticeFilesDTOs}" var="f">
				  		<div class="alert alert-secondary" role="alert">
				  		${f.oldName} <span data-file-num="${f.fileNum}" data-kind="${kind}" class="badge text-bg-secondary files-delete">x</span>
						</div>	
				  	</c:forEach>
					<div class="files-container">
						<div id ="files" data-files-size="${dto.noticeFilesDTOs.size()}">					
							<button type="button" id="btn1">파일추가</button>	
						</div>
						<div class="btn-container">
							<button class="btn btn-primary" type="submit">등록</button>
							<a href="./list" class ="btn btn-danger">취소</a>	
						</div>
					</div>				  				  
				  </c:if>
				  <c:if test="${kind eq 'qna'}">
				  	<c:forEach items="${dto.qnaFilesDTOs}" var="f">
				  		<div class="alert alert-secondary" role="alert">
				  		${f.oldName} <span data-file-num="${f.fileNum}" data-kind="${kind}" class="badge text-bg-secondary files-delete">x</span>
						</div>
				  	</c:forEach>
					<div class="files-container">
						<div id ="files" data-files-size="${dto.qnaFilesDTOs.size()}">					
							<button type="button" id="btn1">파일추가</button>	
						</div>
						<div class="btn-container">
							<button class="btn btn-primary" type="submit">등록</button>
							<a href="./list" class ="btn btn-danger">취소</a>	
						</div>
					</div>				  	
				  </c:if>
			  </div>

			   <input type="hidden" name="userNum" value="${user.userNum}" class="form-control" id="userNum" required>
			  
			</form>
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
	<script type="module" src="/resources/js/fileManager.js"></script>
	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>