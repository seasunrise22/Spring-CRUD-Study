package com.springstudy.models;

import java.util.Date;

public class Post {
	private Integer id; // 데이터베이스 자동 증가
	private String author;
	private String password;
	private String title;
	private String content;
	private Date createdAt; // 데이터베이스 자동 할당
	private Date updatedAt; // 데이터베이스 자동 할당
	private int views;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public void incrementViews() {
		this.views++;
	}

//	@Override
//	public String toString() {
//		return "Post {" +
//	"id=" + id +
//	", author=" + author +
//	", password=" + password +
//	", title=" + title +
//	", content=" + content +
//	", createdAt=" + createdAt +
//	", updatedAt=" + updatedAt +
//	", views=" + views +
//	"}";
//	}
	
}