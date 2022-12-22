package com.join.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true,length = 20)
	private String name;
	@Column(nullable = false,length = 20)
	private String phone;
	@Column(nullable = false,length = 40)
	private String email;
	
	@ManyToMany(mappedBy = "supplier")
	private List<Product> product;

	public Supplier() {

	}
}
