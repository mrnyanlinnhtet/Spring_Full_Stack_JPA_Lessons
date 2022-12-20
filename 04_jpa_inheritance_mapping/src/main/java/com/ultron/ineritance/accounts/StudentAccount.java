package com.ultron.ineritance.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="student_account" )
public class StudentAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	public StudentAccount() {
		
	}

}
