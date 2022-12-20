package com.jpql.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ProductSalePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int saleId;
	private int productId;
	
	public ProductSalePK() {
		
	}

}
