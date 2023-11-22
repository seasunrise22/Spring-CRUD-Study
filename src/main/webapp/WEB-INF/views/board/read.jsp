<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 열람 페이지</title>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<section id="board-section">
		<article id="post-read">
			<div class="read-row">
				<span class="column">번호</span> <span class="content">${post.id}</span>
			</div>
			<div class="read-row">
				<span class="column">작성자</span><span class="content">${post.author}</span>
			</div>
			<div class="read-row">
				<span class="column">작성일</span><span class="content">${post.createdAt}</span>
			</div>
			<div class="read-row">
				<span class="column">수정일</span><span class="content">${post.updatedAt}</span>
			</div>
			<div class="read-row">
				<span class="column">조회수</span><span class="content">${post.views}</span>
			</div>
			<div id="read-space"></div>
			<div class="read-row">
				<span class="column">제목</span><span class="content">${post.title}</span>
			</div>
			<div class="read-row">
				<span class="column">내용</span><span class="content">${post.content}</span>
			</div>
			<div id="board-bottom">
				<button type="button" onclick="location.href='/checkPassword/${post.id}?action=update'">수정</button>
				<button type="button" onclick="location.href='/checkPassword/${post.id}?action=delete'">삭제</button>
			</div>
		</article>

		<!-- 댓글 리스트 -->
		<div id="comment-list">
			<c:forEach var="comment" items="${comments}">
				<div id="row-comment">
					<div class="cell-comment">${comment.author}</div>
					<div class="cell-comment">${comment.content}</div>
					<div class="cell-comment">${comment.createdAt}</div>
				</div>
			</c:forEach>
		</div>

		<!-- 댓글 작성 -->
		<section id="comment-write">
			<span id="comment-label">댓글 쓰기</span>
			<form action="/comment/create" method="POST">
				<input type="hidden" name="postId" value="${post.id}">
				<div id="comment-write-top">
					<input type="text" id="comment-author" name="author" placeholder="작성자" required>
					<textarea id="comment-write-content" name="content" placeholder="댓글 내용" required></textarea>
				</div>
				<div id="comment-write-bottom">
					<button type="submit" id="comment-submit-button">등록</button>
				</div>
			</form>
		</section>
	</section>
</body>
</html>