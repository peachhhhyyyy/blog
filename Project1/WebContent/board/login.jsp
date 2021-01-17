<%@page import="login.mvc.model.LoginCase"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script>
   if(${rCode} == <%=LoginCase.NO_ID%>){
	   alert("존재하지 않는 아이디입니다.");
	   location.href="../";
   }else if(${rCode} == <%=LoginCase.NO_PWD%>){
	   alert("비밀번호가 틀렸습니다.");
	   location.href="../";
   }else{
	   alert("로그인 성공");
	   location.href="board.do";
   }
</script>