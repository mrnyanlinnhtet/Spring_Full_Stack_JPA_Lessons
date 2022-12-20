package com.ultron.entities.bi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "nrc")
public class Nrc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	@Column(nullable = false)
	private String nrc_number;
	@Column(name = "father_name",nullable = false)
	private String fatherName;
	
	@OneToOne(mappedBy = "nrc")
	private Person person;

	public Nrc() {
		
	}

}
