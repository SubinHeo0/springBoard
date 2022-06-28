<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>commException.jsp</h1>
	예외처리 페이지
	(5초뒤에 페이지 이동합니다.)
	${test }
	
	<%
		//response.setHeader("Refresh", "5; url='/board/listAll'");
	%>
</body>
</html>