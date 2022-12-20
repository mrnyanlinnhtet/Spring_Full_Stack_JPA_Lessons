package com.ultron.entities;

import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "members",
        uniqueConstraints = @UniqueConstraint(columnNames = {"email"})
		)

@SecondaryTable(name = "login_info", indexes = {@Index(columnList = "role")})
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = TABLE, generator = "member_squ")
	@SequenceGenerator(name = "member_squ", allocationSize = 1, initialValue = 2001)
	private Integer id;
	private String name;
	private LocalDate dob;
	
	
	@Column(table = "login_info")
	private String loginId;
	@Column(table = "login_info")
	private String password;
	@Column(table = "login_info")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Embedded
	private Contact contact;

	
	public enum Role {
		ADMIN, TEACHER, STUDENT
	}

	public Member() {

	}

	public Member(String name, String phone, String email, String adderss, LocalDate dob) {
		super();
		contact = new Contact();
		this.name = name;
		this.dob = dob;
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setAddress(adderss);
	}

}
