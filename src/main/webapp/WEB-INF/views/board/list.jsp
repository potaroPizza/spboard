<%@page import="com.mymvc.common.PagingVO"%>
<%@page import="com.mymvc.board.model.BoardVo"%>
<%@page import="com.mymvc.board.model.BoardService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	// 뷰페이지
	
	//String condition = request.getParameter("searchCondition");
	//String keyword = request.getParameter("searchKeyword");
	
	//3. request에서 결과 읽어와서 화면 처리
	//List<BoardVo> list = (List<BoardVo>)request.getAttribute("list");
	//PagingVO pageVo = (PagingVO)request.getAttribute("pageVo");
	//int[] rCnt = (int[])request.getAttribute("rCnt");
	
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//if(keyword==null || keyword.isEmpty()) {
	//	keyword = "";
	//}
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>자유게시판 글 목록 - 허브몰</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/clear.css" />
<link rel="stylesheet" type="text/css" href="../css/formLayout.css" />
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">	
	$(function(){
		$('.divList table.box2 tbody').find('tr').hover(function(){
			$(this).css("background", "lightblue");
		}, function(){
			$(this).css("background", "");
		});
	});
</script>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }	
</style>	
</head>	
<body>
<h2>자유게시판</h2>
<div class="divList">
<table class="box2"
	 	summary="기본 게시판에 관한 표로써, 번호, 제목, 작성자, 작성일, 조회수에 대한 정보를 제공합니다.">
	<caption>기본 게시판</caption>
	<c:if test="${!empty param.searchKeyword}">
		<p>검색어 : ${param.searchKeyword}, ${pageVo.totalRecord}건 검색되었습니다.</p>
	</c:if>
	<colgroup>
		<col style="width:10%;" />
		<col style="width:50%;" />
		<col style="width:15%;" />
		<col style="width:15%;" />
		<col style="width:10%;" />		
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">번호</th>
	    <th scope="col">제목</th>
	    <th scope="col">작성자</th>
	    <th scope="col">작성일</th>
	    <th scope="col">조회수</th>
	  </tr>
	</thead> 
	<tbody>
		<c:if test="${empty list}">
		  	<tr>
		  		<td colspan="5" class="align_center">해당 글이 존재하지 않습니다.</td>
		  	</tr>
		</c:if>
	  	
	  	<!--게시판 내용 반복문 시작  -->
		<c:if test="${!empty list}">
			<c:set var="num" value="${pageVo.num}"/>
			<c:set var="curPos" value="${pageVo.curPos}"/>
		  	
		  	<c:forEach var="i" begin="1" end="${pageVo.pageSize}" >
		  		<c:if test="${num >= 1}">
		  			<c:set var="num" value="${num-1}" />
		  			<c:set var="vo" value="${list[curPos]}" />
		  			<c:set var="curPos" value="${curPos+1}" />
						<tr style="text-align:center">
							<td>${vo.no}</td>
							<td style="text-align:left">
								<a href="<c:url value='/board/countUpdate.do?no=${vo.no}' />">
								${vo.title}</a>
							</td>
							<td>${vo.name}</td>
							<td>
								<fmt:formatDate value="${vo.regdate}"
									pattern="yyyy-MM-dd"/>
							</td>
							<td>${vo.readcount}</td>		
						</tr>
				</c:if>
			</c:forEach>
	  <!--반복처리 끝  -->
	 	</c:if>
	 </tbody>
</table>	   
</div>
<div class="divPage">
	<!-- 페이지 번호 추가 -->	
	
	<!-- 블록을 이전으로 전환하는 아이콘 '<' -->	
	<c:if test="${pageVo.firstPage > 1}">
		<a href="<c:url value='/board/list.do?currentPage=${pageVo.firstPage-1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}' />">
			<img src="../images/first.JPG">
		</a>				
	</c:if>
	<!-- [1][2][3][4][5][6][7][8][9][10] -->
	<c:forEach var="i" begin="${pageVo.firstPage}" end="${pageVo.lastPage}">
		<c:if test="${i<pageVo.totalPage}">
			<c:if test="${i==pageVo.currentPage}">
				<span style="color:blue; font-weight:bold;">${i}</span>
			</c:if>
			<c:if test="${i!=pageVo.currentPage}">
				<a href="<c:url value='/board/list.do?currentPage=${i}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}' />">
					[${i}]
				</a>
			</c:if>
		</c:if>
			<!-- if를 여기다 쓰고 조건을 같을때==로 해도 무방하다. -->
	</c:forEach>
	
	<!-- 블록을 다음으로 전환하는 아이콘 '>' -->	
	<c:if test="">
		<a href="<c:url value='/board/list.do?currentPage=${pageVo.lastPage+1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}' />">
			<img src="../images/last.JPG">
		</a>				
	</c:if>
	
	<!--  페이지 번호 끝 -->
</div>
<div class="divSearch">
	<!-- 검색의 결과는 현재페이지와 유사하므로 본인에게 뿌려서, 결과값을 새로 뿌려준다. -->
   	<form name="frmSearch" method="post" 
   		action="<c:url value='/board/list.do' />">
   		<!-- 검색시 설정한 컨디션 설정하기 -->
   		<!-- 전체 검색시 받아온 파라미터가 따로 없어서, condition이 null이 되어 에러가 발생하는데
   				이를 방지하기 위해서 if(condition.equals("title"))가 아닌,
   				if(title.equals("condition"))로 바꿔주면 
   				condition이 null이더라도, null에러가 발생하지 않는다(아주 중요함!). -->
        <select name="searchCondition">
            <option value="title" 
            	<c:if test="${param.searchCondition == 'title' }">
            		selected = "selected"
            	</c:if>
            >제목</option>
            <option value="content"
            	<c:if test="${param.searchCondition == 'content' }">
            		selected = "selected"
            	</c:if>
            >내용</option>
            <option value="name"
            	<c:if test="${param.searchCondition == 'name' }">
            		selected = "selected"
            	</c:if>
            >작성자</option>
        </select>   
        <!-- 검색시 검색한 키워드 뿌려주기 -->
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="${param.searchKeyword}">	 
		<input type="submit" value="검색">
    </form>
</div>

<div class="divBtn">
    <a href="<c:url value='/board/write.do' />" >글쓰기</a>
</div>

</body>
</html>

