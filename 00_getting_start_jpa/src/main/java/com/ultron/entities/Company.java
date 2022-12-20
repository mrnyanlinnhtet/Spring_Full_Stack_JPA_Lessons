package com.ultron.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.SequenceGenerator;

@Entity
@Setter
@Getter
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_squ")
	@SequenceGenerator(name = "company_squ")
	private Integer id;
	private String name;
	
	public Company() {
		
	}

	public Company(String name) {
		super();
		this.name = name;
	}
	
	
	

}
