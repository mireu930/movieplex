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
<title>chat</title>

<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet" href="/resources/css/chat.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
		<div class="content">
			<br><br>
			<div class="innerOuter" style="padding: 5% 10%; width: 100%;">
				<h2>채팅방목록</h2>
				<c:if test="${user.userGrade ne 4}">
					<div>※관리자와 채팅합니다.</div>
				</c:if>
				<br>
				<br>
				<table class="table table-hover" align="center">
					<thead>
						<tr>
							<th>방번호</th>
							<th>채팅방 제목(주제)</th>
							<th>개설자</th>
							<th>참여인수</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="4">존재하는 채팅방이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
								<c:forEach items="${list}" var="chatRoom">
									<tr>
										<td>${chatRoom.chatRoomNo}</td>
										<td>
											${chatRoom.title}
											
												<button class="btn btn-primary"
												onclick="location.href=`/addChatRoomDetail?chatRoomNo=${chatRoom.chatRoomNo}`">참여</button>
											
										</td>
										<td>${chatRoom.userId}</td>
										<td>${chatRoom.cnt}</td>
									</tr>
								</c:forEach>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
				
				<!-- 로그인이 되어있는 경우 보이는 버튼 -->
				
					<div class='btn-area'>
						<button data-bs-toggle="modal" data-bs-target="#chatModal"
						class="btn btn-danger">채팅방 만들기</button>
					</div>
				
			</div>
		</div>
		
		
		<div class="modal fade" id="chatModal">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<!-- 모달 해더 -->
					<div class="modal-header">
						<h4 class="modal-title">채팅방 만들기</h4>
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal">×</button>
					</div>
					 <form action="/addChatRoom" method="post">
						<!--  모달 바디 -->
						<div class="modal-body">
							<label for="title" class="mr-sm-2">제목</label> <input type="text"
								class="form-controll mb-2 mr-sm-2" placeholder="채팅방 제목"
								id="chatRoomTitle" name="title">
						</div>
						<!-- 모달 푸터 -->
						<div class="modal-footer">
							<button type="submit" id="open-form" class="btn btn-primary">만들기</button>
							<button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
						</div>
					 </form>
				</div>
			</div>
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>