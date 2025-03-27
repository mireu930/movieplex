<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${theaterList }" var="t">
	<li class="list-group-item">
		<div>
			<strong>${t.movieDTO.movieTitle}</strong><br> 
			<span class="fw-semibold text-secondary">${t.theaterName}관</span>
			<span class="fw-semibold text-secondary">${t.printStart} ~ ${t.printEnd}</span>
		</div>
	</li>
</c:forEach>
