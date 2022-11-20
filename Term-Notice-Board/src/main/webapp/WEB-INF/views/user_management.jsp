<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.wp.board.*, oracle.sql.*, oracle.jdbc.driver.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 관리</title>
</head>
<body>
	<table align = "center">
				<thead>
					<tr><td>유저 아이디</td><td>비밀번호</td><td>닉네임</td><td>이메일</td><td>제어</td></tr>
				</thead>
				<tbody>
<%
	List<UsersDO> userList = (List<UsersDO>)request.getAttribute("user_list");
	if(userList != null){
		for(UsersDO user : userList){
			pageContext.setAttribute("user", user);
%>
					<tr>
						<td>${user.user_id}</td>
						<td>${user.user_pw}</td>
						<td>${user.nickname}</td>
						<td>${user.email}</td>
<c:choose>
	<c:when test="${user.admin eq false}">
						<td>
							<input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/board?action=user_delete&user_id=${user.user_id}'" />
						</td>
	</c:when>
</c:choose>	
					</tr>			
<%
		}
	} else {
%>
					<tr>
						<td colspan="5">등록된 유저 정보가 없습니다.</td>
					</tr>
<%
	}
%>
				</tbody>
			
			</table>
</body>
</html>