<%@page import="com.mymvc.board.model.BoardVo"%>
<%@page import="java.sql.SQLException"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
/* 	// 뷰 페이지
	String no = request.getParameter("no");
	BoardVo vo = (BoardVo)request.getAttribute("boardVo");
	
	//3. request에서 결과 받아와서 화면 처리
	String content = vo.getContent();
	String email = vo.getEmail();
	
	// 내용 null일때, 공백으로 바꿔주기
	if(content==null || content.isEmpty()) {
		content = "";
	}
	
	// 이메일 null일때, 공백으로 바꿔주기
	if(email==null || email.isEmpty()) {
		email = "";
	} */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>자유게시판 글 수정 - 허브몰</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/clear.css" />
<link rel="stylesheet" type="text/css" href="../css/formLayout.css" />
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />

<script type="text/javascript">

</script>

</head>
<body>
<div class="divForm">
<form name="frmEdit" method="post"
	action="<c:url value='/board/edit_ok.do' />"> 
    <fieldset>
    <!-- 중요한 정보는 보안을 위해 input type=hidden에 넣어서 보내준다. -->
    <input type="hidden" name="no" value="${param.no}"/>
	<legend>글수정</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" 
            	value="${boardVo.title}"/>
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" 
            	value="${boardVo.name}"/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd"/>
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" 
            	value="${boardVo.email}"/>
        </div>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40">${boardVo.content}</textarea>
        </div>
        <div class="center">
            <input type = "submit" value="수정"/>
            <input type = "Button" value="취소" 
            	onclick="location.href	='<c:url value='/board/detail.do?no=${param.no}' />'" />
            <input type = "Button" value="글목록" 
            	onclick="location.href	='<c:url value='/board/list.do' />'" />         
        </div>
	</fieldset>
</form>    
</div>

</body>
</html>