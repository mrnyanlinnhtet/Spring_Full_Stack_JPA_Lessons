package com.ultron.ineritance.accounts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.ultron.ineritance.accounts.embeddable.Security;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;

@MappedSuperclass
@Getter
@Setter
public abstract class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Embedded
	private Security mainSecurity;
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "userName", column = @Column(name = "sec_usename")),
			
		@AttributeOverride(name = "password", column = @Column(name = "sec_password")) 
	})
	private Security secondarySecurity;
	@Column(nullable = false)
	private String email;
	@Column(columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
	private boolean isAdmin;

}
