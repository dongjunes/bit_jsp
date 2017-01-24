<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 ${sessionScope.LOGIN_ID }님 로그아웃 하시겠습니까?
 <form action="new-logout" method="get" >
 <input type="submit" value="네">
 </form>
 
 <form action="WebTemplate.jsp" >
 <input type="submit" value="아니요">
 </form>
</body>
</html>