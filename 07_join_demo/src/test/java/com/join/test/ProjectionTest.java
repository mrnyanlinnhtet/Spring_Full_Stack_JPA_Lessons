package com.join.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.join.dto.GroupByWithCategory;
import com.join.dto.SupplierDto;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectionTest extends AbstractTest {
	@Order(1)
	@Test
	void projection_test() {

		var JPQL = """
				SELECT new com.join.dto.SupplierDto(s.id,s.name,s.phone)
				FROM  Supplier s
				JOIN s.product p
				WHERE p.name = :name
				""";

		var query = em.createQuery(JPQL, SupplierDto.class);
		query.setParameter("name", "Potato Chips");
		var list = query.getResultList();

		System.out.println(list);
	}

	@Order(2)
	@Test
	void group_by_test() {

		var JPQL = """
				SELECT new com.join.dto.GroupByWithCategory(c.name,COUNT(p.id))
				FROM Product p
				JOIN p.category c
				GROUP BY c.name
				ORDER BY c.name DESC
				""";

		var query = em.createQuery(JPQL, GroupByWithCategory.class);
		var list = query.getResultList();
		System.out.println(list);
	}

	@Order(3)
	@Test
	void having_test() {

		var JPQL = """
              SELECT new com.join.dto.GroupByWithCategory(c.name,COUNT(p.id))
              FROM Product p
              JOIN p.category c 
              GROUP BY c.name
              HAVING COUNT(p.id) <> 1 
              ORDER BY c.name DESC 
				""";
		var query = em.createQuery(JPQL,GroupByWithCategory.class);
	    query.getResultList().forEach(System.out::println);
	}

}
