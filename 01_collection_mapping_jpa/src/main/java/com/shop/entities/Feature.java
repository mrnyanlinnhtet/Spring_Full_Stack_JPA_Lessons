package com.shop.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class Feature implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "feature_name",nullable = false)
	private String name;
	@Column(name = "feature_value",nullable = false)
	private String feature;
	
	public Feature() {
		
	}
	
	
}
