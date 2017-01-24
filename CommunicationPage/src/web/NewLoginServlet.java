package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/new-login")

public class NewLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("ID");
		String password = request.getParameter("PASSWORD");
		String currentURL = request.getParameter("CURRENT_URL");

		System.out.println("id : " + id + "  password : " + password + "  current URL : " + currentURL);

		try {
			if(checkLogInfo(id, password)){
				HttpSession session=request.getSession();
				session.setAttribute("LOGIN_ID",id);
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("WebTemplate.jsp");

	}

	private boolean checkLogInfo(String id, String password)throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();
			if (conn == null) {
				throw new Exception("연결할 수 없습니다.");
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from userinformation where id = '" + id + "';");

			if (!rs.next())
				return false;

			String correntPassword = rs.getString("password");
			if (password.equals(correntPassword));
			
			
			
			if (password.equals(correntPassword)) {
				return true;
			} else {
				return false;
			}
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
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
	}

	

}
