package mvc.domain;

import java.sql.Date;

public class Reply {
	private long seq;
	private String name;
	private String pwd;
	private String content;
	private Date rdate;
	private int lev;
	private int sunbun;
	private long board_seq;
	public Reply() {
		
	}
	public Reply(long seq, String name, String pwd, String content, Date rdate, int lev, int sunbun, long board_seq) {
		super();
		this.seq = seq;
		this.name = name;
		this.pwd = pwd;
		this.content = content;
		this.rdate = rdate;
		this.lev = lev;
		this.sunbun = sunbun;
		this.board_seq = board_seq;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getSunbun() {
		return sunbun;
	}
	public void setSunbun(int sunbun) {
		this.sunbun = sunbun;
	}
	public long getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(long board_seq) {
		this.board_seq = board_seq;
	}
	
}
