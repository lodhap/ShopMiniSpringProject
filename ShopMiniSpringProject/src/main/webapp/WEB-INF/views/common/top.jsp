<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${empty login }">
	<a href="loginForm">로그인</a>&nbsp;
	<a href="CartListServlet">장바구니</a>&nbsp;
	<a href="memberForm">회원가입</a>&nbsp;
</c:if>
<c:if test="${!empty login }">
안녕하세요?  ${login.userid }     님 !<br>
	<a href="loginCheck/cartList">장바구니</a>&nbsp;
	<a href="loginCheck/myPage">마이페이지</a>&nbsp;
	<a href="loginCheck/logout">로그아웃</a>&nbsp;
</c:if>