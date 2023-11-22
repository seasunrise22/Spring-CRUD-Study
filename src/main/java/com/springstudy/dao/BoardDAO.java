package com.springstudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.springstudy.models.Comment;
import com.springstudy.models.Post;

@Repository
public class BoardDAO {
	private final DataSource dataSource;

	public BoardDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void create(Post post) {
		try (Connection conn = dataSource.getConnection()) {
			String sql = "INSERT INTO posts (author, password, title, content) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getAuthor());
			pstmt.setString(2, post.getPassword());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Post post) {
		try(Connection conn = dataSource.getConnection()) {
			String sql = "UPDATE posts SET author = ?, password = ?, title = ?, content = ?, updatedAt = NOW() WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getAuthor());
			pstmt.setString(2, post.getPassword());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getContent());
			pstmt.setInt(5, post.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Integer id) {
		try(Connection conn = dataSource.getConnection()) {
			String sql = "DELETE FROM posts WHERE id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateViews(Post post) {
		try (Connection conn = dataSource.getConnection()) {
			String sql = "UPDATE posts SET views = ? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getViews());
			pstmt.setInt(2, post.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTotalPosts() {
		int totalPosts = 0;
		try(Connection conn = dataSource.getConnection()) {
			String sql = "SELECT COUNT(*) FROM posts";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				totalPosts = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return totalPosts;
	}
	
	public List<Post> getPostsByPage(int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<Post> posts = new ArrayList<>();
		try(Connection conn = dataSource.getConnection()) {
			String sql = "SELECT * FROM posts ORDER BY createdAt DESC LIMIT ? OFFSET ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageSize);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setTitle(rs.getString("title"));
				post.setAuthor(rs.getString("author"));
				post.setCreatedAt(rs.getDate("createdAt"));
				post.setViews(rs.getInt("views"));
				posts.add(post);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public Post getPostById(Integer id) {
		Post post = null;
		try(Connection conn = dataSource.getConnection()) {
			String sql = "SELECT * FROM posts WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				post = new Post();
				post.setId(rs.getInt("id"));
				post.setPassword(rs.getString("password"));
				post.setAuthor(rs.getString("author"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setCreatedAt(rs.getDate("createdAt"));
				post.setUpdatedAt(rs.getDate("updatedAt"));
				post.setViews(rs.getInt("views"));				;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return post;
	}
	
	/*
	 * 댓글
	 */
	public void createComment(Comment comment) {
		try(Connection conn = dataSource.getConnection()) {
			String sql = "INSERT INTO comments (postId, author, password, content) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getPostId());
			pstmt.setString(2, comment.getAuthor());
			pstmt.setString(3, comment.getPassword());
			pstmt.setString(4, comment.getContent());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Comment> getCommentsById(Integer id) {
		List<Comment> comments = new ArrayList<>();
		try(Connection conn = dataSource.getConnection()) {
			String sql = "SELECT * FROM comments WHERE postId = ? ORDER BY createdAt ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setAuthor(rs.getString("author"));
				comment.setContent(rs.getString("content"));
				comment.setCreatedAt(rs.getDate("createdAt"));
				comments.add(comment);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return comments;
	}

}
