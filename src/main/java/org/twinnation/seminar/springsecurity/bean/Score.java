package org.twinnation.seminar.springsecurity.bean;

import javax.persistence.*;


@Entity
@Table(name="score")
public class Score {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="points")
	private Long points;
	
	
	public Score(String username, Long points) {
		this.username = username;
		this.points = points;
	}
	
	
	public Score() {}
	
	
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Long getPoints() {
		return points;
	}
	
	
	public void setPoints(Long points) {
		this.points = points;
	}
	
}
