package mvc.domain;

import java.sql.Date;

public class Member {
	private long seq;
	private String name;
	private String email;
	private String pwd;
	private Date rdate;
	public Member() {
		
	}
	public Member(long seq, String name, String email, String pwd, Date rdate) {
		this.seq = seq;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.rdate = rdate;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

}
