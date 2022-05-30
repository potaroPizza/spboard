<%@page import="com.mymvc.board.model.CommentsVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mymvc.board.model.BoardVo"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	// 뷰 페이지
	
	//3. request에 저장된 결과 읽어와서 화면 처리
	//String no = request.getParameter("no");
	BoardVO bdVo = (BoardVO)request.getAttribute("bdVo");
	//List<CommentsVO> cList = (List<CommentsVO>)request.getAttribute("cList");
	
	// 개행처리 가능하게 설정!
	String content = bdVo.getContent();
	if(content!=null && !content.isEmpty()) {
		// 매개변수 1을 매개변수 2로 변환하는 메소드 : replace(문자열1, 문자열2)
		content = content.replace("\r\n", "<br>");
		
		// 글에 내용이 없을때, null이 아닌 공백을 뿌려주는 작업
	} else {
		content = "";
	}
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>자유게시판 상세보기 - 허브몰</title>
<link rel="stylesheet" type="text/css" href="../css/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/clear.css" />
<link rel="stylesheet" type="text/css" href="../css/formLayout.css" />
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm {
		width: 500px;
		}
</style>  
</head>
<body>
	<h2>글 상세보기</h2>
	<div class="divForm">
		<div class="firstDiv">
			<span class="sp1">제목</span> <span>${bdVo.title}</span>
		</div>
		<div>
			<span class="sp1">작성자</span> <span>${bdVo.name}</span>
		</div>
		<div>
			<span class="sp1">등록일</span> <span>${bdVo.regdate}</span>
		</div>
		<div>
			<span class="sp1">조회수</span> <span>${bdVo.readcount}</span>
		</div>
		<div class="lastDiv">			
			<p class="content"><%=content %></p>
		</div>
		<div class="center">
			<a href="<c:url value='/board/edit.do?no=${bdVo.no}' />">수정</a> |
        	<a href="<c:url value='/board/delete.do?no=${bdVo.no}' />">삭제</a> |
        	<a href="<c:url value='/board/list.do' />">목록</a>			
		</div>
	</div>
	<div class="replyList">
<table>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:60%;" />
		<col style="width:20%;" />
	</colgroup>
	<tbody>
		
		<c:if test="${empty cList}">
	  	<tr>
	  		<td colspan="3" class="align_center">댓글이 없습니다.</td>
	  	</tr>
	  	</c:if>
	  	<c:if test="${empty cList}">
	  		<c:forEach var="i" begin="1" end="${fn:length(cList)}">
				<c:set var="cVo" value="${cList[i]}"/>
		  	<tr>
		  		<td>${cVo.name}</td>
		  		<td>>${cVo.content}</td>
		  		<td>>${cVo.regdate}</td>
		  	</tr>
		  	</c:forEach>
		
		</c:if>
	  </tbody>
</table>	   
</div>
	<div class="divForm">
	<form name="frmReply" method="post" 
		action="<c:url value='/board/reply.do' />"> 
    <fieldset>
    <!-- 중요한 정보는 보안을 위해 input type=hidden에 넣어서 보내준다. -->
    <input type="hidden" name="bdNo" value="${param.no}"/>
	<legend>댓글</legend>
		
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" 
            	value=""/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd"/>
        </div>

        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="5" cols="40"></textarea>
        </div>
        <div class="center">
            <input type = "submit" value="확인"/>
        </div>
	</fieldset>
</form>    
</div>
</body>
</html>