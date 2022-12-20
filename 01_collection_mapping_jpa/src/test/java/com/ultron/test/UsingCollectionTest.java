package com.ultron.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UsingCollectionTest {
	
	static EntityManagerFactory emf;
	
	@BeforeAll
	public static void inint() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}
	
	@AfterAll
	public static void terminate() {
		
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
		
	}
	
	@Test
	void test() {
		
	}

}
