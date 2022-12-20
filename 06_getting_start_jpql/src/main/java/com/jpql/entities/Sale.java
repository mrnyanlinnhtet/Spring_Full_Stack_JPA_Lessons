package com.jpql.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sale")
@Getter
@Setter
public class Sale implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,name = "sale_date")
	private LocalDate saleDate;
	@ManyToOne(optional = false)
	private Customer customer;
	@OneToMany(mappedBy = "sale",orphanRemoval = true)
	private List<ProductSale>product;
	
	public Sale() {
		
	}

}
