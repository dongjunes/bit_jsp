<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="web.BBSList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String strUpperSeqNo = request.getParameter("LAST_SEQ_NO");
	int upperSeqNo;
	if (strUpperSeqNo == null) {
		upperSeqNo = Integer.MAX_VALUE;
	} else {
		upperSeqNo = Integer.parseInt(strUpperSeqNo);
	}
	BBSList list = null;
	try {
		list = readDB(upperSeqNo);
		System.out.println("list datasize - " + list.getListSize());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}

	request.setAttribute("BBS_LIST", list);
	RequestDispatcher dispatcher = request.getRequestDispatcher("WebTemplate.jsp?BODY_PATH=BBSListView.jsp");
	dispatcher.forward(request, response);
%>


<%!private BBSList readDB(int upperSeqNo) throws Exception {
		BBSList list = new BBSList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();
			if (conn == null) {
				throw new Exception("연결할 수 없음");
			}

			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from bbsdata where seqNo < " + upperSeqNo + " order by seqno desc");

			for (int cnt = 0; cnt < 5; cnt++) {
				if (!rs.next()) {
					break;
				}
				list.setSeqNo(cnt, rs.getInt("seqNo"));
				list.setTitle(cnt, rs.getString("title"));
				list.setWriter(cnt, rs.getString("writer"));
				list.setDate(cnt, rs.getDate("wdate"));
				list.setTime(cnt, rs.getTime("wtime"));

			}
			if (!rs.next())
				list.setLastPage(true);

		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return list;
	}%>