package com.shop.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String category;

	@ElementCollection
	@CollectionTable(name = "product_price", joinColumns = @JoinColumn(name = "product"))
	@MapKeyColumn(name = "price_type")
	@MapKeyEnumerated(EnumType.STRING)
	private Map<PriceType, Integer> price;

	@ElementCollection
	@CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product"))
	private List<String> tags;

	@ElementCollection
	@CollectionTable(name = "product_feature", joinColumns = @JoinColumn(name = "product"))
	private List<Feature> feature;

	public enum PriceType {
		CUSTOMER, AGENT, PURCHEASE
	}

}
