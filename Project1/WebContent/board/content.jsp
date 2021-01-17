<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Welcome to BitBlog</title>
	<link rel="stylesheet" media="all" href="../css/content.css" />
	<link rel="icon" href="../img/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
</head>
<body>
	<div class="blog-write">
		<a href="board.do?m=write&name=${loginPassUser.name}" class="blog-write__text">새 글 작성</a>
	</div>
	<div class="blog-list">
		<a href="board.do?m=list" class="blog-list__text">리스트</a>
	</div>
	<div class="blog-member">
		<c:if test="${!empty loginPassUser}">
		      <span class="blog-member__text">${loginPassUser.name} 님</span> 
		</c:if>
		<c:choose>
			<c:when test="${empty loginPassUser}">
				<a href="board.do?m=out" class="blog-member__login">로그인</a>
			</c:when>
		    <c:otherwise>
				<a href="board.do?m=logout" class="blog-member__logout">로그아웃</a>
		    </c:otherwise>
		</c:choose>
	</div>
	<c:forEach items="${list}" var="board">		
		<div class="blog-container">
		   <div class="blog-header">
		      <c:if test="${empty board.fname}">
		        	<div class="blog-cover" style="background-image:url(../img/background.jpg);"></div>
			  </c:if>
			  <c:if test="${!empty board.fname}">
		      <div class="blog-cover" style="background-image:url(../uploadImg/${board.fname});"></div> 
		      </c:if>
		   </div>
		   <div class="blog-body">
		      <div class="blog-title">
		         <h1><a href="board.do?m=detail&seq=${board.seq}">${board.subject}</a></h1>
		      </div>
		      <%-- <div class="blog-function">
			     <a href="board.do?m=update&seq=${board.seq}">수정</a>
			     <a href="board.do?m=delete&seq=${board.seq}&fname=${board.fname}" onclick="event();">삭제</a>
			  </div> --%>
		      <div class="blog-summary">
		         <p>${board.content}</p>
		      </div>
		   </div>
		   <div class="blog-footer">
		      <ul>
		         <li class="published-date">${board.rdate}</li>
		      </ul>
		   </div>
		</div>
	</c:forEach>
	
	<script type="text/javascript">
		const start = document.querySelectorAll(".comments-target");
		const target = document.querySelectorAll(".blog-comments");
		
		for (let i=0; i<target.length; i++) {
		    start[i].addEventListener("click", function() {
		      target[i].classList.toggle("is-active");
		    });
		}
		
		function event(){	
			if (confirm("정말 삭제하시겠습니까??") == true){    //확인		
			    document.form.submit();		
			}else{   //취소		
			    return;		
			}
		}
		setTimeout(function(){
			 if (self.name != 'reload') {
		         self.name = 'reload';
		         self.location.reload(true);
		     }else self.name = ''; 
			 }
		,5000)
	</script>
</body>
</html>