<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="seatBookSection mt-4">
            <h2>예매</h2>
            <hr>
            <div class="row">
                <!-- 좌석 선택 영역 -->
                <div class="col-md-8 seat-selection p-0">
                    <div class="audience-selection" id="audience">
                        <div id="adult">
                            <span>일반</span>
                            <div class="btn-group" role="group"
                                aria-label="Basic radio toggle button group">
                                <input type="radio" class="btn-check adultPerson" name="adult" id="adult0"
                                    autocomplete="off" value=0 checked>
                                <label class="btn btn-outline-secondary" for="adult0">0</label>

                                <input type="radio" class="btn-check adultPerson" name="adult" id="adult1"
                                    autocomplete="off" value="1">
                                <label class="btn btn-outline-secondary" for="adult1">1</label>

                                <input type="radio" class="btn-check adultPerson" name="adult" id="adult2"
                                    autocomplete="off" value="2">
                                <label class="btn btn-outline-secondary" for="adult2">2</label>

                                <input type="radio" class="btn-check adultPerson" name="adult" id="adult3"
                                    autocomplete="off" value="3">
                                <label class="btn btn-outline-secondary" for="adult3">3</label>

                                <input type="radio" class="btn-check" name="adult" id="adult4"
                                    autocomplete="off" value="4">
                                <label class="btn btn-outline-secondary" for="adult4">4</label>
                                <input type="radio" class="btn-check" name="adult" id="adult5"
                                    autocomplete="off" value="5">
                                <label class="btn btn-outline-secondary" for="adult5">5</label>
                            </div>
                        </div>
                        <div id="teenager">
                            <span>청소년</span>
                            <div class="btn-group" role="group"
                                aria-label="Basic radio toggle button group">
                                <input type="radio" class="btn-check" name="teen" id="teen0"
                                    autocomplete="off" checked value="0">
                                <label class="btn btn-outline-secondary" for="teen0">0</label>
                                <input type="radio" class="btn-check" name="teen" id="teen1"
                                    autocomplete="off" value="1">
                                <label class="btn btn-outline-secondary" for="teen1">1</label>

                                <input type="radio" class="btn-check" name="teen" id="teen2"
                                    autocomplete="off" value="2">
                                <label class="btn btn-outline-secondary" for="teen2">2</label>

                                <input type="radio" class="btn-check" name="teen" id="teen3"
                                    autocomplete="off" value="3">
                                <label class="btn btn-outline-secondary" for="teen3">3</label>
                                <input type="radio" class="btn-check" name="teen" id="teen4"
                                    autocomplete="off" value="4">
                                <label class="btn btn-outline-secondary" for="teen4">4</label>
                                <input type="radio" class="btn-check" name="teen" id="teen5"
                                    autocomplete="off" value="5">
                                <label class="btn btn-outline-secondary" for="teen5">5</label>
                            </div>
                        </div>
                    </div>
                    <div class="seat-layout">
                        <div id="screen"><img src="/resources/image/img-theater-screen.png"></div>
                        <div id="seat">
                            <!--좌석 들어가는 자리-->
                        </div>
                    </div>
                </div>
                <!-- 영화 정보 영역 -->
                <div class="col-md-4 movie-info p-3">
                    <!-- 제목 영역 (보라색) -->
                    <div class="movie-title">
                        <h5>${theaterDTO.movieDTO.movieTitle}</h5>
                    </div>
                    <hr>
                    <!-- 포스터와 정보 영역 (주황색 + 빨간색) -->
                    <div class="movie-content d-flex align-items-start gap-3 mt-2">
                        <div class="movie-poster">
                            <img src="https://image.tmdb.org/t/p/w500/${theaterDTO.movieDTO.shortPoster}" id="moviePoster" alt="${theaterDTO.movieDTO.movieTitle}">
                        </div>
                        <div class="movie-details">
                            <p>${theaterDTO.printDate}</p>
                            <p>${theaterDTO.timeStart} ~ ${theaterDTO.timeEnd}</p>
                            <p>${theaterDTO.theaterName}관</p>
                        </div>
                    </div>
                    <hr>
                    <div>

                    </div>
                    <!-- 금액 영역-->
                    <div class="movie-price mt-3">
                        <span>금액: <span id="ticketPrice">0</span> 원</span>
                    </div>
                    <hr>
                    <!-- 버튼 영역 -->
                    <div class="movie-actions mt-3">
                        <button id="finalBtn" class="btn btn-outline-primary w-100">예매 완료</button>
                    </div>
                </div>
                
                
            </div>
        </div>
