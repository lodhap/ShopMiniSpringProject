<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>마이페이지 화면입니다.</h1>
	<jsp:include page="common/top.jsp"></jsp:include><br>
	<jsp:include page="common/menu.jsp"></jsp:include>
	<hr>
	<jsp:include page="member/mypage.jsp"></jsp:include>
</body>
</html>