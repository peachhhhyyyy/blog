package board.mvc.model;

public class BoardSQL {
	final static String LIST = "select * from BOARD where email= ? order by seq desc";
	final static String LIST_ALL = "select * from BOARD order by seq desc";
	final static String WRITE = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, sysdate, ?, ?, ?)";
	final static String DELETE = "delete from BOARD where seq=?";
	final static String GETBOARD = "select * from BOARD where seq=?";
	final static String MODIFIED = "update BOARD set SUBJECT=?, CONTENT=?, FNAME=?, OFNAME=?, FSIZE=? where SEQ=?";
	final static String MODIFIED_NOFILE = "update BOARD set SUBJECT=?, CONTENT=? where SEQ=?";
}