<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Welcome to BitBlog</title>
	<link rel="stylesheet" media="all" href="../css/main.css" />
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
	<div class="blog-slider">
	<div class="blog-slider__wrp swiper-wrapper">
	    <c:if test="${empty list}">
	       <p class="blog-slider__empty">
	          게시글이 없습니다.
	       </p>
	    </c:if>
	
		<c:forEach items="${list}" var="board">
		    <div class="blog-slider__item swiper-slide">
		      
		      	<div class="blog-slider__img">
			      	<c:if test="${empty board.fname}">
		        	<img src="../img/background.jpg" alt="">
			        </c:if>
				    <img src="../uploadImg/${board.fname}" alt="">
		        </div>
		      <div class="blog-slider__content">
		      	<span class="blog-slider__name">${board.name}</span>
		        <span class="blog-slider__code">${board.rdate}</span>
		        <div class="blog-slider__title">${board.subject}</div>
		        <div class="blog-slider__text">${board.content} </div>
		        <a href="board.do?m=content&seq=${board.seq}&email=${board.email}" class="blog-slider__button">READ MORE</a>	       
	        
		      </div>
		    </div>	  
		</c:forEach>
		
	</div>
	<div class="blog-slider__pagination"></div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/js/swiper.min.js"></script>
	<script>
	var swiper = new Swiper('.blog-slider', {
	    spaceBetween: 30,
	    effect: 'fade',
	    loop: true,
	    mousewheel: {
	      invert: false,
	    },
	    pagination: {
	      el: '.blog-slider__pagination',
	      clickable: true
	    }
	});
	</script>
	<script>
		setTimeout(function(){
			 if (self.name != 'reload') {
		         self.name = 'reload';
		         self.location.reload(true);
		     }
		     else self.name = ''; 
		},5000)
	</script>
</body>
</html>