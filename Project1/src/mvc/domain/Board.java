package mvc.domain;

import java.sql.Date;

public class Board {
	private long seq;
	private String name;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	private String fname;
	private String ofname;
	private long fsize;
	
	public Board() {
		
	}

	public Board(long seq, String name, String email, String subject, String content, Date rdate, String fname,
			String ofname, long fsize) {
		super();
		this.seq = seq;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
		this.fname = fname;
		this.ofname = ofname;
		this.fsize = fsize;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getOfname() {
		return ofname;
	}

	public void setOfname(String ofname) {
		this.ofname = ofname;
	}

	public long getFsize() {
		return fsize;
	}

	public void setFsize(long fsize) {
		this.fsize = fsize;
	}
	
	
}
