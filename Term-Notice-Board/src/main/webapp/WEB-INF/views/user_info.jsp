<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.wp.board.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 정보</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/board?action=user_update"
			method="POST">
		<h1>${sessionScope.user_id} 님의 정보</h1>
		
		<table>
      	<tr>
      	<td><label for="ID">ID: </label></td>
      	<td><input type="text" placeholder="ID" name="user_id" value="${sessionScope.user_pw}" readonly /></td>
      	</tr>
      	<tr>
      	<td><label for="PassWord">Password: </label></td>
     	 <td><input type="password" placeholder="Password" name="user_pw" value="${sessionScope.user_pw}" readonly /></td>
      	</tr>
     	 <tr>
     	 <td><label for="NickName">Nickname: </label></td>
      	<td><input type="text" placeholder="Nickname" name="nickname" value="${sessionScope.nickname}" required /></td>
      	</tr>
      	<tr>
      <td><label for="Email">Email: </label></td>
      <td><input type="text" placeholder="Email" name="email" value="${sessionScope.email}" required /></td>
      </tr>
 		</table>
 	 	<hr>
      	<input type="submit" value="수정" />
      	<c:choose>
   		<c:when test="${sessionScope.admin eq true}">
    		<input type="button" value="사용자 관리" onclick="location.href='${pageContext.request.contextPath}/board?action=user_management'" />
   		</c:when>
   		</c:choose>
      	<input type="button" value="나가기" onclick="window.history.go(-1);"/>
   </form>
   
   
</body>
</html>