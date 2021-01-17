package reply.mvc.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Reply;
import static reply.mvc.model.ReplySQL.*;

public class ReplyDAO {
	private DataSource ds;
	ReplyDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");			
		}catch(NamingException ne) {
		}
	}
	ArrayList<Reply> listR(long bSeq){
		ArrayList<Reply>list = new ArrayList<Reply>();
		String sql = LISTR;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bSeq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long seq = rs.getLong("seq");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String content = rs.getString("content");
				Date rdate = rs.getDate("rdate");
				int lev = rs.getInt("lev");
				int sunbun =rs.getInt("sunbun");
				//long b_seq = rs.getLong("board_seq");
				Reply r = new Reply(seq, name, pwd, content, rdate, lev, sunbun, bSeq);
				list.add(r);
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
	ArrayList<Reply> listRR(long bSeq){
		ArrayList<Reply>list = new ArrayList<Reply>();
		String sql = LISTRR;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bSeq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long seq = rs.getLong("seq");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String content = rs.getString("content");
				Date rdate = rs.getDate("rdate");
				int lev = rs.getInt("lev");
				int sunbun =rs.getInt("sunbun");
				//long b_seq = rs.getLong("board_seq");
				Reply r = new Reply(seq, name, pwd, content, rdate, lev, sunbun, bSeq);
				list.add(r);
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
	int lev_N(long bseq) {
		String sql = LEV_N;
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt= con.prepareStatement(sql);
			pstmt.setLong(1, bseq);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				int levN = rs.getInt(1);
				return levN;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			System.out.println(se);
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
			}
		}
	}
	void in_reply(Reply re) {
		if(re.getSunbun()==0) {
			String sql = IN_REPLY;
			PreparedStatement pstmt = null;
			Connection con = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, re.getName());
				pstmt.setString(2, re.getPwd());
				pstmt.setString(3, re.getContent());
				pstmt.setInt(4, re.getLev());
				pstmt.setLong(5, re.getBoard_seq());
				pstmt.executeUpdate();
			}catch(SQLException se) {
				System.out.println("in_reply : "+se);
			}finally {
				try {
					if(pstmt !=null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se) {
				}
			}
		}else {
			String sql = IN_REREPLY;
			PreparedStatement pstmt = null;
			Connection con = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, re.getName());
				pstmt.setString(2, re.getPwd());
				pstmt.setString(3, re.getContent());
				pstmt.setInt(4, re.getLev());
				pstmt.setInt(5, re.getSunbun());
				pstmt.setLong(6, re.getBoard_seq());
				pstmt.executeUpdate();
			}catch(SQLException se) {
				System.out.println("in_rereply : "+se);
			}finally {
				try {
					if(pstmt !=null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se) {
				}
			}
		}
	}
	int getLEV (long seq, long bseq) {
		String sql = GET_LEV;
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			pstmt.setLong(2, bseq);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int lev=rs.getInt(1);
				return lev;
			}else {
				int lev = -1;
				return lev;
			}
			
		}catch(SQLException se) {
			System.out.println(se);
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
			}
		}
	}
	int getSUN (int lev, long bseq) {
		String sql = GET_SUN;
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, lev);
			pstmt.setLong(2, bseq);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int sun=rs.getInt(1);
				return sun;
			}else {
				int sun = -1;
				return lev;
			}
			
		}catch(SQLException se) {
			System.out.println(se);
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
			}
		}
	}
	void b_reply_del(long bseq) {
		String sql = B_REPLY_DEL;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bseq);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("in_rereply : "+se);
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
			}
		}
		
	}
}
