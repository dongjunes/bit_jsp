<%@page import="web.BBSItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%int seqNo=Integer.parseInt(request.getParameter("SEQ_NO"));
    System.out.println("SEQNO = " + seqNo);
    BBSItem bbsitem=new BBSItem();
    bbsitem.setSeqNo(seqNo);
    bbsitem.readDB();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>아이템 페이지</h2>
제목 :
	<%=bbsitem.getContent() %><br /> 작성자 :
	<%=bbsitem.getWriter() %><br /> 작성일시 :
	<%=bbsitem.getDate() %><%=bbsitem.getTime() %><br />
	===================================
	<br />
	<%=bbsitem.getContent() %>
</body>
</html>