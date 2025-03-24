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
<link rel="stylesheet" href="/resources/css/calendar.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main>
		<div class="d-flex">
			<div class="adminSidebar">
				<c:import url="/WEB-INF/views/templates/adminSideBar.jsp"></c:import>
			</div>

			<div
				class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary"
				style="width: 380px;">
				<a href="/"
					class="d-flex align-items-center flex-shrink-0 p-3 link-body-emphasis text-decoration-none border-bottom">
					<span class="fs-5 fw-semibold">영화 목록</span>
				</a>
				<div class="list-group list-group-flush border-bottom scrollarea">
					<div class="list-group list-group-flush border-bottom scrollarea">
						<div
							class="list-group-item list-group-item-action active py-3 lh-sm"
							data-movie-id="1">
							<div
								class="d-flex w-100 align-items-center justify-content-between">
								<strong class="mb-1">List group item heading</strong> <small>Wed</small>
							</div>
							<div class="col-10 mb-1 small">Some placeholder content in
								a paragraph below the heading and date.</div>
						</div>

						<div class="list-group-item list-group-item-action py-3 lh-sm"
							data-movie-id="2">
							<div
								class="d-flex w-100 align-items-center justify-content-between">
								<strong class="mb-1">List group item heading</strong> <small
									class="text-body-secondary">Tues</small>
							</div>
							<div class="col-10 mb-1 small">Some placeholder content in
								a paragraph below the heading and date.</div>
						</div>

						<!-- 동일한 방식으로 계속 반복 -->
					</div>
				</div>
			</div>

			<div class="calendar-wrapper"
				style="height: 100vh; overflow-y: auto;">
				<div class="calendar-header">
					<button id="prevMonth">&#8593;</button>
					<span id="monthYear"></span>
					<button id="nextMonth">&#8595;</button>
				</div>

				<div class="calendar-weekdays">
					<span>Sun</span><span>Mon</span><span>Tue</span><span>Wed</span> <span>Thu</span><span>Fri</span><span>Sat</span>
				</div>

				<div class="calendar-days" id="calendarDays"></div>
			</div>

			<div class="theater" style="flex-grow: 1; overflow-y: auto;">
				<div class="d-grid gap-2 mb-3">
					<button class="btn btn-primary" type="button">Button</button>
					<button class="btn btn-primary" type="button">Button</button>
				</div>
				<div>
					<div
				class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary border-top"
				>
				<a href="/"
					class="d-flex align-items-center flex-shrink-0 p-3 link-body-emphasis text-decoration-none border-bottom">
					<span class="fs-5 fw-semibold">상영 시간</span>
				</a>
				<div class="list-group list-group-flush border-bottom scrollarea">
					<div class="list-group list-group-flush border-bottom scrollarea">
						<div
							class="list-group-item list-group-item-action active py-3 lh-sm"
							data-movie-id="1">
							<div
								class="d-flex w-100 align-items-center justify-content-between">
								<strong class="mb-1">List group item heading</strong> <small>Wed</small>
							</div>
							<div class="col-10 mb-1 small">Some placeholder content in
								a paragraph below the heading and date.</div>
						</div>

						<div class="list-group-item list-group-item-action py-3 lh-sm"
							data-movie-id="2">
							<div
								class="d-flex w-100 align-items-center justify-content-between">
								<strong class="mb-1">List group item heading</strong> <small
									class="text-body-secondary">Tues</small>
							</div>
							<div class="col-10 mb-1 small">Some placeholder content in
								a paragraph below the heading and date.</div>
						</div>

						<!-- 동일한 방식으로 계속 반복 -->
					</div>
				</div>
			</div>
				</div>
			</div>


		</div>
	</main>

	<script src="/resources/js/calendar.js"></script>
	<script src="/resources/js/adminPage.js"></script>
	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>