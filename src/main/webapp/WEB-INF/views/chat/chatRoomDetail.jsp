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
<link rel="stylesheet" href="/resources/css/chat.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>

</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
		<ul class="display-chatting">
			<c:forEach items="${list}" var="msg">
				<c:if test="${msg.userNum eq user.userNum}">
					<li class="myChat">
						<p class="chat">${msg.message}</p>
						<span class="chatDate">${msg.createDate}</span>
					</li>
				</c:if>
				<c:if test="${msg.userNum ne user.userNum}">
					<li class="otherChat">
						<b>${msg.userId}</b>
						<p class="chat">${msg.message}</p>
						<span class="chatDate">${msg.createDate}</span>
					</li>
				</c:if>

			</c:forEach>
		</ul>
		
		<div class="input-area">
			<textarea rows="3" id="inputChatting"></textarea>
			<button id="send">전송</button>
		</div>
	</main>
	<!-- footer -->
	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
	
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script>
		const userNum = '${user.userNum}';
		const userId = '${user.userId}';
		const chatRoomNo = '${chatRoomNo}';

		let chattingSocket = new SockJS("/chatServer?chatRoomNo=" + chatRoomNo);
	</script>
	<script src="/resources/js/chat.js"></script>
</body>
</html>