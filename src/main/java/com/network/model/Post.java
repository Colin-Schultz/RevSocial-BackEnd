package com.network.model;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int post_id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@Column(name = "post_date", nullable = false)
	private Timestamp postDate;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "post_pic", nullable = true)
	private String picture;
	
	@Column(name = "liked_By", nullable=true)
	private String[] likes;

	public Post() {
		super();
	}

	public Post(int post_id, User user, Timestamp postDate, String message, String picture, String[] likes) {
		super();
		this.post_id = post_id;
		this.user = user;
		this.postDate = postDate;
		this.message = message;
		this.picture = picture;
		this.likes = likes;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String[] getLikes() {
		return likes;
	}

	public void setLikes(String[] likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(likes);
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + post_id;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (!Arrays.equals(likes, other.likes))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (post_id != other.post_id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", user=" + user + ", postDate=" + postDate + ", message=" + message
				+ ", picture=" + picture + ", likes=" + Arrays.toString(likes) + "]";
	}
	
	


	
	
	
	
}
