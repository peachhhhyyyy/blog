package board.mvc.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Board;
import static board.mvc.model.BoardSQL.*;

public class BoardDAO {
	private DataSource ds;
	BoardDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");			
		}catch(NamingException ne) {
		}
	}
	ArrayList<Board> list(String email){
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = LIST;
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long seq = rs.getLong(1);
				String name = rs.getString(2);
				//String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				java.sql.Date rdate = rs.getDate(6);
				String fname = rs.getString(7);
				String ofname = rs.getString(8);
				long fsize = rs.getLong(9);
				Board b = new Board(seq, name, email, subject, content, rdate, fname, ofname, fsize);
				list.add(b);				
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
			}
		}
	}
	ArrayList<Board> listA(String email){
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = LIST_ALL;
		Statement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con=ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				long seq = rs.getLong(1);
				String name = rs.getString(2);
				//String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				java.sql.Date rdate = rs.getDate(6);
				String fname = rs.getString(7);
				String ofname = rs.getString(8);
				long fsize = rs.getLong(9);
				Board b = new Board(seq, name, email, subject, content, rdate, fname, ofname, fsize);
				list.add(b);				
			}
			return list;
		}catch(SQLException se){
			System.out.println("listA : "+se);
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}			
		}
	}
	void write(Board b) {
		String sql = WRITE;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getEmail());
			pstmt.setString(3, b.getSubject());
			pstmt.setString(4, b.getContent());
			pstmt.setString(5, b.getFname());
			pstmt.setString(6, b.getOfname());
			pstmt.setLong(7, b.getFsize());
			pstmt.executeUpdate();
			System.out.println("게시글 작성 성공");
		}catch(SQLException se){
			System.out.println("write : "+se);
			System.out.println("게시글 작성 실패");
		}finally {
			try {
				if (pstmt !=null ) pstmt.close();
				if (con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void delete(long seq) {
		String sql = DELETE;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println("se : "+se);
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	Board getBoard(long seq) {
		String sql = GETBOARD;
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//long seq = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				java.sql.Date rdate = rs.getDate(6);
				String fname = rs.getString(7);
				String ofname = rs.getString(8);
				long fsize = rs.getLong(9);
				Board b = new Board(seq, name, email, subject, content, rdate, fname, ofname, fsize);
				return b;
			}
		}catch(SQLException se) {
			System.out.println("se : "+se);
			return null;
		}finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(SQLException se) {}
		}
		return null;
	}
	void modified(Board b) {
		String sql = MODIFIED;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b.getSubject());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFname());
			pstmt.setString(4, b.getOfname());
			pstmt.setLong(5, b.getFsize());
			pstmt.setLong(6, b.getSeq());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("se : "+se);
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void modifiedNofile(Board b) {
		String sql = MODIFIED_NOFILE;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b.getSubject());
			pstmt.setString(2, b.getContent());
			pstmt.setLong(3, b.getSeq());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("se : "+se);
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(SQLException se) {}
		}
	}
}
