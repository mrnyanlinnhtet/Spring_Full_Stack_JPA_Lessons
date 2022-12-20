package com.ultron.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BiTest {
	
	 static EntityManagerFactory emf;

	 @BeforeAll
	 static void init() {
		emf = Persistence.createEntityManagerFactory("JPU"); 
	 }
	 
	 @AfterAll
	 static void terminate() {
		 if(emf != null && emf.isOpen()) {
			 emf.close();
		 }
	 }
	 
	 @Test
	 void test() {
		 
	 }
}
