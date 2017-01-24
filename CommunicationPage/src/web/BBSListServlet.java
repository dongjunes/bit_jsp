package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/bbs-list")
public class BBSListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("BBS_LIST", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BBSListView.jsp");
		dispatcher.forward(request, response);

	}

	private BBSList readDB(int upperSeqNo) throws Exception {
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
	}

}
