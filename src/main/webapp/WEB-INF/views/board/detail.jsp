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
				  <div id="boardContents" class="form-control" style="width: 100%; font-size: 1.1em; min-height: 300px; overflow-y: auto; white-space: pre-line;">
				  	<c:choose>
				  		<c:when test="${kind eq 'faq'}">
				  			<c:forEach items="${dto.faqFilesDTOs}" var="file">
					      		<img src="/resources/images/faq/${file.fileName}" 
					           class="card-img-top rounded" width="200px" height="200px" alt="">
				    		</c:forEach>
				  		</c:when>
				  		<c:when test="${kind eq 'qna'}">
				  			<c:forEach items="${dto.qnaFilesDTOs}" var="file">
						      <img src="/resources/images/qna/${file.fileName}" 
						           class="card-img-top rounded" width="200px" height="200px" alt="">
				    		</c:forEach>
				  		</c:when>
				  		<c:when test="${kind eq 'notice'}">
				  			<c:forEach items="${dto.noticeFilesDTOs}" var="file">
					      		<img src="/resources/images/notice/${file.fileName}" 
					           class="card-img-top rounded" width="200px" height="200px" alt="">
				   			 </c:forEach>
				  		</c:when>	
				  	</c:choose>
				    ${dto.boardContents}
				  </div>
				</div>				
			  </div>
			  
				<div class="btn-detail-container">
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
				
				<div class="file-download-container">
				    첨부 파일
				    <ul class="file-list">
				        <c:choose>
				            <c:when test="${kind eq 'faq'}">
				                <c:forEach items="${dto.faqFilesDTOs}" var="f">
				                    <li>
				                        <a href="./fileDown?fileNum=${f.fileNum}">
				                            <i class="fas fa-download"></i> ${f.oldName}
				                        </a>
				                    </li>
				                </c:forEach>
				            </c:when>
				            <c:when test="${kind eq 'notice'}">
				                <c:forEach items="${dto.noticeFilesDTOs}" var="f">
				                    <li>
				                        <a href="./fileDown?fileNum=${f.fileNum}">
				                            <i class="fas fa-download"></i> ${f.oldName}
				                        </a>
				                    </li>
				                </c:forEach>
				            </c:when>
				            <c:when test="${kind eq 'qna'}">
				                <c:forEach items="${dto.qnaFilesDTOs}" var="f">
				                    <li>
				                        <a href="./fileDown?fileNum=${f.fileNum}">
				                            <i class="fas fa-download"></i> ${f.oldName}
				                        </a>
				                    </li>
				                </c:forEach>
				            </c:when>
				            <c:otherwise>
				                <li>첨부된 파일이 없습니다.</li>
				            </c:otherwise>
				        </c:choose>
				    </ul>
				</div>
				
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>