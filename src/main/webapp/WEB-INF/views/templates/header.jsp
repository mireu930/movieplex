<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container header">
	<header
		class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3">
		<div class="col-md-1 mb-2 mb-md-0">
			<!-- <a href="/"
				class="d-inline-flex link-body-emphasis text-decoration-none"> <svg
					class="bi" width="40" height="32" role="img" aria-label="Bootstrap">
					<use xlink:href="#bootstrap"></use></svg>
			</a> -->
		</div>
		<div class="d-flex justify-content-between align-items-center px-4 gap-5 fs-5 fw-bold">
		    <!-- 왼쪽 메뉴 -->
		    <ul class="nav d-flex justify-content-center gap-5">
		        <li><a href="#" class="nav-link px-2 ">영화</a></li>
		        <li><a href="/movieBooks/booking" class="nav-link px-2">예매</a></li>
		        <li><a href="#" class="nav-link px-2">ReviewNest</a></li>
		    </ul>
		
		    <!-- 중앙에 위치할 div -->
		    <div class="logoDiv text-center flex-shrink-0">
		        <a href="/"><img src="/resources/image/logo_clean.png"></a>
		    </div>
		
		    <!-- 오른쪽 메뉴 -->
		    <ul class="nav d-flex justify-content-center gap-5">
		        <li><a href="#" class="nav-link px-2 link-secondary">공지사항</a></li>
		        <li><a href="#" class="nav-link px-2 link-secondary">FAQ</a></li>
		        <li><a href="#" class="nav-link px-2 link-secondary">QNA</a></li>
		    </ul>
		</div>
		<div class="col-md-1 text-end">
			<c:if test="${empty user}">
				<a class="nav-link px-2 link-secondary" href="/users/login">로그인</a>				
			</c:if>
			<c:if test="${not empty user and user.userId ne 'sss'}">
				<a class="nav-link px-2 link-secondary" href="/users/logout">로그아웃</a>
				<a class="nav-link px-2 link-secondary" href="/users/mypage">마이페이지</a>
			</c:if>
			<c:if test="${not empty user and user.userId eq 'sss'}">
				<a class="nav-link px-2 link-secondary" href="/users/logout">로그아웃</a>
				<a class="nav-link px-2 link-secondary" href="/users/admin">관리자페이지</a>
			</c:if>
		</div>
	</header>
</div>