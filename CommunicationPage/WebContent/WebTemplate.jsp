<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">비트 커뮤니케이션 사이트</h2>
	<br />
	<br />

	<table align="center" border="0" cellpadding="70px">
		<tr>
			<td><a href="TodaysMent.jsp">오늘의 한마디</a></td>
			<c:choose> 
			<c:when test="${sessionScope.LOGIN_ID == null }">
				<td><a href="LoginWindow.html">로그인</a></td>
			</c:when>
			<c:otherwise>
				<td><a href="LogoutWindow.jsp">로그아웃</a></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
		<c:choose>
		<c:when test="${sessionScope.LOGIN_ID == null }">
		<td><a href="LoginWindow.jsp">로그인 후 게시글을 남길 수 있습니다.</a></td>
		</c:when>
		<c:otherwise>
				<td><a href="BBSInput.html">글쓰기</a></td>
			</c:otherwise>
		</c:choose>
			<td><a href="Subscript.html">회원가입</a></td>
		</tr>
	</table>
	
	<table border="1" width="200" height="100" cellpadding="30px" align="center">
  		<tr>
			<td>글목록  <a href="BBSList.jsp">확인하기</a></td>
 		 </tr>
 		 <tr  height="150" valign="top">
			<td><jsp:include page="${param.BODY_PATH }"/></td>
  		</tr>
  </table>
	

</body>
</html>