package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/bbs-post")
public class BBSPostServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();

		try {

			int seqNo = 0;
			String id = (String) session.getAttribute("LOGIN_ID");
			if (id == null) {
				throw new Exception("로그인을 먼저 해주세요");
			}
			System.out.println("id : " + id);

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			GregorianCalendar now = new GregorianCalendar();

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();

			if (conn == null) {
				throw new Exception("DB와 연결할 수 없습니다.");
			}

			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) as CNT from bbsdata;");
			rs.next();
			seqNo = rs.getInt("CNT") + 1;

			String query = String.format(
					"insert into bbsdata(seqno,title,content, writer, wdate, wtime) values(%d, '%s', '%s', '%s', '%TF', '%TT');",
					seqNo, title, content, id, now, now);
			int rowNum = stmt.executeUpdate(query);
			if (rowNum < 1) {
				throw new ServletException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		response.sendRedirect("BBSPostResult.html");
		
	}

}
