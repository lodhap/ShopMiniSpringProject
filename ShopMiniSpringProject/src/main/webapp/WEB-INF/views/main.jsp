<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$("#loginForm").on("submit", function(){
			var userId = $("#userId")
			var passwd = $("#passwd")
			if(userId.val().length==0){
				event.preventDefault();
				alert("아이디를 입력해주세요.");
				userId.focus();
			}else if(passwd.val().length==0){
				event.preventDefault();
				alert("비밀번호를 입력해주세요.");
				passwd.focus();
			}
		})//ajax
	})//ready
</script>
<c:if test="${!empty success}">
	<script>
		alert("${success}");
	</script>
</c:if>
</head>
<body>
	<h1>메인화면입니다.</h1> 
	<jsp:include page="common/top.jsp"></jsp:include><br>
	<jsp:include page="common/menu.jsp"></jsp:include>
	<hr>
	<jsp:include page="goods/goodsList.jsp"></jsp:include>
</body>
</html>