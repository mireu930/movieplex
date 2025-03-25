<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 📄 layout.jsp or theaterAdd.jsp 등 -->
<div class="container-fluid py-4">

	<!-- 제목 영역 -->
	<div class="row justify-content-center mb-4">
		<div class="col-12 col-md-10 col-lg-8">
			<label for="movieTitle" class="form-label fw-bold fs-5">영화 제목</label>
			<input type="text" id="movieTitle" value="${movieDTO.movieTitle}"
				class="form-control form-control-lg movie-title" readonly>
		</div>
	</div>

	<!-- 본문 레이아웃 -->
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 layout-wrapper">

			<!-- 주황 (1번) -->
			<div class="left-box" id="theaterRadioArea">
				<div class="form-check">
					<input class="form-check-input" type="radio"
						name="flexRadioDefault" id="theaterName1"> <label
						class="form-check-label" for="flexRadioDefault1"> 1관 </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio"
						name="flexRadioDefault" id="theaterName2"> <label
						class="form-check-label" for="flexRadioDefault2"> 2관 </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio"
						name="flexRadioDefault" id="theaterName3"> <label
						class="form-check-label" for="flexRadioDefault2"> 3관 </label>
				</div>
			</div>

			<!-- 중앙 (빨강) -->
			<div class="center-box" id="scheduleListArea">
				<!-- 예: 상영 시간 리스트 -->

				<div
					class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary"
					style="width: 800px;">
					<a href="#"
						class="d-flex align-items-center flex-shrink-0 p-3 link-body-emphasis text-decoration-none border-bottom">
						<span class="fs-5 fw-semibold">상영 목록</span>
					</a>

					<div class="list-group list-group-flush border-bottom scrollarea">
						<c:forEach items="${list }" var="l">
							<div
								class="list-group-item movie-list-group list-group-item-action py-3 lh-sm"
								data-movie-id="${l.movieId }">
								<strong class="mb-1">${l.movieTitle }</strong>
							</div>
						</c:forEach>
					</div>

				</div>
			</div>

			<!-- 오른쪽 (초록/노랑/보라) -->
			<div class="right-wrapper">
				<div class="right-box middle" id="middleRightArea">
					<label for="startTime" class="form-label">시작 시간</label> <input
						type="time" id="startTime" class="form-control mb-3"> <label
						for="endTime" class="form-label">종료 시간</label> <input type="time"
						id="endTime" class="form-control mb-3">

					<p class="mt-2 fw-bold">
						러닝타임: <span id="runningTimeDisplay">-</span>분
					</p>
				</div>

				<div class="right-box bottom" id="bottomRightArea">
					<div class="d-grid gap-2">
						<button class="btn btn-success" type="button">Button</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>