package com.network.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.dao.PostDao;
import com.network.dao.UserDao;
import com.network.model.Post;
import com.network.model.User;

@Service
public class PostService {
	private PostDao pd;
	private UserDao ud;
	
	@Autowired
	public PostService(PostDao pd, UserDao ud) {
		this.pd = pd;
		this.ud = ud;
	}
	
	public PostDao getPd() {
		return pd;
	}
	public void setPd(PostDao pd) {
		this.pd = pd;
	}
	
	public List<Post> getPosts() {
		return pd.findAll();
	}
	
	public List<Post> getPostsByUsername(Integer user_id){
		return pd.findAllByUsername(user_id);
	}
	
	public Post findById(Integer i) {
		return pd.findById(i);
	}
	
	public Post updatePost(Post p) {
		return pd.update(p);
	}
	
	public Post createPost(Post p) {
		User u = ud.findByUsername(p.getUser().getUsername());
		p.setUser(u);
		p.setPostDate(new Timestamp(System.currentTimeMillis()));
		return pd.save(p);
	}
	
	public Post deletePost(Integer i) {
		return pd.delete(i);
	}

}
