<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용 보기</title>
</head>
<body>
	<h2>글내용 보기</h2>
	<table border="1" cellspacing="0" cellpadding="0" width="600">
		<tr>
			<td>번호</td>
			<td>${boardDto.bnum}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${boardDto.bhit}</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${boardDto.bid}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${boardDto.bname}</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td>${boardDto.btitle}</td>
		</tr>
		<tr>
			<td height="100" valign="top">글내용</td>
			<td valign="top">${boardDto.bcontent}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${boardDto.bdate}</td>
		</tr>
		<tr>
			<td colspan="2">
				<%
					String idCheck = request.getAttribute("idCheck").toString(); // conroller 에서 model 로 넘겨준 값이 request 로 뺄수있고 Attribute 로 빼야 함
					if(idCheck.equals("1")){
				%>
				<input type="button" value="수정" onclick="javascript:window.location.href='modify?bnum=${boardDto.bnum}'">
				<input type="button" value="삭제" onclick="javascript:window.location.href='deleteview?bnum=${boardDto.bnum}'">
				<%
					}
			 	%>
			 	<input type="button" value="목록" onclick="javascript:window.location.href='list'">
			</td>
		</tr>
		
	</table>
</body>
</html>