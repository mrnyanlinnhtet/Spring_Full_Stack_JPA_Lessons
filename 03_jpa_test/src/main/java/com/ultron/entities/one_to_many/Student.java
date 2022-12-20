package com.ultron.entities.one_to_many;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20, nullable = false)
	private String name;
	@Column(length = 20, nullable = false)
	private String phone;
	@Column(length = 50, nullable = false)
	private String email;
	@Column(length = 100, nullable = false)
	private String address;
	
	@OneToMany(mappedBy = "student")
	@JoinColumn(nullable = false,name = "student_id")
	private List<Subject>subjects;

	public Student() {
		
	}

}
