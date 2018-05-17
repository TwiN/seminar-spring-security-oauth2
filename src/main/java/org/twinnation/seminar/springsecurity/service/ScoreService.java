package org.twinnation.seminar.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twinnation.seminar.springsecurity.bean.Score;
import org.twinnation.seminar.springsecurity.repository.ScoreRepository;

import java.util.List;


@Service
public class ScoreService {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	
	public Score createScore(String username, Long points) {
		return scoreRepository.save(new Score(username, points));
	}
	
	
	public List<Score> getScoreboard() {
		return scoreRepository.findAll();
	}
	
	
	public void deleteScore(Long id) {
		scoreRepository.deleteById(id);
	}
	
}
