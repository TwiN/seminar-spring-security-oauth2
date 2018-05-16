package org.twinnation.seminar.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}


	@GetMapping("/")
	@PreAuthorize("isAuthenticated()")
	public String index() {
		return "index";
	}


	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		return "admin";
	}
	
}
