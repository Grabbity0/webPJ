<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 작성&수정</title>
</head>
<body>
<c:choose> 
<c:when test="${board.board_id eq null}"> 
<form id = "list_form" action="${pageContext.request.contextPath}/board?action=board_insert"
			method="POST">
	<div>
	<table>
      <tr>
      <td><label for="subject">제목: </label></td>
      <td><input type="text" placeholder="subject" name="board_subj" required /></td>
      </tr>
      <tr>
      <td><label for="content">내용: </label></td>
      <td><textarea style="resize: none;" rows="30" cols="70" name="board_cont" required></textarea></td>
      </tr>
      <tr>
      <td><label for="subject">작성자: </label></td>
      <td><input type="text" placeholder="writer" name="user_id" value="${sessionScope.user_id}" readonly /></td>
      </tr>
	</table>
	</div>
	<div>
	<input type="submit" value="게시물 등록"/>
	<input type="button" value="취소" onclick="window.history.go(-1);" />
	</div>
</form>
</c:when>	
<c:otherwise>
<form id = "list_form" action="${pageContext.request.contextPath}/board?action=board_update"
			method="POST">
	<div>
	<table>
	  <tr>
	  <td><input type="text" placeholder="board_id" name="board_id" value="${board.board_id}" readonly /></td>
	  </tr>
      <tr>
      <td><label for="subject">제목: </label></td>
      <td><input type="text" placeholder="subject" name="board_subj" value="${board.board_subj}" required /></td>
      </tr>
      <tr>
      <td><label for="content">내용: </label></td>
      <td><textarea style="resize: none;" rows="30" cols="70" name="board_cont"required>${board.board_cont}</textarea></td>
      </tr>
      <tr>
      <td><label for="subject">작성자: </label></td>
      <td><input type="text" placeholder="writer" name="user_id" value="${board.user_id}" readonly /></td>
      </tr>
	</table>
	</div>
	<div>
	<input type="submit" value="게시물 수정" />
	<input type="button" value="취소" onclick="window.history.go(-1);" />
	</div>
	</form>
</c:otherwise>
</c:choose>
</body>
</html>