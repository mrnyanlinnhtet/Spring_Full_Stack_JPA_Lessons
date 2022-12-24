package com.jpql.test;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.jpql.dto.MinMaxDto;
import com.jpql.dto.SumDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class AggregationMethodTest extends ReferenceAbleTest {

	@Order(1)
	@Test
	void min_max_test() {

		var JPQL = """
				SELECT new com.jpql.dto.MinMaxDto(c.id,c.name,MIN(s.saleDate),MAX(s.saleDate))
				FROM Sale s JOIN  s.customer c
				GROUP BY c.id,c.name
				ORDER BY c.name DESC
						""";

		var query = em.createQuery(JPQL, MinMaxDto.class);
		var list = query.getResultList();
		System.out.println(list);
	}

	@Order(2)
	@Test
	void sum_test() {

		em.createQuery(
				"""
		SELECT new com.jpql.dto.SumDto(t.id,t.name,p.id,p.name,p.price,SUM(ps.quantity))FROM ProductSale ps
		JOIN ps.product p
		JOIN ps.sale.customer.address.township t
		GROUP BY  t.id,t.name,p.id,p.name,p.price
		ORDER BY t.name,p.name"""
										,
				SumDto.class).getResultList().forEach(System.out::println);;
		
		
	}
}
