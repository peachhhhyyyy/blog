<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Welcome to BitBlog</title>
	<link rel="stylesheet" media="all" href="../css/list.css" />
	<link rel="icon" href="../img/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
</head>
<body>
	<div class="blog-write">
		<a href="board.do?m=write" class="blog-write__text">새 글 작성</a>
	</div>
	<div class="blog-list">
		<a href="board.do?m=home" class="blog-list__text">내 일기</a>
	</div>
	<div class="blog-member">
		<c:if test="${!empty loginPassUser}">
		      <span class="blog-member__text">${loginPassUser.name} 님</span> 
		</c:if>
		<c:choose>
			<c:when test="${empty loginPassUser}">
				<a href="board.do" class="blog-member__login">로그인</a>
			</c:when>
		    <c:otherwise>
				<a href="board.do?m=logout" class="blog-member__logout">로그아웃</a>
		    </c:otherwise>
		</c:choose>
	</div>
	<div class="card-container">
		<c:forEach items="${list}" var="board">
			<a class="card-item" href="board.do?m=detail&seq=${board.seq}">
			  <div class="card">
			    <div class="side front">
			      	<c:if test="${empty board.fname}">
		        	<div class="img" style="background-image:url(../img/background.jpg);"></div>
			        </c:if>
			        <c:if test="${!empty board.fname}">
				    <div class="img" style="background-image:url(../uploadImg/${board.fname});"></div>	
				    </c:if>	      
			      <div class="info">
			        <h2 class="title">${board.subject}</h2>
			        <p class="author">${board.name}</p>
			        <p class="content">${board.content}</p>
			      </div>
			    </div>
			  </div>
			</a>
		</c:forEach>
	</div>
</body>
</html>