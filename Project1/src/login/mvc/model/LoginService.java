package login.mvc.model;

import static login.mvc.model.LoginCase.*;

import mvc.domain.Member;

public class LoginService {
	private LoginDAO dao;
	private static final LoginService instance = new LoginService();
	private LoginService() {
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	
	public int checkMember(String email, String pwd) {
		Member m = dao.getMember(email);
		if(m == null) {
			System.out.println("아이디 없음");
			return NO_ID;
		}else {
			String pwdDb = m.getPwd();
			if(pwdDb != null) pwdDb = pwdDb.trim();
			if(!pwd.equals(pwdDb)) {
				System.out.println("패스워드 없음");
				return NO_PWD;
			}else {
				System.out.println("로그인 성공");
				return PASS;
			}
		}
	}	
	public Member getMemberS(String email) {
		Member m = dao.getMember(email);
		m.setPwd(""); // for 보안성
		return m;
	}
	public int register(Member mem, String email) {
		Member m = dao.getMember(email);
		if(m == null) {
			System.out.println("회원가입 가능");
			dao.register(mem);
			return PASS;
		}else{
			System.out.println("중복된 아이디 있음");
			return REG_ID;
		}		
		
	}
	
}