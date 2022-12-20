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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	@Column(length = 20, nullable = false)
	private String name;
	@Column(length = 32,nullable = false,columnDefinition = "VARCHAR(32) DEFAULT 'STUDENT' ")
	@Enumerated(value=EnumType.STRING)
	private Role role;
	@Column(name = "login_id", length = 20, nullable = false)
	private String loginId;
	@Column(length = 255, nullable = false)
	private String password;
	
	@ManyToMany(mappedBy ="teachers")
	private List<Classes> classes;

	public enum Role {
		ADMIN, TEACHER, STUDENT, OFFICE
	}
	
	public Account() {
		
	}
	
	

}
