package com.network.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.network.email.ResetEmailConfig;
import com.network.model.User;
import com.network.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	private UserService us;

	// md5 Salt hash
	public static String encryptPassword(String password) {
		String returnPass = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			returnPass = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return returnPass;

	}

	@Autowired
	public UserController(UserService us) {
		this.us = us;
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	@GetMapping
	public @ResponseBody List<User> getAll() {
		return us.getAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody User getUserById(@PathVariable(value = "id") @RequestBody Integer id) {
		return us.findById(id);
	}

	@PutMapping("/login")
	public @ResponseBody User getUserByEmail(@RequestBody User u) {
		return us.findByEmail(u.getEmail().toLowerCase(), encryptPassword(u.getPassword()));

	}

	@GetMapping("/username/{username}")
	public @ResponseBody User getUserByUsername(@PathVariable(value = "username") String username) {
		return us.findByUsername(username);
	}

	@PutMapping
	public @ResponseBody User updateUser(@RequestBody User u) {
		u.setPassword(encryptPassword(u.getPassword()));
		u.setEmail(u.getEmail().toLowerCase());
		return us.update(u);
	}

	@PostMapping
	public @ResponseBody User createUser(@RequestBody User u) {
		u.setPassword(encryptPassword(u.getPassword()));
		u.setEmail(u.getEmail().toLowerCase());
		Logger.getLogger(UserController.class).info("Attempting to Create user with email: " +u.getEmail());
		return us.createUser(u);
	}
	
	@PostMapping("/upload")
	public @ResponseBody User uploadPicture(@RequestBody User u) {
		return us.uploadPicture(u);
	}
	
	@GetMapping("/picture/{username}")
	public @ResponseBody String getPictureURL(@PathVariable(value = "username") String username) {
		return us.findByUsername(username).getPicture();
	}
	
	@PutMapping("/reset/{email}")
	public @ResponseBody String resetPassword(@PathVariable(value = "email") String email) {
		ResetEmailConfig.setToEmail(email);
		String newPassword = encryptPassword(ResetEmailConfig.generatePassword());
		
		try {
			ResetEmailConfig.sendEmail();
			us.resetPassword(email, newPassword);
		} catch (UnsupportedEncodingException | MessagingException e) {
			return e.getMessage();
		}
		
		return "reset";
	}

	@DeleteMapping
	public @ResponseBody User deleteUser(@RequestBody Integer i) {
		return us.deleteUser(i);
	}

}