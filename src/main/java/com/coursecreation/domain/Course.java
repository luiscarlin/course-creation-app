package com.coursecreation.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {

	private Long id; 
	private String name;
	private Set<Lesson> lessons = new HashSet<>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="course")
	public Set<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}
}
