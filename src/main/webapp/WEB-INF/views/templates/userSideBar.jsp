<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>

	<div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary min-vh-100"
		style="width: 350px; border-right: 2px solid gray;">
		<a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
			<ion-icon name="settings-outline"></ion-icon> 
			<span class="fs-5">설정</span>
		</a>
		<hr>
		<!-- 사이드 바에서 선택시 js로 active 속성 변경 해줘야 합니다! 그래야 파란색 바가 해당 페이지로 이동합니다! -->
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item">
				<a href="javascript:void(0);" data-section="user" class="nav-link" onclick="setActive(this); loadUserInfo();">
					<ion-icon name="person-outline" class="me-2"></ion-icon>
					내정보
				</a>
			</li>
			<li class="nav-item">
				<a href="javascript:void(0);" data-section="payment" class="nav-link" onclick="setActive(this)">
					<ion-icon name="card-outline" class="me-2"></ion-icon>
					결제내역
				</a>
			</li>
		
				<li class="nav-item">
					<a href="javascript:void(0);" data-section="point" class="nav-link" onclick="setActive(this)">
						 <ion-icon name="wallet-outline" class="me-2"></ion-icon>
						포인트
					</a>
				</li>
				
				<li class="nav-item">
					<a href="javascript:void(0);" data-section="review" class="nav-link" onclick="setActive(this)">
						<ion-icon name="chatbubble-ellipses-outline" class="me-2"></ion-icon> 
						관람평
					</a>
				</li>
				
				<li class="nav-item">
					<a href="javascript:void(0);" data-section="coupon" class="nav-link" onclick="setActive(this)">
						<ion-icon name="pricetag-outline" class="me-2"></ion-icon>
						쿠폰
					</a>
				</li>
			</ul>
		<hr>
	</div>