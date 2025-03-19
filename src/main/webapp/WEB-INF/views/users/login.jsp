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

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
	<div class = "container-fluid my-5">
		<div class = "row col-md-8 offset-md-2">
			<form class="row g-3" action = "./login" method = "post">
			  <div class="col-md-4">
			    <label for="userId" class="form-label">아이디</label>
			    <div class="input-group has-validation">
			      <input type="text" name="userId"  class="form-control is-invalid" id="validationServer02" aria-describedby="inputGroupPrepend3 validationServerServer02Feedback" required>
			      <div id="validationServerServer02Feedback" class="invalid-feedback">
			        아이디를 입력하세요.
			      </div>
			    </div>
			  </div>
			  <div class="col-md-4">
			    <label for="userPw" class="form-label">비밀번호</label>
			    <div class="input-group has-validation">
			      <input type="password" name="userPw" class="form-control is-invalid" id="validationServerUsername" aria-describedby="inputGroupPrepend3 validationServerUsernameFeedback" required>
			      <div id="validationServerUsernameFeedback" class="invalid-feedback">
			        비밀번호를 입력하세요.
			      </div>
			    </div>
			  </div>
			  <div class="col-12">
			    <button class="btn btn-primary" type="submit">로그인</button>
			    <a href="/users/join">회원가입하기</a>
			  </div>
			  <%-- <div>
				<a id="kakao-login-btn" href="${urlKakao}"> <img
					src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
					width="150px" alt="카카오 로그인 버튼" />
				</a>
			</div> --%>
			</form>
		</div>
	</div>
	</main>
<!-- footer -->
<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>