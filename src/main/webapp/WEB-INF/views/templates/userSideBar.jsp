<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary min-vh-100 "
		style="width: 350px; border-right: 2px solid gray;">
		<a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
			<img src="/resources/image/admin_page.png" style="width: 30px; height: auto; margin-right: 10px;"> <span
				class="fs-4">설정</span>
		</a>
		<hr>
		<!-- 사이드 바에서 선택시 js로 active 속성 변경 해줘야 합니다! 그래야 파란색 바가 해당 페이지로 이동합니다! -->
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item">
				<a href="javascript:void(0);" data-section="user" class="nav-link" onclick="setActive(this)">
					<img src="/resources/image/admin_user.png" style="width: 30px; height: auto;" class="me-2"> 내정보
				</a>
			</li>
			<li class="nav-item">
				<a href="javascript:void(0);" data-section="movies" class="nav-link" onclick="setActive(this)">
					<img src="/resources/image/admin_table.png" style="width: 30px; height: auto;" class="me-2"> 결제내역
				</a>
			</li>
		
				<li class="nav-item">
					<a href="javascript:void(0);" data-section="theater" class="nav-link" onclick="setActive(this)">
						<img src="/resources/image/admin_user.png" style="width: 30px; height: auto;" class="me-2"> 포인트
					</a>
				</li>
				
				<li class="nav-item">
					<a href="javascript:void(0);" data-section="theater" class="nav-link" onclick="setActive(this)">
						<img src="/resources/image/admin_user.png" style="width: 30px; height: auto;" class="me-2"> 관람평
					</a>
				</li>
				
				<li class="nav-item">
					<a href="javascript:void(0);" data-section="theater" class="nav-link" onclick="setActive(this)">
						<img src="/resources/image/admin_user.png" style="width: 30px; height: auto;" class="me-2"> 쿠폰
					</a>
				</li>
			</ul>
		<hr>
	</div>