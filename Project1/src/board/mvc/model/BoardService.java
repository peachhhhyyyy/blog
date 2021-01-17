package board.mvc.model;

import java.util.ArrayList;

import mvc.domain.Board;

import static file.mvc.model.Filecheck.*;

public class BoardService {
	private BoardDAO dao;
	
	private static final BoardService instance = new BoardService();
	private BoardService(){
		dao = new BoardDAO();
	} 
	public static BoardService getInstance() {
		return instance;
	}
	
	public ArrayList<Board> listM(String email) {
		ArrayList<Board> list = dao.list(email); //use1
		if(list==null) {
			System.out.println("list 가져오기 실패");
			return null;
		}else {
			System.out.println("list 가져오기 성공");
			return list;
		}	
	}
	public ArrayList<Board> listA(String email) {
		ArrayList<Board> list = dao.listA(email); //use1
		if(list==null) {
			System.out.println("list 가져오기 실패");
			return null;
		}else {
			System.out.println("list 가져오기 성공");
			return list;
		}	
	}
	public void write(Board b) {
		dao.write(b);
	}
	public int fileFilter(String str) {
		System.out.println(str+"jpg");
		if(str.equals("jpg")||str.equals("jpeg")||str.equals("png")||str.equals("gif")) {
			return PASS;
		}else {
			return NO_EXTENSION;
		}
	}
	public void delete(long seq) {
		dao.delete(seq);
	}
	public Board update(long seq) {
		Board b =dao.getBoard(seq);
		return b;
	}
	public void modified(Board b) {
		String fname = b.getFname();
		if(fname!=null) {
			dao.modified(b);
			System.out.println("1. 파일 수정 완");
		}else {
			dao.modifiedNofile(b);
			System.out.println("2. 내용 수정 완");
		}	
	}
	public Board getBoard(long seq) {
		Board b = dao.getBoard(seq);
		return b;
	}
}
