package com.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.dao.UserDao;
import com.network.model.User;

@Service
public class UserService {
	
	private UserDao ud;
	
	public UserService(UserDao ud) {
		this.ud = ud;
	}
	
	public UserDao getUd() {
		return ud;
	}

	@Autowired
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	
	public List<User> getAll(){
		return ud.findAll();
	}
	
	public User findById(Integer i) {
		return ud.findById(i);
	}
	
	public User findByEmail(String email, String password) {
		return ud.findByEmail(email, password);
	}
	
	public User findByUsername(String username) {
		return ud.findByUsername(username);
	}
	
	public User update(User u) {
		return ud.update(u);
	}
	
	public User createUser(User u) {
		return ud.save(u);
	}
	
	public User uploadPicture(User u) {
		return ud.upload(u);
	}
	
	public void resetPassword(String email, String password) {
		ud.resetPassword(email, password);
	}
	
	public User deleteUser(Integer i) {
		return ud.delete(i);
	}

}
