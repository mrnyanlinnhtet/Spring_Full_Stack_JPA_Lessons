package com.ultron.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.ultron.member_ship.Contact;

public class FetchModeTest {

	static EntityManagerFactory emf;

	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}

	@ParameterizedTest
	@ValueSource(ints = 1)
	void single_field_lazy_fetch_mode_test(int id) {

		var em = emf.createEntityManager();
		var contact = em.find(Contact.class, id);
		em.close();

		assertThrows(LazyInitializationException.class, contact.getMember()::getName);

	}

	@AfterAll
	static void terminate() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

}
