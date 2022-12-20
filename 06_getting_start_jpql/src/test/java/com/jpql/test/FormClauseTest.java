package com.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jpql.entities.Customer;

public class FormClauseTest extends ReferenceAbleTest {
	
	@ParameterizedTest
	@CsvSource("aun,094,1")
    void select_test(String name,String phone,int count) {
		
		var JPQL = "SELECT c FROM Customer c WHERE name LIKE :name AND phone LIKE :phone";
		
		var query = em.createQuery(JPQL,Customer.class);
		query.setParameter("name", name.concat("%"));
		query.setParameter("phone", phone.concat("%"));;
		
		var result = query.getResultList();
		
		assertEquals(count, result.size());
	
	 }
	
	
	@ParameterizedTest
	@CsvSource("Red,3")
	void member_of_test(String color,Long count) {
		
		var JPQL  = "SELECT COUNT(p) FROM Product p WHERE :color member of p.colors";
		
		var query = em.createQuery(JPQL,Long.class);
		query.setParameter("color", color);
		
		var result = query.getSingleResult();
		
		assertEquals(count, result);
		
	}
	
	
	@ParameterizedTest
	@CsvSource("2022-05-10,2022-05-15,5")
	void between_test(LocalDate from,LocalDate to,Long count) {
		var JPQL = "SELECT COUNT(s) FROM Sale s WHERE s.saleDate BETWEEN :from AND :to";
		
		var query = em.createQuery(JPQL,Long.class);
		    query.setParameter("from",from);
		    query.setParameter("to",to);
		    
		var result = query.getSingleResult();
		
		assertEquals(count,result);
	}
	
	

}
 