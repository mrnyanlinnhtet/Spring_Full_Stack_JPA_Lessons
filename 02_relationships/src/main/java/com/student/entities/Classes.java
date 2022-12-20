package com.student.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "classes")
public class Classes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false,name = "open_date")
	private LocalDate openDate;
	@Column(nullable = false,name = "start_time")
	private LocalTime startTime;
	@Column(nullable = false,name = "end_time")
	private LocalTime endTime;
	@Column(nullable = false,name = "duration_months")
	private double months;
	
	@ManyToMany
	@JoinTable(name = "class_teacher",
	joinColumns = @JoinColumn(name = "class_id"),
	inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Account> teachers;
	
	
	public Classes() {
		
	}
}
