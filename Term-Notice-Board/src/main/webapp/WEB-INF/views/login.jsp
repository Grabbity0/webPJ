<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.wp.board.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/board?action=login"
			method="POST">
 <table>
      <tr>
      <td><label for="ID">ID: </label></td>
      <td><input type="text" placeholder="ID" name="user_id" required /></td>
      </tr>
      <tr>
      <td><label for="Password">Password: </label></td>
      <td><input type="password" placeholder="Password" name="user_pw" required /></td>
      </tr>
 </table>
 <hr>
      <input type="submit" value="로그인" />
      <input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/board?action=user_register_form'" />
      <input type="button" value="뒤로가기" onclick="window.history.go(-1)" />
 </form>
</body>
</html>