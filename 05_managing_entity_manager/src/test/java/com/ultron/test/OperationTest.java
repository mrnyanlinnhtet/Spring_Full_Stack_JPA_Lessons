package com.ultron.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class OperationTest {
	
	static EntityManagerFactory emf;
	
	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}
	
	
	
	@AfterAll
	public static void terminate() {
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}

}
