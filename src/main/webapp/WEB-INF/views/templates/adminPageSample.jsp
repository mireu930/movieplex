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
<title>Album example · Bootstrap v5.3</title>

<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet" href="/resources/css/calendar.css">

<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
	<!-- header -->
	<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
	<main >
		<div class="d-flex">
			<div class="adminSidebar">
				<c:import url="/WEB-INF/views/templates/adminSideBar.jsp"></c:import>
			</div>
			<div id="mainContents">


			</div>
		</div>
	</main>

	<script src="/resources/js/calendar.js"></script>
	<script src="/resources/js/adminPage.js"></script>
	<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</body>
</html>