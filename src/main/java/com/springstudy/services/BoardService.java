package com.springstudy.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springstudy.dao.BoardDAO;
import com.springstudy.models.Comment;
import com.springstudy.models.Post;

@Service
public class BoardService {

	private final BoardDAO boardDAO;

	public BoardService(BoardDAO postDAO) {
		this.boardDAO = postDAO;
	}

	public void create(Post post) {
		boardDAO.create(post);
	}

	public void update(Post post) {
		boardDAO.update(post);
	}

	public void delete(Integer id) {
		boardDAO.delete(id);
	}

	public int getTotalPosts() {
		return boardDAO.getTotalPosts();
	}

	public List<Post> getPostsByPage(int page, int pageSize) {
		return boardDAO.getPostsByPage(page, pageSize);
	}

	public Post getPostById(Integer id) {
		return boardDAO.getPostById(id);
	}

	public void incrementViews(Integer id) {
		Post post = boardDAO.getPostById(id);
		post.incrementViews();
		boardDAO.updateViews(post);
	}

	public boolean checkPassword(Integer id, String password) {
		Post post = boardDAO.getPostById(id);
		return post.getPassword().equals(password);
	}

	public int getNumOfPages(int pageSize) {
		int totalPosts = getTotalPosts();
		return (int) Math.ceil((double) totalPosts / pageSize);
	}

	/**
	 * 댓글
	 */
	public void createComment(Comment comment) {
		boardDAO.createComment(comment);
	}
	
	public List<Comment> getCommentsById(Integer id) {
		return boardDAO.getCommentsById(id);
	}
}
