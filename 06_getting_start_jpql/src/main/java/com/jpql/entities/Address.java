package com.jpql.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	@Column(length = 100)
	private String building;
	@Column(length = 100)
	private String street;
	
	@ManyToOne
	private Township township;
	@OneToOne
	@PrimaryKeyJoinColumn
	@MapsId("id")
	private Customer customer;
	
	public Address() {
		
	}
	

}
