package web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BBSItem {
	private int seqNo; // 순번
	private String title; // 제목
	private String content; // 내용
	private String writer; // 저자
	private Date date; // 저장일자
	private Time time; // 저장시기

	public BBSItem() {
	}

	public int getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return this.writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public void readDB() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();

			if (conn == null) {
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			}
			stmt = conn.createStatement();
			//rs = stmt.executeQuery("select * from bbs where seqNo = '" + seqNo + "';");
			rs = stmt.executeQuery("select * from bbsdata where seqNo = '"+seqNo+"';");
			if(rs.next()){
				this.title=rs.getString("title");//제목
				this.content=rs.getString("content");//내용
				this.writer=rs.getString("writer");//작성자
				this.date=rs.getDate("wdate");//저장일자
				this.time=rs.getTime("wtime");//저장시각
				
			}
			
		
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}

		}
	}
}
