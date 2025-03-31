<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<label for="" class="form-label" style="font-size: 1.2em; font-weight: bold;">회원정보</label>

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