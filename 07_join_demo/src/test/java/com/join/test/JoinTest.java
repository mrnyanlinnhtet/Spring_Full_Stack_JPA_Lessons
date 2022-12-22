package com.join.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.join.entities.Category;
import com.join.entities.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JoinTest {

	EntityManagerFactory emf;
	EntityManager em;

	@Order(1)
	@Test
	void one_default_test() {
		var data = em.find(Product.class, 1);
		System.out.println(data.getCategory().getName());
	}

	@Order(2)
	@Test
	void many_to_one_test() {
		var category = em.find(Category.class, 1);
		System.out.println(category.getName());

		category.getProduct().stream().map(Product::getName).forEach(System.out::println);
	}

	@Order(3)
	@Test
	void jpql_one_test() {
		var JPQL = "SELECT p  FROM Product p JOIN p.category c WHERE c.name = :name";

		var data = em.createQuery(JPQL, Product.class);
		data.setParameter("name", "Foods");
		data.getResultList().stream().map(Product::getName).forEach(System.out::println);
	}

	@Order(4)
	@Test
	void jpq_many_test() {
		var JPQL = "SELECT p FROM Product p JOIN p.supplier s WHERE s.name = :supplier";

		var data = em.createQuery(JPQL, Product.class);
		data.setParameter("supplier", "196 Store");
		data.getResultList().stream().map(Product::getName).forEach(System.out::println);
	}

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
