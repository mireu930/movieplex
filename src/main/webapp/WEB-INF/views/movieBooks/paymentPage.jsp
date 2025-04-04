<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<div class="container payment-container">
			<div class="row payment no-gutters">
				<!-- 좌측 A 영역 -->

				<div class="col-md-8 payment-left">
					<h3>결제</h3>

					<div class="payment-infoSection">
						<div class="payment-top">
							<div>
								<h5>예매 정보</h5>
							</div>

							<div class="payment-info">
								<div class="movImg">
									<img src="https://image.tmdb.org/t/p/w500${theaterDTO.movieDTO.shortPoster}"
										id="moviePoster" alt="${theaterDTO.movieDTO.movieTitle}">
								</div>
								<ul class="list">
									<li id="movieNm">${theaterDTO.movieDTO.movieTitle}</li>
									<li><span id="playDe">${theaterDTO.printDate}</span> <span
											id="playTime">${theaterDTO.timeStart}~
											${theaterDTO.timeEnd}</span></li>
									<li><span id="brchNm">${theaterDTO.theaterName}관</span></li>
									<li id="totalPeople"></li>
								</ul>
							</div>
						</div>

						<div class="row payment-bottom">
							<div class="col-md-6">
								<div class="payment-coupon">
									<h5>쿠폰</h5>
									<div>
										<select class="form-select form-select-sm" aria-label="Small select example">
											<option selected>Open this select menu</option>
											<c:forEach items="${coupons}" var="v">
												<option value="1">One</option>
											</c:forEach>

											<option value="2">Two</option>
											<option value="3">Three</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="payment-method">
									<h5>결제 수단</h5>
									<div id="payment-radio">
										<div class="form-check mb-4">
											<input class="form-check-input" type="radio" name="method" id="method1"
												value="0">
											<label class="form-check-label" for="method1">
												무통장 입금
											</label>
											<select id="bank" class="form-select form-select-sm"
												aria-label="Small select example" disabled>
												<option selected disabled value="default">은행을 선택하세요</option>
												<option value="신한은행">신한은행</option>
												<option value="국민은행">국민은행</option>
												<option value="기업은행">기업은행</option>
												<option value="우리은행">우리은행</option>
											</select>
										</div>

										<div class="form-check">
											<input class="form-check-input" type="radio" name="method" id="method2"
												value="1">
											<label class="form-check-label" for="method2">
												카드 결제
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 우측 B 영역 -->
				<div class="col-md-4 payment-right">
					<div class="payment-total">
						<h5>결제 금액</h5>
						<div class="payment-summary">
							<div class="payment-item">
								<span>성인 1명</span>
								<span>13,000원</span>
							</div>
							<div class="payment-item">
								<span>금액</span>
								<span id="total-amount">13,000원</span>
							</div>
							<div class="payment-item discount">
								<span>할인 적용</span>
								<span id="discount-amount">0원</span>
							</div>
							<hr>
							<div class="payment-item final">
								<span>최종 결제 금액</span>
								<span id="final-amount" class="text-success">13,000원</span>
							</div>
						</div>
						<div class="payment-buttons">
							<button class="btn btn-secondary">이전</button>
							<button id="payBtn" class="btn btn-primary">결제</button>
						</div>
					</div>
				</div>

			</div>
		</div>