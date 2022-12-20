package com.student.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.student.entities.Account;
import com.student.entities.Account.Role;
import com.student.entities.Address;

public class RelationshipsTest {

	static EntityManagerFactory emf;

	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}

	@AfterAll
	public static void terminate() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	@Test
	void test() {

		var em = emf.createEntityManager();

		em.getTransaction().begin();

		// Create Account
		var acc = new Account();
		acc.setLoginId("nyan123");
		acc.setPassword("admin");
		acc.setName("Nyan Linn Htet");
		acc.setRole(Role.ADMIN);
		em.persist(acc);

		 Assertions.assertEquals(1, acc.getId());
		// Create Address
		var adr = new Address();
		adr.setAccount(acc);
		adr.setAddress("Sittwe");
		adr.setEmail("mr.nyanlinnhtet13@gmail.com");
		adr.setPhone("09444859894");
		em.persist(adr);

		em.getTransaction().commit();

	}

}
