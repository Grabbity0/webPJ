<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.wp.board.*, oracle.sql.*, oracle.jdbc.driver.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<style>
	table{
		board-collapse: collapse;
	}
	td {
		padding: 5px;
		border: 1px solid black;
	}
	a {
		text-decoration: none;
	}
	
</style>
</head>
<body>

<c:choose>
<c:when test="${sessionScope.user_id eq null}">
<input type="button" value="로그인" onclick="location.href='${pageContext.request.contextPath}/board?action=login_form'" />
<input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/board?action=user_register_form'" />
</c:when>
<c:otherwise>
<div>
	<h3>${sessionScope.nickname} 님 환영합니다.</h3>
 	<input type="button" value="회원정보" onclick="location.href='${pageContext.request.contextPath}/board?action=user_form'" />
	<input type="button" value="로그아웃" onclick="location.href='${pageContext.request.contextPath}/board?action=logout'" />
</div>
<div>

<table align = "center">
				<thead>
					<tr><td>Board_numbering</td><td>제목</td><td>작성시간</td><td>작성자</td></tr>
				</thead>
				<tbody>
<%
	List<BoardDO> boardList = (List<BoardDO>)request.getAttribute("board_list");
	if(boardList != null){
		for(BoardDO board : boardList){
			pageContext.setAttribute("board", board);
%>
					<tr>
						<td>${board.board_id}</td>
						<td width="300"><a href="${pageContext.request.contextPath}/board?action=board_form&board_id=${board.board_id}" >${board.board_subj} </a></td>
						<td>${board.writeTime}</td>
						<td>${board.user_id}</td>
					</tr>				
<%
		}
	} else {
%>
					<tr>
						<td colspan="5">등록된 게시물이 없습니다.</td>
					</tr>
<%
	}
%>
				</tbody>
			
			</table>
	<input type="button" value="게시글 작성" onclick="location.href='${pageContext.request.contextPath}/board?action=board_modify_form'" />

</div>
</c:otherwise>
</c:choose>

</body>
</html>