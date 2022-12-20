package com.jpql.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class ReferenceAbleTest {

	 protected static EntityManagerFactory emf;
	 protected EntityManager em;
	 
	 @BeforeAll
	 public static void initSystem() {
		 emf = Persistence.createEntityManagerFactory("JPU");
	 }
	 
	 @BeforeEach
	 public void before() {
		 em = emf.createEntityManager();
	 }
	 
	 @AfterEach
	 public void after() {
		 em.close();
	 }
	 
	 @AfterAll
	 public static void terminateSystem() {
		 if(emf != null && emf.isOpen()) {
			 emf.close();
		 }
	 }
}
