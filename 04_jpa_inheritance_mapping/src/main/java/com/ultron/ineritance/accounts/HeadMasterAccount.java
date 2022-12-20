package com.ultron.ineritance.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "headmaster_account")
public class HeadMasterAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	public HeadMasterAccount() {
		
	}

}
