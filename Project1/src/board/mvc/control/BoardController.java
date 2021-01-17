package board.mvc.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.mvc.model.BoardService;
import board.mvc.model.FileSet;
import login.mvc.model.LoginCase;
import login.mvc.model.LoginService;
import mvc.domain.Board;
import mvc.domain.Member;
import mvc.domain.Reply;
import reply.mvc.model.ReplyService;

import static file.mvc.model.Filecheck.*;


@WebServlet("/board/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
	private String m = "";
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		//System.out.println(m);
		if(m != null) {
			m= m.trim();
			if(m.equals("home")) {
				home(request, response);
			}else if(m.equals("list")) {
				list(request, response);
			}else if(m.equals("detail")) {
				detail(request, response);
			}else if(m.equals("login")) {
				login(request, response);
			}else if(m.equals("register")) {
				register(request, response);
			}else if(m.equals("logout")) {
				logout(request, response);
			}else if(m.equals("content")) {
				content(request, response);
			}else if(m.equals("update")) {
				update(request, response);
			}else if(m.equals("modified")) {
				modified(request, response);
			}else if(m.equals("write")) { 
				write(request, response);
			}else if(m.equals("insert")) {
				insert(request, response);
			}else if(m.equals("delete")) {
				delete(request, response);
			}else if(m.equals("reply")) {
				reply(request, response);
			}else if(m.equals("rereply")) {
				rereply(request, response);
			}else{
				home(request, response); 
			}
		}else {
			home(request, response);				
		}
	}
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m  = (Member)session.getAttribute("loginPassUser");

		String email = m.getEmail();
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listM(email);
		
		request.setAttribute("list", list);
		//테스트
		//for(Board dto: list) System.out.println(dto.getContent());
		
		String view = "home.jsp";		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m  = (Member)session.getAttribute("loginPassUser");

		String email = m.getEmail();
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listA(email);
		
		request.setAttribute("list", list);
		//테스트
		//for(Board dto: list) System.out.println(dto.getContent());
		
		String view = "list.jsp";		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		
		if(email != null) email = email.trim();
		if(pwd != null) pwd = pwd.trim();
		
		LoginService service = LoginService.getInstance();
		int rCode = service.checkMember(email, pwd);
		request.setAttribute("rCode", rCode);
		
		if(rCode == LoginCase.PASS) {
			HttpSession session = request.getSession();
			
			Member m = service.getMemberS(email);
			session.setAttribute("loginPassUser", m);
			session.setAttribute("email", email);
		}
		
		String view = "login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void register(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{		
		String name = request.getParameter("name");
		String email =request.getParameter("email");
		String pwd = request.getParameter("pwd");
		System.out.println(name+"  "+email+"  "+pwd);
		
		Member mem = new Member(-1, name, email, pwd, null);
		
		LoginService service = LoginService.getInstance();
		int rCode = service.register(mem, email);
		
		request.setAttribute("rCode", rCode);
		
		if(rCode == LoginCase.PASS) {
			HttpSession session = request.getSession();
			Member m = service.getMemberS(email);
			session.setAttribute("loginPassUser", m);
		}
		String view = "register.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session.removeAttribute("loginPassUser");
		session.invalidate();
		
		String view = "../index.jsp";
		response.sendRedirect(view);
	}
	private void content(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m  = (Member)session.getAttribute("loginPassUser");

		String email = m.getEmail();
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listM(email);
		
		request.setAttribute("list", list);
		//테스트
		//for(Board dto: list) System.out.println(dto.getContent());
		if(list.size() == 0) {
			response.sendRedirect("board.do?m=home");
		}else {
			String view = "content.jsp";		
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	private void write(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String view = "write.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		File fSaveDir = new File(FileSet.FILE_DIR);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		MultipartRequest mr = new MultipartRequest(request,
                FileSet.FILE_DIR,
                1*1024*1024,
                "utf-8",
                new DefaultFileRenamePolicy());
		
		long fsize = 0L;
		
		String name = mr.getParameter("name");
		name = new String(name.getBytes("8859_1"),"utf-8"); 
		String email = mr.getParameter("email");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String fname = mr.getFilesystemName("fname");
		String ofname =mr.getOriginalFileName("fname");
		
		if(fname!=null) {	
			fSaveDir = new File(FileSet.FILE_DIR, fname);
			fsize = fSaveDir.length();
			
			int test = fname.lastIndexOf(".");
			String test1 = fname.substring(test+1);
			System.out.println(test1);
		
			BoardService service = BoardService.getInstance();
			int fil = service.fileFilter(test1);
			System.out.println(fil);
			
			if(fil == PASS) {
				Board b = new Board(-1, name, email, subject, content, null, fname, ofname, fsize);
				service.write(b);
				String view = "board.do";
				response.sendRedirect(view);
			}else{
				String view = "../file/check.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}		
		}else{
			fname = null;
			ofname=null;
			
			Board b = new Board(-1, name, email, subject, content, null, fname, ofname, fsize);
			BoardService service = BoardService.getInstance();
			service.write(b);
			String view = "board.do";
			response.sendRedirect(view);
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String seqStr = request.getParameter("seq");
		String fname = request.getParameter("fname");
		
		if(fname != null) fname = fname.trim();
		File f = new File(FileSet.FILE_DIR, fname);
		if(f.exists()) f.delete();
		
		long seq = -1;
		if(seqStr != null) {
			seqStr = seqStr.trim();
			try {
				seq = Long.parseLong(seqStr);
				BoardService service = BoardService.getInstance();
				ReplyService serviceR = ReplyService.getInstance();
				service.delete(seq);
				serviceR.b_reply_del(seq);
			}catch(NumberFormatException ne) {}
		
		}
		System.out.println(seq);
		
		String view = "board.do?m=content";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);		
	}
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String seqStr = request.getParameter("seq");
		long seq = -1;
		if(seqStr!=null) {
			seqStr = seqStr.trim();
			try {
				seq = Long.parseLong(seqStr);
				BoardService service = BoardService.getInstance();
				Board b = service.update(seq);
				request.setAttribute("b", b);
				System.out.println("b 전송");
			}catch(NumberFormatException ne) {}
		}else {
			System.out.println("seqStr 안넘어옴");
		}
		
		String view = "update.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void modified(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		File fSaveDir = new File(FileSet.FILE_DIR);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		MultipartRequest mr = new MultipartRequest(request,
                FileSet.FILE_DIR,
                1*1024*1024,
                "utf-8",
                new DefaultFileRenamePolicy());
		
		long fsize = 0L;
		long seq = -1;
		String seqStr = mr.getParameter("seq");
		if(seqStr!=null) {
			seqStr = seqStr.trim();
			try {
				seq = Long.parseLong(seqStr);	
				System.out.println(seq);
			}catch(NumberFormatException ne) {}
		}else {
			System.out.println("seqStr 안넘어옴");
		}
		BoardService service = BoardService.getInstance();
			
		String email = mr.getParameter("email");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String fname = mr.getFilesystemName("fname");
		String ofname =mr.getOriginalFileName("fname");
		
		System.out.println(content+subject+fname);
		
		Board checkB = service.getBoard(seq);
		String checkFname = checkB.getFname(); //수정 전 파일네임
		if(checkFname!=null) {
			if(fname!=null) {	//기존에 있는 이미지 수정
				File f = new File(FileSet.FILE_DIR, checkFname); 
				if(f.exists()) f.delete(); //기존의 이미지 파일 삭제
				
				fSaveDir = new File(FileSet.FILE_DIR, fname);
				fsize = fSaveDir.length(); 
				
				int test = fname.lastIndexOf(".");
				String test1 = fname.substring(test+1);
				System.out.println(test1);		
				
				int fil = service.fileFilter(test1);
				System.out.println(fil);
				
				if(fil == PASS) {
					Board b = new Board(seq, null, email, subject, content, null, fname, ofname, fsize);
					System.out.println(seq+"null"+ email+ subject+ content+ "null"+ fname+ ofname+ fsize);
					service.modified(b);
					String view = "board.do?m=content&email=";
					response.sendRedirect(view+email);
				}else{
					String view = "board.do?m=content&email=";
					response.sendRedirect(view+email);
				}		
			}else{ // 기존 이미지 유지
				fname = null;
				ofname = null;
				
				Board b = new Board(seq, null, email, subject, content, null, fname, ofname, fsize);
				service.modified(b);
				String view = "board.do?m=content&email=";
				response.sendRedirect(view+email);
			}
		}else{ // 기존 이미지 x 수정 이미지 x
			fname = null;
			ofname= null;
			
			Board b = new Board(seq, null, email, subject, content, null, fname, ofname, fsize);
			service.modified(b);
			String view = "board.do?m=content&email=";
			response.sendRedirect(view+email);
		}
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	long seq = -1;
    	String seqStr = request.getParameter("seq");
    	if(seqStr != null){
    		seqStr = seqStr.trim();
    		if(seqStr.length() != 0){
    			try{
    				seq = Long.parseLong(seqStr);
    				BoardService service = BoardService.getInstance();
    				Board b = service.getBoard(seq);
    				request.setAttribute("b", b);
    			}catch(NumberFormatException ne){
    			}
    		}
    	}
    	ReplyService serviceR = ReplyService.getInstance();
		ArrayList<Reply> listR =serviceR.listR(seq);
		ArrayList<Reply> listRR =serviceR.listRR(seq);
		if(listR!=null) {
			request.setAttribute("listR", listR);
			System.out.println("listR 넘어감");
			for(Reply re: listR) System.out.println(re.getContent());
		}else {
			System.out.println("listR 안넘어감");
		}
		if(listRR!=null) {
			request.setAttribute("listRR", listRR);
			System.out.println("listRR 넘어감");
			for(Reply re: listRR) System.out.println(re.getContent());
		}else {
			System.out.println("listRR 안넘어감");
		}
		
    	String view = "detail.jsp";
    	RequestDispatcher rd = request.getRequestDispatcher(view);
    	rd.forward(request, response);
	}
	private void reply(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		long seq = -1;
    	String seqStr = request.getParameter("seq");
    	String comment = request.getParameter("comment");
    	String name = request.getParameter("name");
    	String pwd = request.getParameter("pwd");
    	if(seqStr != null){
    		seqStr = seqStr.trim();
    		if(seqStr.length() != 0){
    			try{
    				seq = Long.parseLong(seqStr);
    			}catch(NumberFormatException ne){
    			}
    		}
    	}
    	ReplyService serviceR = ReplyService.getInstance();
		int levN =serviceR.levN(seq);
		System.out.println(levN);
		if(levN != -1) {
			levN++;
			Reply reply = new Reply(-1, name, pwd, comment, null, levN, 0, seq);
			serviceR.in_reply(reply);
		}else {
			System.out.println("-1 반환됨");
		}
		String view = "board.do?m=detail&seq=";
		response.sendRedirect(view+seq);
	}
	private void rereply(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		long seq = -1;
		long bseq = -1;
		String seqStr = request.getParameter("seq");
    	String bseqStr = request.getParameter("bseq");
    	String comment = request.getParameter("comment");
    	String name = request.getParameter("name");
    	String pwd = request.getParameter("pwd");
    	if(seqStr != null){
    		seqStr = seqStr.trim();
    		bseqStr = bseqStr.trim();
    		if(seqStr.length() != 0){
    			try{
    				seq = Long.parseLong(seqStr);
    				bseq = Long.parseLong(bseqStr);
    			}catch(NumberFormatException ne){
    			}
    		}
    	}
    	ReplyService serviceR = ReplyService.getInstance();
		int levN =serviceR.get_lev(seq, bseq);
		int sun = serviceR.get_sun(levN, bseq);
		System.out.println(seq+"  "+bseq+"rere"+levN+" "+sun);
		if(sun == 1) {
			Reply reply = new Reply(-1, name, pwd, comment, null, levN, sun, bseq);
			serviceR.in_reply(reply);
		}else if(sun>1){
			sun++;
			Reply reply = new Reply(-1, name, pwd, comment, null, levN, sun, bseq);
			serviceR.in_reply(reply);
		}else {		
			System.out.println("-1 반환됨");
		}
    	
    	//System.out.println(seq+name+pwd+comment);
    	
		
		String view = "board.do?m=detail&seq=";
		response.sendRedirect(view+bseq);
	}
}
