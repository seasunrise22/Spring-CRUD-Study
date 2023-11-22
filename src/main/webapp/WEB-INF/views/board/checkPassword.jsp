<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인 페이지</title>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<section id="checkPassword-section">
		<article>
			<c:choose>
				<c:when test="${action == 'update'}">
					<form action="/update/${postId}" method="post">
						<input type="password" id="password-box" name="password"
							placeholder="비밀번호를 입력하세요.">
						<button type="submit" id="checkPassword-button">수정</button>
					</form>
				</c:when>
				<c:otherwise>
					<form action="/delete/${postId}" method="post">
						<input type="password" id="password-box" name="password"
							placeholder="비밀번호를 입력하세요.">
						<button type="submit" id="checkPassword-button">삭제</button>
					</form>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty error}">
				<script>
					alert('${error}');
				</script>
			</c:if>
		</article>
	</section>
</body>
</html>