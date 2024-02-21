<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Error 페이지</h1>
<%
	out.print(exception);
%>
<br>
<a href="http://localhost:8092//app/">메인화면으로</a><br>
<a href="/">메인화면으로2</a>
</body>
</html>