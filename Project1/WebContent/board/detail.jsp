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
      <style>
         .blog-container {
         position: absolute;
         top: 50%;
         left: 50%;
         -webkit-transform: translate(-50%, -50%);
             -ms-transform: translate(-50%, -50%);
                 transform: translate(-50%, -50%);
         margin: 0 auto;
      }
      .blog-container.is-active {
         position:relative;
         top:auto;
         left:auto;
         -webkit-transform: translate(0, 0);
             -ms-transform: translate(0, 0);
                 transform: translate(0, 0);
         margin: 100px auto;
      }
      .blog-cover {
         height: 30rem;
      }
      .comment-comment {
         display: none;
      }
      .comment-comment.is-active {
         display: block;
      }
      .tf_reply.rereply {
      	height: 50px;
      }
      </style>
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
      <svg display="none" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
         <defs>
            <symbol id="icon-bubble" viewBox="0 0 1024 1024">
               <title>bubble</title>
               <path class="path1" d="M512 224c8.832 0 16 7.168 16 16s-7.2 16-16 16c-170.464 0-320 89.728-320 192 0 8.832-7.168 16-16 16s-16-7.168-16-16c0-121.408 161.184-224 352-224zM512 64c-282.784 0-512 171.936-512 384 0 132.064 88.928 248.512 224.256 317.632 0 0.864-0.256 1.44-0.256 2.368 0 57.376-42.848 119.136-61.696 151.552 0.032 0 0.064 0 0.064 0-1.504 3.52-2.368 7.392-2.368 11.456 0 16 12.96 28.992 28.992 28.992 3.008 0 8.288-0.8 8.16-0.448 100-16.384 194.208-108.256 216.096-134.88 31.968 4.704 64.928 7.328 98.752 7.328 282.72 0 512-171.936 512-384s-229.248-384-512-384zM512 768c-29.344 0-59.456-2.24-89.472-6.624-3.104-0.512-6.208-0.672-9.28-0.672-19.008 0-37.216 8.448-49.472 23.36-13.696 16.672-52.672 53.888-98.72 81.248 12.48-28.64 22.24-60.736 22.912-93.824 0.192-2.048 0.288-4.128 0.288-5.888 0-24.064-13.472-46.048-34.88-56.992-118.592-60.544-189.376-157.984-189.376-260.608 0-176.448 200.96-320 448-320 246.976 0 448 143.552 448 320s-200.992 320-448 320z"></path>
            </symbol>
         </defs>
      </svg>
      <c:set value="${b}" var="board" />
      <div class="blog-container">
         <div class="blog-header">
            <c:if test="${empty b.fname}">
               <div class="blog-cover" style="background-image:url(../img/background.jpg);"></div>
            </c:if>
            <c:if test="${!empty b.fname}">
               <div class="blog-cover" style="background-image:url(../uploadImg/<c:out value="${b.fname}"/>);">
         </div>
         </c:if>
      </div>
      <div class="blog-body">
         <div class="blog-title">
            <h1>
               <c:out value="${b.subject}"/>
            </h1>
         </div>
         <div class="blog-function">
            <a href="board.do?m=update&seq=<c:out value="${b.seq}"/>">수정</a>
            <c:if test ="${loginPassUser.email == b.email}">
            <a href="board.do?m=delete&seq=<c:out value="${b.seq}"/>&fname=<c:out value="${b.fname}"/>"> 삭제</a>
            </c:if>
         </div>
         <div class="blog-summary">
            <p><c:out value="${b.content}"/></p>
         </div>
      </div>
      <div class="blog-footer">
         <ul>
            <li class="published-date">
               <c:out value="${b.rdate}"/>
            </li>
            <li class="comments">
               <a href="#" class="comments-target">
                  <svg class="icon-bubble">
                     <use xlink:href="#icon-bubble"></use>
                  </svg>
                  <span class="numero"><!-- 4 --></span>
               </a>
            </li>
         </ul>
      </div>
      <div class="blog-comments">
      	<%-- <c:set value="${listR}" var="reply"/>
      	<p class="comments-count">총 <span class="comments-count-num">${reply.lev}</span>개의 댓글</p> --%>
        <ul id="comments-list" class="comments-list">
            <c:if test="${empty listR}">
            <li>
              <div class="comment-main-level">
                 <div class="comment-box">
                    <div class="comment-content" style="text-align:center;">
                       댓글이 없습니다.
                    </div>
                 </div>
              </div>
           </li>
		   </c:if>
		   <c:forEach items="${listRR}" var="reply">
           <li>
           	<c:choose>
           	  <c:when test="${reply.sunbun==0}"> <!-- & board.seq == reply.board_seq -->
              <div class="comment-main-level">
                 <div class="comment-box">
                    <div class="comment-head">
                       <div class="comment-name">${reply.name}</div>
                       <span class="posted-time">${reply.rdate}</span>
                       <a href="javascript:void(0)" class="comment-reply">답글</a>
                    </div>
                    <div class="comment-content">
                       ${reply.content}
                    </div>
					<div class="comment-comment">
					   <form method="post" action="board.do?m=rereply&seq=${reply.seq}&bseq=${reply.board_seq}">
					      <fieldset class="fld_reply">
					         <legend class="screen_out">댓글쓰기 폼</legend>
					         <div class="reply_write">
					            <label for="comment" class="lab_write"></label>
					            <textarea id="comment" name="comment" class="tf_reply rereply" placeholder="여러분의 소중한 댓글을 입력해주세요"></textarea>
					         </div>
					         <div class="reply_writer">
					            <div class="writer_info">
					               <span class="info_name">
					               <span class="wrap_info">
					               <input type="text" name="name" id="name" class="inp_info" placeholder="이름" value="">
					               </span>
					               </span>
					               <span class="info_pw">
					               <span class="wrap_info">
					               <input type="password" name="pwd" id="password" class="inp_info" placeholder="비밀번호">
					               </span>
					               </span>
					            </div>
					            <div class="writer_btn">
					               <button type="submit" class="btn_enter" onclick="addComment(this, 255); return false;">입력</button>
					            </div>
					         </div>
					      </fieldset>
					   </form>
					</div>
                 </div>
              </div>
              </c:when>    
              <c:otherwise>       <%-- test="${reply.sunbun!=0}" --%>
	              <%-- c:forEach items="${listRR}" var="rereply"> --%>
	             	 <%-- c:if test="${reply.lev==rereply.lev}"> --%>
			              <ul class="comments-list reply-list">
			                  <li>
			                     <div class="comment-box">
			                        <div class="comment-head">
			                           <div class="comment-name by-author">${reply.name}</div>
			                           <span class="posted-time">${reply.rdate}</span>
			                        </div>
			                        <div class="comment-content">
			                           ${reply.content}
			                        </div>
			                     </div>
			                  </li>
			               </ul>
			         <%-- </c:if> --%>
			     <%-- </c:forEach>  --%>
			 </c:otherwise>   
			 </c:choose>          
           </li>
           </c:forEach>
        </ul>
        <!-- // 댓글 -->
        <form method="post" action="board.do?m=reply&seq=${board.seq}">
        	<fieldset class="fld_reply">
               <legend class="screen_out">댓글쓰기 폼</legend>
               <div class="reply_write">
                  <label for="comment" class="lab_write"></label>
                  <textarea id="comment" name="comment" class="tf_reply" placeholder="여러분의 소중한 댓글을 입력해주세요"></textarea>
               </div>
               <div class="reply_writer">
                  <div class="writer_info">
                     <span class="info_name">
                        <span class="wrap_info">
                        <input type="hidden" valus="">
                        <input type="text" name="name" id="name" class="inp_info" placeholder="이름" value="">
                        </span>
                     </span>
                     <span class="info_pw">
                        <span class="wrap_info">
                        <input type="password" name="pwd" id="password" class="inp_info" placeholder="비밀번호">
                        </span>
                     </span>
                  </div>
                  <div class="writer_btn">
                     <button type="submit" class="btn_enter" onclick="addComment(this, 255); return false;">입력</button>
                  </div>
               </div>
         	</fieldset>
         </form>
      </div>
      </div>
      <script type="text/javascript">
	      const start = document.querySelectorAll(".comments-target");
	      const target = document.querySelectorAll(".blog-comments");
	      const page = document.querySelectorAll(".blog-container");
	      
	      const comment = document.querySelectorAll(".comment-comment");
	      const commentTarget = document.querySelectorAll(".comment-reply")
	      
	      for (let i=0; i<target.length; i++) {
	          start[i].addEventListener("click", function() {
	            target[i].classList.toggle("is-active");
	            page[i].classList.toggle("is-active");
	          });               
	      }
	      
	      for (let j=0; j<commentTarget.length; j++) {
	          commentTarget[j].addEventListener("click", function() {
	            comment[j].classList.toggle("is-active");
	          });
	      }
      </script>
   </body>
</html>

