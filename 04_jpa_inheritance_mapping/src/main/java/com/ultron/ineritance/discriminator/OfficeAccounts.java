package com.ultron.ineritance.discriminator;

import java.awt.Color;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultron.ineritance.converter.ColorConverter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorColumn(name = "entity_type",columnDefinition = "CHAR(1)")
@Table(name = "office_accounts")
public abstract class OfficeAccounts  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(name = "user_name", nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles role;
	@Convert(converter = ColorConverter.class)
	@Column(name = "fav_color",nullable = false)
	private Color favColor;
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean isAdmin;

	public OfficeAccounts() {

	}

	public OfficeAccounts(String name, String username, String password,Roles role, boolean isAdmin) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isAdmin = isAdmin;
	}

	public enum Roles {
		ADMIN, PROJECT_MANAGER, STAFF
	}

}
