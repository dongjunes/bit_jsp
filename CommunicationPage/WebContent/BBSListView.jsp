<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>게시판 목록 보기</h3>
<table border="1" width="200">
	<tr>
		<td>제목</td>
		<td>작성자</td>
	</tr>
	<c:forEach var="cnt" begin="0" end="${BBS_LIST.listSize-1 }">
		<tr>
			<td><a href="WebTemplate.jsp?BODY_PATH=BBSItemView.jsp?SEQ_NO=${BBS_LIST.seqNo[cnt] }"> ${BBS_LIST.title[cnt] }</a></td>
			<td>${BBS_LIST.writer[cnt] }</td>
			
		</tr>
	</c:forEach>
</table>

<c:if test="${!BBS_LIST.lastPage }">
<a href="bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize-1] }">다음페이지</a>
</c:if>