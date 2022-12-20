package com.ultron.entities.one_to_many;

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
@Getter
@Setter
@Table(name = "subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "subject_name",length = 50,nullable = false)
	private String name;
	@Column(length = 50,nullable = false)
	private String teacher_name;
	
	@ManyToOne
	private Student student;
	
	public Subject() {
		
	}

}
