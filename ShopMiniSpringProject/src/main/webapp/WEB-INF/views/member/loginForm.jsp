<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${!empty sessionScope.mesg }">
	<script>
		alert("${sessionScope.mesg}");
	</script>
</c:if>

<%
session.removeAttribute("mesg");
%>

<form action="login" method="post">
아이디:<input type="text" name="userId"><br>
비밀번호:<input type="text" name="passwd"><br>
<input type="submit" value="로그인">
<input type="reset" value="취소">
</form>