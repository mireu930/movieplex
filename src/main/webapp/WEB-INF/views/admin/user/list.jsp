<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<label for="" class="form-label" style="font-size: 1.2em; font-weight: bold;">회원정보</label>

<form id="list_form" style="width: 600px; margin: 20px auto;">
    <input type="hidden" name="page" id="pageNum">
    <div class="row mb-3">
     <div class="col-md-3">
        <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
        <select class="form-select" name="kind" id="inlineFormSelectPref">
          <option value="k1">회원아이디</option>
          <option value="k2">회원이름</option>
        </select>
      </div>
      <div class="col-md-6">
        <label class="visually-hidden" for="inlineFormInputGroupUsername"></label>
          <input type="text" name="search" value="${pager.search}" class="form-control" id="inlineFormInputGroupUsername" placeholder="검색어를 입력하세요">
      </div>
    
      <div class="col-md-3">
        <button type="submit" class="btn btn-primary">검색</button>
      </div>
    
    </div>
</form>

<table style="width: 600px; margin: 20px auto; border-collapse: collapse;">
    <thead>
        <tr style="background-color: #ddd; text-align: left;">
            <th style="padding: 10px; border-bottom: 2px solid #bbb;">회원 아이디</th>
            <th style="padding: 10px; border-bottom: 2px solid #bbb;">회원 이름</th>
            <th style="padding: 10px; border-bottom: 2px solid #bbb; text-align: center;">상태</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${userList}" var="u">
            <tr style="background-color: #f9f9f9;">
                <td style="padding: 10px; border-bottom: 1px solid #ddd; cursor: pointer;" id="userId">${u.userId}</td>
                <td style="padding: 10px; border-bottom: 1px solid #ddd;">${u.userName}</td>
                <td style="padding: 10px; border-bottom: 1px solid #ddd; text-align: center;">
                    ${u.userOut == 1 ? "<span style='color: red;'>탈퇴</span>" : "<span style='color: green;'>활동중</span>"}
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="pagination-wrapper" style="width: 600px; margin: 20px auto;">
    <nav aria-label="Page navigation example">
      <ul class="pagination">
          
        <li class="page-item"><button class="page-link pages" data-page-num="${pager.start-1}">Previous</button></li>
          <c:forEach begin="${pager.start}" end="${pager.end}" var="i">
            <li class="page-item"><button class="page-link pages" data-page-num="${i}">${i}</button></li>
          </c:forEach>
       <li class="page-item ${pager.endCheck?'disabled':''}"><button class="page-link pages" data-page-num="${pager.end+1}">Next</button></li>
      </ul>
</nav>
</div>