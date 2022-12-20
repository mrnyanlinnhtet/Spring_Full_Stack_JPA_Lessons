package com.ultron.ineritance.family_acc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import lombok.Getter;
import lombok.Setter;
import static javax.persistence.InheritanceType.JOINED;
@Entity
@Inheritance(strategy = JOINED)
@Getter
@Setter
public abstract class FamilyAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "account_name",nullable = false)
	private String accountName;
	@Column(name = "username",nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean isAdmin;
}
