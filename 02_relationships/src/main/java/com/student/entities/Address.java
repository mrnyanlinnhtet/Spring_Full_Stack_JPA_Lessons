package com.student.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter
@Setter
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
 //	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(nullable = false)
	private Integer id;
	@Column(length = 255,nullable = false)
	private String address;
	@Column(length = 12,nullable = false)
	private String phone;
	@Column(length = 50,nullable = false)
	private String email;
	
	@MapsId
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
    private Account account;

	
	public Address() {
		
	}
}
