<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록 페이지</title>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<section id="board-section">
		<article id="post-list">
			<div class="row header">
				<div class="cell">번호</div>
				<div class="cell">제목</div>
				<div class="cell">작성자</div>
				<div class="cell">작성일</div>
				<div class="cell">조회수</div>
			</div>

			<c:forEach var="post" items="${posts}">
				<div class="row">
					<div class="cell">${post.id}</div>
					<div class="cell">
						<a href="/read/${post.id}">${post.title}</a>
					</div>
					<div class="cell">${post.author}</div>
					<div class="cell">${post.createdAt}</div>
					<div class="cell">${post.views}</div>
				</div>
			</c:forEach>

			<div id="board-bottom">
				<button type="button" id="write-button"
					onclick="location.href='/write'">글쓰기</button>
			</div>

			<div id="pagination">
				<c:forEach var="i" begin="1" end="${numOfPages}">
					<a href="/?page=${i}">${i}</a>
				</c:forEach>
			</div>
		</article>
	</section>


</body>
</html>