<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-grid gap-2 mb-3 mt-3">
	<button class="btn btn-danger" id="add_btn" type="button">상영 추가</button>
</div>
<div>
	<div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary border-end">
		<a href="#"
			class="d-flex align-items-center flex-shrink-0 p-3 link-body-emphasis text-decoration-none border-bottom">
			<span class="fs-5 fw-semibold">상영 시간</span>
		</a>
		<div class="list-group list-group-flush border-bottom scrollarea" id="theaterArea">
			<c:forEach items="${list }" var="l">
				<div class="list-group-item list-group-item-action py-3 lh-sm">
					<div class="d-flex w-100 align-items-center justify-content-between">

						<!-- 좌측: 시간 + 상영관 한 줄로 -->
						<div>
							<strong>${l.printStart} ~ ${l.printEnd}</strong> <span
								class="text-muted small ms-2">${l.theaterName}</span>
						</div>

						<!-- 우측: 버튼 그룹 -->
						<div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
							<button type="button" class="btn btn-primary">수정하기</button>
							<button type="button" class="btn btn-primary">삭제하기</button>
						</div>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>