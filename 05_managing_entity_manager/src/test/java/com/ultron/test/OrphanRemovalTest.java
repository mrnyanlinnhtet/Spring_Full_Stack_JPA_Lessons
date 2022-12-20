package com.ultron.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.ultron.member_ship.Member;

public class OrphanRemovalTest {

	static EntityManagerFactory emf;

	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}

	@ParameterizedTest
	@ValueSource(ints = 1)
	public void orphan_removal_test(int id) {
		var em = emf.createEntityManager();

		em.getTransaction().begin();

		var member = em.find(Member.class, id);
		Assertions.assertNotNull(member);

		em.remove(member);
		assertFalse(em.contains(member));

		em.getTransaction().commit();

		em = emf.createEntityManager();
		member = em.find(Member.class, id);
		assertNull(member);
	}

	@AfterAll
	public static void terminate() {

		if (emf != null && emf.isOpen()) {
			emf.close();
		}

	}

}
