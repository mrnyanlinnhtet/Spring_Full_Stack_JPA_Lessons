package com.jpql.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@NamedQuery(name = "Category.getCount",query = "SELECT COUNT(c) FROM Category c")
@NamedQuery(name = "Category.getAll",query = "SELECT c FROM Category c")
@NamedQuery(name = "Category.updateCategoryById",query = "UPDATE Category c SET c.name = :name WHERE c.id = :id")
@NamedQuery(name = "Category.findNameLike",query = "SELECT c FROM Category c WHERE LOWER(c.name) LIKE ?1")
@Getter
@Setter
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 50,unique = true)
	private String name;
	@Column(length = 50)
	private String logo;
	
	public Category() {
		
	}

}
