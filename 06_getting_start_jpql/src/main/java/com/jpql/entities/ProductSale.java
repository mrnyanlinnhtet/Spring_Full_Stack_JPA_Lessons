package com.jpql.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_sale")
public class ProductSale {

	@EmbeddedId
	private ProductSalePK id;
    
	@ManyToOne
	@MapsId("productId")
	private Product product;
	
	@ManyToOne
	@MapsId("saleId")
	private Sale sale;
	
	private int quantity;
	
	
	public ProductSale() {
		id = new ProductSalePK();
	}
}
