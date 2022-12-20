package com.jpql.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "product")
@Getter
@Setter
@NamedQuery(name = "Product.findCategoryNameLike",query = "SELECT p FROM Product p WHERE LOWER(p.category.name) LIKE ?1")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	private Category category;
	private String image;
	private int price;
	private boolean available;
	
	@Column(name = "color")
	@CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product"))
	@ElementCollection
	private List<String>colors;

	public Product() {

	}

}
