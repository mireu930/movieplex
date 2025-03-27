<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${theaterList }" var="t">
	<li class="list-group-item">
		<div style="display: flex; justify-content: space-between;">
			<div>
				<strong>${t.movieDTO.movieTitle}</strong><br> 
				<span class="fw-semibold text-secondary">${t.theaterName}관</span>
				<span class="fw-semibold text-secondary">${t.printStart} ~ ${t.printEnd}</span>
			</div>
			<div>
				<button id="del_btn" type="button" class="btn btn-outline-danger" data-theater-id="${t.theaterId}">삭제하기</button>
			</div>
			
		</div>
	</li>
</c:forEach>
