<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.wp.board.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
	<script>
		function (){
			
		}
		
	</script>
</head>
<body>
<form action="<%= request.getContextPath() %>/board?action=register"
			method="POST">
 <table>
      <tr>
      <td><label for="ID">ID: </label></td>
      <td><input type="text" placeholder="ID" name="user_id" required /> 
      </td>
      </tr>
      <tr>
      <td><label for="PassWord">Password: </label></td>
      <td><input type="password" placeholder="Password" name="user_pw" required /></td>
      </tr>
      <tr>
      <td><label for="NickName">Nickname: </label></td>
      <td><input type="text" placeholder="Nickname" name="nickname" required /></td>
      </tr>
      <tr>
      <td><label for="Email">Email: </label></td>
      <td><input type="text" placeholder="Email" name="email" required /></td>
      </tr>
 </table>
 <hr>
      <input type="submit" value="회원가입" />
      <input type="button" value="취소" onclick="window.history.go(-1);"/>
 </form>
</body>
</html>