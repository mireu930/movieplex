<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
        <div class="payment-success bg-light text-center p-4">
            <div class="success-icon mb-3">
                ✔️
            </div>
            <h2 class="text-success">결제가 완료되었습니다!</h2>
            <p class="lead">예매가 성공적으로 완료되었습니다.</p>
            <p>예매 내역은 <strong>마이페이지</strong>에서 확인할 수 있습니다.</p>
            
            <!-- 예매 정보 영역 -->
            <div class="success-movie-info text-start mt-4">
                <h5>예매 정보</h5>
                <p><strong>영화 제목:</strong> <span id="movieName">${theaterDTO.movieDTO.movieTitle }</span></p>
                <p><strong>상영관:</strong> <span id="theaterName">${theaterDTO.theaterName }관</span></p>
                <p><strong>상영 시간:</strong> <span id="screeningTime">${theaterDTO.printStart } ~ ${theaterDTO.printEnd }</span></p>
                <p><strong>예매 인원:</strong> <span id="numPeople"></span></p>
            </div>
            
            <div class="account-info text-start">
                <h5>입금 계좌 정보</h5>
                <p><strong>은행명:</strong> <span id="bankName"></span></p>
                <p><strong>계좌번호:</strong> 1111-1111-1111</p>
                <p><strong>예금주:</strong> 무비플렉스</p>
            </div>

            <div class="btn-group successBtn mt-3">
                <a href="/users/mypage" class="btn btn-outline-info">마이페이지로 이동</a>
                <a href="/" class="btn btn-outline-primary">홈으로 돌아가기</a>
            </div>
        </div>
    </div>