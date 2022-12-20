package com.shop.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	private String address;
	@ElementCollection
	private List<String> phoneNumbers;
	@ElementCollection
	private List<String> emails;

	public Contact() {

	}

}
