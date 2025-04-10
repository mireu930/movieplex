<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script src="/docs/5.3/assets/js/color-modes.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>ReviewNest</title>

<link href="/resources/image/reviewnest_title.png" rel="shortcut icon" type="image/x-icon">

<link rel="stylesheet" href="/resources/css/reviewNestMain.css">
<link rel="stylesheet" href="/resources/css/login.css">
</head>
<body>
	<!-- header -->
	<div class="container">
	<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_header.jsp"></c:import>
	</div>
	<main>
		<div class="cover_box">
			<div class = "form-box login">
				<h2>로그인</h2>
					<form action = "/reviewNest/login" method = "post">
					  <div class="input-box">
						<div class="input-group has-validation">
						 <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
						  <input type="text" name="userId" id="userId" placeholder="아이디" required>
						</div>
					  </div>
					  <div class="input-box">
						<div class="input-group has-validation">
							<span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
						  <input type="password" name="userPw" id="userPw" placeholder="비밀번호" required>
						</div>
					  </div>
					  <div class ="remember-forget">
						<!-- <label><input type ="checkbox">remember me</label> -->
						<input type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" value="forget password?"
						style="background-color: transparent; border-color: transparent;">
						</div>
					 
						
						<button class="btn btn-primary" type="submit" style="margin-bottom: 5px;">통합로그인</button>
		
						<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApi}&redirect_uri=${redirectUrl2}" class="btn btn-warning">
							 카카오로그인
       						</a>
				</form>
			</div>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
			  <div class="modal-content">
				<div class="modal-header">
				  <h1 class="modal-title fs-5" id="exampleModalLabel">이메일을 입력해주세요</h1>
				  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
				  <div class="mb-3">
					<label for="fogetUserEmail" class="col-form-label"></label>
					<textarea data-board-num="" class="form-control is-invalid" id="fogetUserEmail" placeholder="xxx@xxx.xxx"></textarea>
				  </div>
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				  <button type="button" class="btn btn-primary" id="emailTransport" data-bs-dismiss="modal">전송</button>
				</div>
			  </div>
			</div>
		  </div>
	
	</main>
	<script src="/resources/js/login.js"></script>
	<script src="/resources/js/join.js"></script>
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
	
<!-- footer -->
<c:import url="/WEB-INF/views/reviewNest/templates/reviewNest_footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>