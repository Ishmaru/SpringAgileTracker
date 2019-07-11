package com.ad.application.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Story {
	
	public static enum Iteration 
	{ 
	    BACKLOG, SPRINT, TESTING, COMPLETE; 
	} 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String title;
	private String userStory;
	private String actionCriteria;
	private Integer points;
	@Enumerated(EnumType.STRING)
	private Iteration sprintIteration;
	
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserStory() {
		return userStory;
	}
	public void setUserStory(String userStory) {
		this.userStory = userStory;
	}
	public String getActionCriteria() {
		return actionCriteria;
	}
	public void setActionCriteria(String actionCriteria) {
		this.actionCriteria = actionCriteria;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Iteration getSprintIteration() {
		return sprintIteration;
	}
	public void setSprintIteration(Iteration sprintIteration) {
		this.sprintIteration = sprintIteration;
	}
	public Long getId() {
		return id;
	}
	public Story(@NotEmpty String title, String userStory, String actionCriteria, Integer points,
		Iteration sprintIteration, Long userId) {
		this.title = title;
		this.userStory = userStory;
		this.actionCriteria = actionCriteria;
		this.points = points;
		if(sprintIteration != null) {
			this.sprintIteration = sprintIteration;
		}else {
			this.sprintIteration = Iteration.BACKLOG;
		}

		this.userId = userId;
	}
	public Story() {
	}
	
	@Override
	public String toString() {
		return "Story [id=" + id + ", title=" + title + ", userStory=" + userStory + ", actionCriteria="
				+ actionCriteria + ", points=" + points + ", sprintIteration=" + sprintIteration + ", userId=" + userId
				+ "]";
	}
	
	
}
