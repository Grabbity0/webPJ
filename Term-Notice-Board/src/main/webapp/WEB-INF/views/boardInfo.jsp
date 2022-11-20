<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.wp.board.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%
	BoardDO board = (BoardDO)request.getAttribute("board");
%>
	<article>		
			<h1>${board.board_subj}</h1>
			
			<p>${board.board_cont}</p>
			<p>${board.user_id}</p>
			<p>${board.writeTime}</p>
			<div>
				<p>	
				<c:choose>
				<c:when test="${board.user_id eq sessionScope.user_id}">
				<input type="button" value="게시판 수정" onclick="location.href='${pageContext.request.contextPath}/board?action=board_modify_form&board_id=${board.board_id}'" >
				<input type="button" value="게시판 삭제" onclick="location.href='${pageContext.request.contextPath}/board?action=board_delete&board_id=${board.board_id}'">
				</c:when>
				<c:when test="${sessionScope.admin eq true}">
				<input type="button" value="게시판 수정" onclick="location.href='${pageContext.request.contextPath}/board?action=board_modify_form&board_id=${board.board_id}'" >
				<input type="button" value="게시판 삭제" onclick="location.href='${pageContext.request.contextPath}/board?action=board_delete&board_id=${board.board_id}'">
				</c:when>
				</c:choose>
				<input type="button" value="메인으로" onclick="window.history.go(-1);" />
				<p>
			</div>
		
	</article>
</body>
</html>