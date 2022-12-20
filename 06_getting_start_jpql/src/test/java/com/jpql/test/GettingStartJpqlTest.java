package com.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jpql.entities.Category;

public class GettingStartJpqlTest {
	static EntityManagerFactory emf;

	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}

	@Test
	public void test() {
		var em = emf.createEntityManager();

		var query = em.createQuery("SELECT COUNT(*) FROM Category", Long.class);
		var result = query.getSingleResult();
		assertEquals(3, result);
	}

	@Test
	public void select_all() {
		var em = emf.createEntityManager();

		var query = em.createQuery("SELECT c FROM Category c", Category.class);
		var stream = query.getResultStream();

		stream.forEach((a) -> System.out.println(a.getName()));
	}

	@Test
	public void update() {

		var em = emf.createEntityManager();

		em.getTransaction().begin();
		var query = em.createQuery("""
				UPDATE Category c SET c.name = :name WHERE c.id = :id
				""");
		query.setParameter("name", "Mens");
		query.setParameter("id", 1);
		var count = query.executeUpdate();
		assertEquals(1, count);
		em.getTransaction().commit();
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"f,1",
		"m,1"
	})
	void searchCategoryByNameLike(String name,int count) {
		
		var em = emf.createEntityManager();
		var query = em.createNamedQuery("Category.findNameLike",Category.class);
		query.setParameter(1, name.toLowerCase().concat("%"));
		var list = query.getResultList();
		assertEquals(count,list.size());
	}

	@AfterAll
	public static void terminate() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

}
