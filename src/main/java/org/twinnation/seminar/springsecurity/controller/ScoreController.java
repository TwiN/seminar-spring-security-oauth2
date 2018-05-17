package org.twinnation.seminar.springsecurity.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twinnation.seminar.springsecurity.bean.Score;
import org.twinnation.seminar.springsecurity.service.ScoreService;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Api
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	
	@GetMapping("/scores")
	public List<Score> getScoreboard() {
		return scoreService.getScoreboard();
	}
	
}
