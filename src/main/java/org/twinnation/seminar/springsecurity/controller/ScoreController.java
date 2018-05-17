package org.twinnation.seminar.springsecurity.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
	
	
	@PostMapping("/scores")
	public Score createScore(@RequestParam String username, @RequestParam Long points) {
		return scoreService.createScore(username, points);
	}
	
	
	@DeleteMapping("/scores/{id}")
	public String deleteScore(@PathVariable Long id) {
		scoreService.deleteScore(id);
		return "{}";
	}
	
}
