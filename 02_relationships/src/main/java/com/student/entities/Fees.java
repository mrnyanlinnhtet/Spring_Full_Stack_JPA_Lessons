package com.student.entities;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course_fees")
@Getter
@Setter
public class Fees {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "course_fees",nullable = false)
	private double fees;
	@Column(name = "reference_date",nullable = false)
	private LocalDate refDate;
	@ManyToOne(optional = false)
	private Course course;

}
