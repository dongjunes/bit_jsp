package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new-script")
public class SubscriptServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String currentURL=request.getParameter("CURRENT_URL");

		System.out.println(name + "  " + id + "  " + password + "  " + currentURL);

		if (name == null || id == null || password == null) {
			try {
				throw new Exception("데이터를 입력하세요");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "1234");
			if (conn == null) {
				throw new Exception("데이터를 연결할 수 없습니다.");
			}

			stmt = conn.createStatement();
			String command = String.format("insert into userinformation(name, id, password) values('%s','%s','%s');",
					name, id, password);
			int rowNum = stmt.executeUpdate(command);
			if (rowNum < 1) {
				throw new Exception("데이터를 입력할 수 없습니다.");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("SubScriptResult.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

	}

}
