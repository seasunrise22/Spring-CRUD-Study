<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 페이지</title>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<section id="board-section">
		<article id="post-write">
			<form action="/create" method="post">
			<input type="hidden" id="id" name="id" value="${empty post ? '' : post.id}">
				<div>
					<input type="text" id="author" name="author" value="${empty post ? '' : post.author}" placeholder="작성자" required>
					<input type="password" id="password" name="password"  value="${empty post ? '' : post.password}" placeholder="비밀번호" required>
				</div>
				<div>
					<input type="text" id="title" name="title" value="${empty post ? '' : post.title}" placeholder="제목" required>
				</div>
				<div>
					<textarea id="content" name="content" placeholder="내용" required>${empty post ? '' : post.content}</textarea>
				</div>
				<div id="board-bottom">
					<button type="button" id="write-cancel-button" onclick="location.href='/'">취소</button>
					<button type="submit" id="write-submit-button">등록</button>
				</div>
			</form>
		</article>
	</section>

</body>
</html>