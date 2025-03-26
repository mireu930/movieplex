<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid py-4">
	<!-- 🎬 제목 + 날짜 + 관 + 조회버튼 -->
	<div class="row justify-content-center mb-4">
	  <div class="col-12 col-lg-10">
		<div class="d-flex flex-wrap align-items-end gap-3">
		  
		  <!-- 날짜 -->
		  <div>
			<label for="theaterDate" class="form-label fw-bold">날짜</label>
			<input type="date" id="theaterDate" class="form-control">
		  </div>
  
		  <!-- 라디오: 관 선택 -->
		  <div class="d-flex gap-2 align-items-center" id="theaterName">
			<div class="form-check">
			  <input type="radio" id="1" name="theater" class="form-check-input">
			  <label for="1" class="form-check-label">1관</label>
			</div>
			<div class="form-check">
			  <input type="radio" id="2" name="theater" class="form-check-input">
			  <label for="2" class="form-check-label">2관</label>
			</div>
			<div class="form-check">
			  <input type="radio" id="3" name="theater" class="form-check-input">
			  <label for="3" class="form-check-label">3관</label>
			</div>
		  </div>
  
		  <!-- 조회 버튼 -->
		  <button class="btn btn-primary" id="fetchScheduleBtn">조회</button>
  
		</div>
	  </div>
	</div>
  
	<!-- 본문 -->
	<div class="row justify-content-center">
	  <div class="col-11 col-lg-10 layout-wrapper">
		
		<!-- 📃 상영 목록 -->
		<div class="center-box flex-grow-1">
		  <h5 class="fw-bold mb-3">상영 목록!</h5>
		  <div id="scheduleList" class="list-group">
			<!-- 여기에 비동기로 상영 시간 목록 추가됨 -->
		  </div>
		</div>
  
		<!-- 🕒 상영 추가 폼 -->
		<div class="right-wrapper" style="max-width: 400px;">
		  <label for="movieTitle" class="form-label">🎬 영화 제목</label>
		  <select id="selectOption" class="form-select" aria-label="Default select example">
			<option selected disabled value="default">상영 추가할 영화 선택</option>
		  	<c:forEach items="${movieList}" var="m">
		  		<option value="${m.movieId }" >${m.movieTitle }</option>
		  	</c:forEach>
		  </select>
		  <p class="fw-bold">러닝타임: <span id="runningTimeDisplay">-</span>분</p>

		  <!-- <select class="form-select" aria-label="Default select example" id="theaterName">
			<option selected disabled value="default">상영관 선택</option>
			<option value="1">1관</option>
			<option value="2">2관</option>
			<option value="3">3관</option>
		  </select> -->
  
		  <label for="startTime" class="form-label">시작 시간</label>
		  <input type="time" id="startTime" class="form-control mb-2">
  
		  <label for="endTime" class="form-label">종료 시간</label>
		  <input type="time" id="endTime" class="form-control mb-2" disabled>
  
  
		  <button class="btn btn-success w-100 mb-2" id="add_btn">추가</button>
		  <button class="btn btn-secondary w-100" id="reset_btn">초기화</button>
		</div>
  
	  </div>
	</div>
  </div>
  