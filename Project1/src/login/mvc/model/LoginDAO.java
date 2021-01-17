package login.mvc.model;

import static login.mvc.model.LoginSQL.*;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import mvc.domain.Member;

class LoginDAO {
	private DataSource ds;
	LoginDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");			
		}catch(NamingException ne) {
		}
	}
	Member getMember(String email){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = MEMBER;	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long seq = rs.getLong("seq");
				String name = rs.getString("name");
				//String email = rs.getString(3);
				String pwd = rs.getString("pwd");
				Date rdate = rs.getDate("rdate");
				return new Member(seq, name, email, pwd, rdate);
			}else {
				return null;
			}
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void register(Member mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = REGISTER;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getEmail());
			pstmt.setString(3, mem.getPwd());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("LoginDAO insert() se: "+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
}