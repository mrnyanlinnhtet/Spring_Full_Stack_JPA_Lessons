package com.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpql.entities.Category;

public class StaticQueryTest {

	 static EntityManagerFactory emf;
	 
	 @BeforeAll
	 public static void init() {
		 emf = Persistence.createEntityManagerFactory("JPU");
	 }
	 
	 @Test
	 public void get_count() {
		 
		 var em = emf.createEntityManager();
		 var query = em.createNamedQuery("Category.getCount",Long.class);
		 var count = query.getSingleResult();
		 
		 assertEquals(3,count);
		 
	 }
	 
	 @Test
	 public void get_all_data() {
		 var em = emf.createEntityManager();
		 var query = em.createNamedQuery("Category.getAll",Category.class);
		 var stream = query.getResultStream();
		 stream.forEach((a)->{
			 System.out.println("Category : " + a.getName());
		 });
	 }
	 
	 
	 @Test
	 public void update_data() {
		 var em = emf.createEntityManager();
		 
		 em.getTransaction().begin();
		 var query = em.createNamedQuery("Category.updateCategoryById");
		 query.setParameter("name", "Mens");
		 query.setParameter("id", 1);
		 var count = query.executeUpdate();
		 
		 assertEquals(1,count);
		 
		 
		 em.getTransaction().commit();
	 }
	 
	 public static void terminate() {
		 if(emf != null && emf.isOpen()) {
			 emf.close();
		 }
	 }
}
