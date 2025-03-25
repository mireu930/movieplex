<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header class="header-container border-bottom lh-1 py-3">
	<div class="row flex-nowrap align-items-center">
		<div class="logo col-2 pt-1">
			<a class="link-secondary" href="/reviewNest"><img id="logo_img" src="/resources/image/reviewnest_logo2.png"></a>
		</div>
	
		<div class="col-3 text-center fs-5 fw-bold">
			<ul class="nav gap-5">
				<li><a href="/reviewNest" class="nav-link px-2 link-secondary">홈</a></li>
				<!-- <li><a href="#" class="nav-link px-2"></a></li> -->
				<li><a href="/reviewNest/getMovieList" class="nav-link px-2 link-secondary">영화</a></li>
				<!-- <li><a href="#" class="nav-link px-2"></a></li> -->
				<li><a href="/reviewNest/getTvList" class="nav-link px-2 link-secondary" >시리즈</a></li>
			</ul>
		</div>
		<div class="col-7 d-flex justify-content-end align-items-center gap-3">
			<a class="btn btn-sm btn-outline-secondary" href="#">로그인</a>
		</div>
	</div>
</header>