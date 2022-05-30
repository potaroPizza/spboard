<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mainstyle.css'/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/clear.css'/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/formLayout.css'/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mystyle.css'/> "/>

<title>자유게시판 글쓰기 - 허브몰</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.6.0.min.js'/> "></script>
<script type="text/javascript">

	$(function(){
		$('#btList').click(function(){
			location.href = "<c:url value='/board/list.do' />";
		});
		
		// 폼을 submit하거나, 등록 버튼을 눌렀을 때.
		$('form[name=frmWrite]').submit(function(){
			$('.infobox').each(function(idx, item){
				// this or item으로 해당태그의 선택자 지정 가능.
				// 각 인풋박스의 값을 잘라내서 확인했을때, 길이가 1보다 작으면 입력하지 않은것!
				// trim()을 사용하는 이유는, 공백을 제거하고 잘라내기 때문에
				// 공백을 값으로 인식하지 못하는 불상사를 방지하는 역할을 하므로, 중요함!
				if($.trim($(this).val()).length<1){	
					alert($(this).prev().text() + "을(를) 입력하세요");
					// or (this).parent().find('label').text()
					
					$(item).focus();
					
					// 이벤트 진행 막고, 반복문 탈출하는거 매우 중요하므로 꼭 외우자!
					event.preventDefault();	// 잘못된경우이므로, 이벤트가 진행되는걸 막아야해!
					return false;			// 그리고, 반복문을 탈출해야해!
										// 서블릿이 진행되는 service()를 탈출한다는 의미이다.
				}
			});
		});
		
	});
</script>

</head>
<body>
<div class="divForm">
<form name="frmWrite" method="post" 
	action="<c:url value='/board/write.do' />" >
 <fieldset>
	<legend>글쓰기</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" class="infobox"/>
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" class="infobox"/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd"  class="infobox"/>
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" />
        </div>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40"></textarea>
        </div>
        <div class="center">
            <input type = "submit" value="등록"/>
            <input type = "Button" value="글목록" id="btList"/>         
        </div>
    </fieldset>
</form>
</div>   

              
</body>
</html>