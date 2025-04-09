<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
	<script type="module" src="https://cdn.jsdelivr.net/npm/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://cdn.jsdelivr.net/npm/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>

	<div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary min-vh-100 "
		style="width: 300px; border-right: 2px solid gray;">
		<a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
			<img src="/resources/image/admin_page.png" style="width: 30px; height: auto; margin-right: 10px;"> <span
				class="fs-4">설정</span>
		</a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item">
				<a href="javascript:void(0);" data-kind="user" class="nav-link" onclick="setActive(this)">
					<span style="display: inline-flex; align-items: center;">
						<ion-icon name="people-outline" size="large"></ion-icon>
						<span style="margin-left: 4px;">회원</span>
					</span>
				</a>
			</li>
			<!-- <li class="nav-item">
				<a href="javascript:void(0);" data-kind="movies" class="nav-link" onclick="setActive(this)">
					<span style="display: inline-flex; align-items: center;">
						<ion-icon name="videocam-outline" size="large"></ion-icon>
						<span style="margin-left: 4px;">영화 목록</span>
					</span>
				</a>
			</li> -->

			<li class="nav-item">
				<a href="javascript:void(0);" data-kind="theater" class="nav-link" onclick="setActive(this)">
					<span style="display: inline-flex; align-items: center;">
						<ion-icon name="calendar-outline" size="large"></ion-icon>
						<span style="margin-left: 4px;">상영 추가</span>
					</span>
				</a>
			</li>

		

		</ul>
		<hr>
	</div>