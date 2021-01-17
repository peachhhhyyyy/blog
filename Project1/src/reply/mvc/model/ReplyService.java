package reply.mvc.model;

import java.util.ArrayList;

import mvc.domain.Reply;

public class ReplyService {
	private ReplyDAO dao;
	
	private static final ReplyService instance = new ReplyService();
	private ReplyService(){
		dao = new ReplyDAO();
	} 
	public static ReplyService getInstance() {
		return instance;
	}
	public ArrayList<Reply> listR(long bseq){
		ArrayList<Reply>listR= dao.listR(bseq);
		if(listR==null) {
			System.out.println("listR 가져오기 실패");
			return null;
		}else {
			System.out.println("listR 가져오기 성공");
			return listR;
		}	
	}
	public ArrayList<Reply> listRR(long bseq){
		ArrayList<Reply>listRR= dao.listRR(bseq);
		if(listRR==null) {
			System.out.println("listR 가져오기 실패");
			return null;
		}else {
			System.out.println("listR 가져오기 성공");
			return listRR;
		}	
	}
	public int levN(long bseq) {
		int levN = dao.lev_N(bseq);
		if(levN!= -1) {
			return levN;
		}else {
			System.out.println("안넘어와~");
			return -1;
		}
	}
	public void in_reply(Reply re) {
		dao.in_reply(re);
	}

	/*
	 * public void in_rereply(Reply re) { dao.in_rereply(re); }
	 */
	public int get_lev(long seq, long bseq) {
		int lev = dao.getLEV(seq, bseq);
		if(lev!= -1) {
			return lev;
		}else {
			System.out.println("lev값 못가져옴");
			return -1;
		}
		
	}
	public int get_sun(int lev, long bseq) {
		int sun = dao.getSUN(lev, bseq);
		if(lev!= -1) {
			return sun;
		}else {
			System.out.println("sun값 못가져옴");
			return -1;
		}
		
	}
	public void b_reply_del(long bseq) {
		dao.b_reply_del(bseq);
	}
}
