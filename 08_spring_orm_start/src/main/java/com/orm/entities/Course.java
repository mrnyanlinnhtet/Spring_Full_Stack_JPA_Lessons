package com.orm.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private long id;
	private String name;
	private int duration;
	private int fees;
	private String description;

	public Course() {

	}

	public Course(String name, int duration, int fees, String description) {
		super();
		this.name = name;
		this.duration = duration;
		this.fees = fees;
		this.description = description;
	}

}
