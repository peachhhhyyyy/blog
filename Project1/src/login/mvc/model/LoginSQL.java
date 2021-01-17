package login.mvc.model;

class LoginSQL {
	final static String MEMBER = "select * from member where EMAIL= ?";
	final static String REGISTER = "insert into member values(member_SEQ.nextval, ?, ?, ?, sysdate)";
}