package com.ultron.member_ship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "contacts")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy = "contact")
	private Member member;
	private String phone;
	private String email;
	
	
	public Contact() {
		
	}


	public Contact(String phone, String email) {
		super();
		this.phone = phone;
		this.email = email;
	}
	
	

}
