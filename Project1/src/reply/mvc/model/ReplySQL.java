package reply.mvc.model;

public class ReplySQL {
	static final String LISTR = "select * from reply where board_seq=? order by lev, sunbun";
	static final String LEV_N = "select count(*) from REPLY where BOARD_SEQ=?";
	static final String IN_REPLY = "insert into REPLY values(REPLY_SEQ.nextval, ?, ?, ?, SYSDATE, ?, 0, ?)";
	static final String IN_REREPLY = "insert into REPLY values(REPLY_SEQ.nextval, ?, ?, ?, SYSDATE, ?, ?, ?)";
	static final String GET_LEV = "select lev from REPLY where SEQ = ? and board_seq=?";
	static final String GET_SUN = "select count(sunbun) from reply where lev =? and board_seq=? ";
	static final String LISTRR = "select * from reply where sunbun>=0 and board_seq=? order by lev, sunbun";
	static final String B_REPLY_DEL = "delete from REPLY where BOARD_SEQ = ?";
}
