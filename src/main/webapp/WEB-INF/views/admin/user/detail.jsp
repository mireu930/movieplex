<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<label for="movieTitle" class="form-label" style="font-size: 1.2em; font-weight: bold;">회원정보</label>

		<div style="padding: 20px; background-color: #f9f9f9; border-radius: 8px; width: 600px; margin: 20px auto;">
                <p><strong>아이디:</strong> ${userDetail.userId}</p>
                <p><strong>이름:</strong>${userDetail.userName}</p>
                <p><strong>이메일:</strong>${userDetail.userEmail}</p>
                <p><strong>폰번호:</strong>${userDetail.userPhone}</p>
                <p><strong>등급:</strong> ${userDetail.userGrade}</p>
                <p><strong>가입일:</strong> ${userDetail.registDate}</p>
                <p><strong>로그인형태:</strong> ${userDetail.sns == 0?"일반":"SNS"}</p>
                <p><strong>상태:</strong>
                  ${userDetail.userOut == 1 ? "<span style='color: red;'>탈퇴</span>" : "<span style='color: green;'>활동중</span>"}
                </p>
            </div>