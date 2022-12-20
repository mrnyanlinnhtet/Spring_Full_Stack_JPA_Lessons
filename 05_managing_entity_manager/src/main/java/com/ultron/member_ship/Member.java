package com.ultron.member_ship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@Table(name = "membership")
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(name = "username",nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false,columnDefinition = "VARCHAR(32) DEFAULT 'MEMBER'")
	private Role role;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> tags;
	
   @OneToOne(orphanRemoval = true)
	private Contact contact;
	
	
	public Member() {
		tags = new ArrayList<String>();
	}
	
	
	public Member(String name, String userName, String password, Role role) {
		this();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public enum Role{
		ADMIN,GOLD_MEMBER,MEMBER
	}

}
