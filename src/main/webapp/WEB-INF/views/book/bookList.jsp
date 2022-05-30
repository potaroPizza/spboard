<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- http://localhost:9090/springexam/book/bookList.do -->

<!DOCTYPE html>
<html>
<head>
<meta charset="">
<title></title>
</head>
<body>
	<h1>책 목록</h1>
	<table border="1" style="width:500px; border-collapse:collapse;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>가격</th>
			<th>출판사</th>
			<th>등록일</th>
		</tr>
		<c:if test="${empty list}">
		  	<tr>
		  		<td colspan="5" class="align_center">해당 글이 존재하지 않습니다.</td>
		  	</tr>
		</c:if>
		<!-- 반복구간 시작 -->
		<c:if test="${!empty list}">
			<c:set var="num" value="${pageVo.num}"/>
			<c:set var="curPos" value="${pageVo.curPos}"/>
			
			<c:forEach var="i" begin="1" end="${pageVo.pageSize}">
				<c:if test="${num >= 1}">
					<c:set var="num" value="${num-1}" />
		  			<c:set var="dto" value="${list[curPos]}" />
		  			<c:set var="curPos" value="${curPos+1}" />
		  
					<tr>
						<td>${dto.no}</td>
						<td><a href="<c:url value='/book/bookDetail.do?no=${dto.no}'/> ">
							${dto.title}</a></td>
						<td style="text-align:right">
							${dto.price}</td>
						<td>${dto.publisher}</td>
						<td>${dto.joindate}</td>
					</tr>
				</c:if>
			</c:forEach>
		<!-- 반복구간 끝 -->
		</c:if>
	</table>
	<br>
	
    <div>
    	<!-- 페이지 -->
    	<c:if test="${pageVo.firstPage > 1}">
		<a href="<c:url value='/book/bookList.do?currentPage=${pageVo.firstPage-1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}' />">
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
					<a href="<c:url value='/book/bookList.do?currentPage=${i}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}' />">
						[${i}]
					</a>
				</c:if>
			</c:if>
				<!-- if를 여기다 쓰고 조건을 같을때==로 해도 무방하다. -->
		</c:forEach>
		
		<!-- 블록을 다음으로 전환하는 아이콘 '>' -->	
		<c:if test="">
			<a href="<c:url value='/book/bookList.do?currentPage=${pageVo.lastPage+1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}' />">
				<img src="../images/last.JPG">
			</a>				
		</c:if>
    </div>
	
	<br>
	   	<form name="frm" method="post"
	   		action="<c:url value='/book/bookList.do'/> ">
        <select name="searchCondition">
            <option value="title" 
            	<c:if test="${param.searchCondition == 'title' }">
            		selected = "selected"
            	</c:if>
            >제목</option>
            <option value="publisher" 
            	<c:if test="${param.searchCondition == 'publisher' }">
            		selected = "selected"
            	</c:if>
            >출판사</option>
        </select>   
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="${param.searchKeyword}">   
		<input type="submit" value="검색">
    </form>
	
	<a href="<c:url value='/book/bookWrite.do'/> ">책 등록</a>
</body>
</html>