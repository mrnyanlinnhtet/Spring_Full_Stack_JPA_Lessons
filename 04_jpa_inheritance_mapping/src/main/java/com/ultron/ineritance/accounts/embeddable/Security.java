package com.ultron.ineritance.accounts.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "username", nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	
}
