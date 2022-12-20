package com.ultron.ineritance.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_account")
public class TeacherAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	public TeacherAccount() {
		
	}

}
