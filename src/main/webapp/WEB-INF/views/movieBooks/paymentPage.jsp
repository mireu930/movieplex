<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="payment-container">
    <!-- 매장 정보 -->
    <div class="payment-section store-info">
        <div class="title">매장 정보</div>
        <img src="/path/to/poster.jpg" alt="영화 포스터">
        <div class="store-details">
            <p>진격의 거인 완결편 더 라스트 어택</p>
            <p>2025.04.01 (화) 18:05 ~ 20:39</p>
            <p>강동/1관 · 2D(자막)</p>
            <p>성인 1명</p>
        </div>
    </div>

    <!-- 할인 적용 -->
    <div class="payment-section discount-section">
        <div class="title">할인 적용</div>
        <div class="discount-buttons">
            <button>할인쿠폰</button>
            <button>VIP 영화쿠폰</button>
            <button>메가박스 관람권</button>
            <button>무비패스 관람권</button>
            <button>페이즈 금액권</button>
            <button>스토어 교환권</button>
        </div>
    </div>

    <!-- 결제 수단 -->
    <div class="payment-section payment-method">
        <div class="title">결제 수단</div>
        <label><input type="radio" name="payment" checked> JoongAng PAY</label><br>
        <label><input type="radio" name="payment"> 내통장결제</label>
    </div>

    <!-- 결제 금액 -->
    <div class="payment-section payment-summary">
        <div class="title">결제 금액</div>
        <div class="summary-item">
            <span>성인 1명:</span> <span>13,000원</span>
        </div>
        <div class="summary-item">
            <span>금액:</span> <span>13,000원</span>
        </div>
        <div class="summary-item">
            <span>할인적용:</span> <span>0원</span>
        </div>
        <hr>
        <div class="summary-item final-price">
            <span>최종결제금액:</span> <span>13,000원</span>
        </div>
        <button class="payment-btn">결제</button>
    </div>
</div>

