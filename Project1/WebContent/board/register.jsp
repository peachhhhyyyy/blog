<%@page import="login.mvc.model.LoginCase"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script>
   if(${rCode} == <%=LoginCase.REG_ID%>){
	   alert("아이디가 존재합니다.");
	   location.href="../";
   }else{
	   alert("회원가입이 완료됐습니다.");
	   location.href="board.do";
   }   
</script>