package com.ultron.ineritance.discriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pm_account")
@DiscriminatorValue("P")
public class PMAccount extends OfficeAccounts {

	private static final long serialVersionUID = 1L;
	
	public PMAccount() {
		
	}

	public PMAccount(String name, String username, String password, Roles role, boolean isAdmin) {
		super(name, username, password, role, isAdmin);
	}

	 
}
