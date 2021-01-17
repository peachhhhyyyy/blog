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
			System.out.println("���̵� ����");
			return NO_ID;
		}else {
			String pwdDb = m.getPwd();
			if(pwdDb != null) pwdDb = pwdDb.trim();
			if(!pwd.equals(pwdDb)) {
				System.out.println("�н����� ����");
				return NO_PWD;
			}else {
				System.out.println("�α��� ����");
				return PASS;
			}
		}
	}	
	public Member getMemberS(String email) {
		Member m = dao.getMember(email);
		m.setPwd(""); // for ���ȼ�
		return m;
	}
	public int register(Member mem, String email) {
		Member m = dao.getMember(email);
		if(m == null) {
			System.out.println("ȸ������ ����");
			dao.register(mem);
			return PASS;
		}else{
			System.out.println("�ߺ��� ���̵� ����");
			return REG_ID;
		}		
		
	}
	
}