package org.twinnation.seminar.springsecurity.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.twinnation.seminar.springsecurity.bean.User;
import org.twinnation.seminar.springsecurity.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api")
@Api
public class UserController {
	
	@Autowired private UserService userService;
	
	
	@GetMapping("/user")
	public User getCurrentUser() {
		return userService.getCurrentUser();
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	
	@PutMapping("/users")
	public String createUser(@RequestParam String username, @RequestParam String password) {
		return userService.createUser(username, password);
	}
	

	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User deleteUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
}
