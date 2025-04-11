<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<link rel="icon" href="/resources/image/admin_page.png"/> 
<title>관리자 페이지</title>

<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet" href="/resources/css/calendar.css">
<link rel="stylesheet" href="/resources/css/adminAddTheaterForm.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main >
		<div id="contents">
			<div class="adminSidebar">
				<c:import url="/WEB-INF/views/templates/adminSideBar.jsp"></c:import>
			</div>
			<div id="mainContents">


			</div>
		</div>
	</main>
	<script src="/resources/js/adminPage.js"></script>
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>