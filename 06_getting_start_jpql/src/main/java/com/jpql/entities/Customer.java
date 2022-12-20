package com.jpql.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "customer")
@Getter
@Setter
@EqualsAndHashCode
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 50)
	private String name;
	@Column(nullable = false,length = 50)
	private String phone;
	@Column(nullable = false,length = 90)
	private String email;
	
	@OneToOne(optional = false,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}
	         ,mappedBy = "customer")
	private Address address;
	
	@OneToMany(mappedBy = "customer",orphanRemoval = true)
	private List<Sale> sale;
	
	public Customer() {
		
	}

}
