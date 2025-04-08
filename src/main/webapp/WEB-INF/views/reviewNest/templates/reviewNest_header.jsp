<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header-container border-bottom lh-1 py-3">
	<div class="row flex-nowrap align-items-center">
		<div class="logo col-2 pt-1">
			<a class="link-secondary" href="/reviewNest"><img id="logo_img" src="/resources/image/reviewnest_logo2.png"></a>
		</div>
	
		<div class="col-auto text-center fs-5 fw-bold">
			<ul class="nav gap-4 ">
				<li><a href="/reviewNest" class="nav-link px-2 link-secondary">홈</a></li>
				<li><a href="/reviewNest/getMovieList" class="nav-link px-2 link-secondary">영화</a></li>
				<li><a href="/reviewNest/getTvList" class="nav-link px-2 link-secondary" >시리즈</a></li>
				<li><a href="/" class="nav-link px-2 link-secondary">MOVIEPLEX</a></li>
			</ul>
		</div>
		
		<div class="col-6 d-flex justify-content-end align-items-center gap-3 me-4" >
			<ul class="nav gap-4">
				<c:if test="${empty user}">
				<li><a class="btn btn-sm btn-outline-secondary" href="/users/login">로그인</a></li>
				</c:if>
				<c:if test="${not empty user}">
				<li><a href="/users/reviewNest/nestMypage" class="nav-link px-2 link-secondary">마이페이지</a></li>
				<li><a class="nav-link px-2 link-secondary" href="/users/logout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</header>