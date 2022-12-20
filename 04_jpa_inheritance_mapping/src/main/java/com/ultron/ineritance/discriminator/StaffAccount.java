package com.ultron.ineritance.discriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "staff_account")
@DiscriminatorValue("S")
public class StaffAccount extends OfficeAccounts {

	private static final long serialVersionUID = 1L;
	
	public StaffAccount() {
		
	}

	public StaffAccount(String name, String username, String password, Roles role, boolean isAdmin) {
		super(name, username, password, role, isAdmin);
	}
	
	

}
