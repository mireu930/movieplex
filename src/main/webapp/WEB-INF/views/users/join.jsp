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
<!-- <link rel="stylesheet" href="/resources/css/login.css"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link href="https://fonts.cdnfonts.com/css/poppins" rel="stylesheet">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
		<div class="cover_box">
			<div class = "form-box register">
				<h2>register</h2>
				<form action = "./join" method = "post" enctype="multipart/form-data">
					  <div class="input-box">
						
						<div class="input-group has-validation">
						  <input type="text" name="userId"  class="form-control is-invalid" id="userId" placeholder="ID" aria-describedby="userIdFeedback" required>
						  <div id="userIdFeedback" class="invalid-feedback">
						   아이디는 영문,숫자 6자 이상 12자이하이여야합니다다
						  </div>
						</div>
					</div>
					<div>
						<button id="idbtn">아이디확인</button>
					</div>
		
					  <div class="input-box">
						<!-- <label for="userPw" class="form-label">비밀번호</label> -->
						<div class="input-group has-validation">
						  <input type="password" name="userPw" class="form-control is-invalid" id="userPw" aria-describedby="userPwFeedback" required>
						  <div id="userPwFeedback" class="invalid-feedback">
							영문,숫자 8자 이상 12자이하이여야합니다다
						  </div>
						</div>
					  </div>
					
					<div class="input-box">
					  <!-- <label for="userPwCheck" class="form-label">비밀번호 확인</label> -->
					  <div class="input-group has-validation">
						<input type="password" name="userPw" class="form-control is-invalid" id="userPwCheck" aria-describedby="userPwCheckFeedback" required>
						<div id="userPwCheckFeedback" class="invalid-feedback">
						  비밀번호가 일치하지 않습니다.
						</div>
					  </div>
					</div>
		
					  <div class="input-box">
						<!-- <label for="userEmail" class="form-label">이메일</label> -->
						<input type="text" name = "userEmail" class="form-control is-invalid" id="userEmail" value="${email}" aria-describedby="userEmailFeedback" required>
						<input type="button" class="btn-primary" id="mailCheckBtn" value="이메일인증번호받기">
						<div id="userEmailFeedback" class="invalid-feedback">
							이메일을 입력하세요.
						</div>
						<input type="text" id="mailInput" class="form-control is-invalid" placeholder="인증번호 6자리를 입력해주세요" aria-describedby="mailInputCheck" required>
						<div id="mailInputCheck"></div>
					  </div>
		
					  <div class="input-box">
						<!-- <label for="userPhone" class="form-label">휴대폰번호</label> -->
						<input type="text" name ="userPhone" class="form-control is-invalid" id="userPhone" aria-describedby="userPhoneFeedback" required>
						<div id="userPhoneFeedback" class="invalid-feedback">
						  휴대대폰번호를 입력하세요.(-포함x)
						</div>
					  </div>
					  <div class="input-box">
						<label for="userName" class="form-label">이름</label>
						<input type="text" name ="userName" class="form-control is-invalid" id="userName" value="${name}" aria-describedby="userNameFeedback" required>
						<div id="userNameFeedback" class="invalid-feedback">
						  이름을 입력하세요.
						</div>
					  </div>
					  
					  
						<div class="form-check">
						  <input class="form-check-input is-invalid" type="checkbox" value="" id="agree" aria-describedby="agreeFeedback" required>
						  <label class="form-check-label" for="checkbox">
							회원가입에 동의합니다.
						  </label>
						  <div id="checkboxFeedback" class="invalid-feedback">
							가입하기전에 동의를 눌러주세요.
						  </div>
						</div>
					 
						<button class="btn btn-primary" type="submit">회원가입</button>
					  
				 </form>
			</div>


		</div>
	</main>
<!-- footer -->
<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
<script src="/resources/js/join.js"></script>
</body>
</html>