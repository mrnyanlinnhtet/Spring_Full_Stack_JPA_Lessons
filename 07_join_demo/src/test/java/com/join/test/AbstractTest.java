package com.join.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {
	EntityManagerFactory emf;
	EntityManager em;
	
	
	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
		em = emf.createEntityManager();
	}

	@AfterEach
	void terminate() {
		em.close();
		emf.close();
	}
}
