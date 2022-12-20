package com.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jpql.entities.Product;

public class ProductTest {
	
 static EntityManagerFactory emf;
	 
	 @BeforeAll
	 public static void init() {
		 emf = Persistence.createEntityManagerFactory("JPU");
	 }
	 
	 @ParameterizedTest
	 @CsvSource("f,2")
	 void searchProductByCategory(String category,int count) {
		 var em = emf.createEntityManager();
		 
		 var query = em.createNamedQuery("Product.findCategoryNameLike",Product.class);
		 query.setParameter(1,category.toLowerCase().concat("%"));
		var list = query.getResultList();
		
		assertEquals(count,list.size());
	 }
	 
	 
	 @AfterAll
	 public static void terminate() {
		 if(emf != null && emf.isOpen()) {
			 emf.close();
		 }
	 }

}
