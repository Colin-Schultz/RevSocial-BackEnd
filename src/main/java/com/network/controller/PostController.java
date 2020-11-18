package com.network.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.network.model.Post;
import com.network.model.User;
import com.network.service.PostService;
import com.network.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

	private PostService ps;
	private UserService us;

	@Autowired
	public PostController(PostService ps, UserService us) {
		this.ps = ps;
		this.us = us;
	}

	public PostService getPs() {
		return ps;
	}

	public void setPs(PostService ps) {
		this.ps = ps;
	}

	@GetMapping
	public @ResponseBody List<Post> getAll() {
		return ps.getPosts();
	}

	@GetMapping("/{id}")
	public @ResponseBody Post getPostById(@PathVariable(value = "id") @RequestBody Integer id) {
		return ps.findById(id);
	}
	@GetMapping("/user/{username}")
	public @ResponseBody List<Post> getPostByUsername(@PathVariable(value = "username") String username){
		User u = us.findByUsername(username);
		return ps.getPostsByUsername(u.getUser_id());
	}

	@PutMapping
	public @ResponseBody Post updatePost(@RequestBody Post p) {
		return ps.updatePost(p);
	}

	@PostMapping
	public @ResponseBody Post createPost(@RequestBody Post p , @RequestHeader(value="image") MultipartFile image) {
		Logger.getLogger(PostController.class).info(p.getUser() + ": Created a post");
		return ps.createPost(p);
	}

	@DeleteMapping
	public @ResponseBody Post deletePost(@RequestBody Integer id) {
		return ps.deletePost(id);
	}

}