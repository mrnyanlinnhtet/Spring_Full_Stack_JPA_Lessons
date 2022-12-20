package com.student.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 45)
	private String name;
	@Column(nullable = false)
	private int hour;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false,length = 45)
	private Level level;
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	@OneToMany
	@JoinColumn(nullable = false,name = "course_id")
	private List<Classes> classes;
	
	public enum Level{
		BASIC,INTERMEDIATE,ADVANCE
	}


}
