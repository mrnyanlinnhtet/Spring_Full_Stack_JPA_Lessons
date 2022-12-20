package com.ultron.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.ultron.member_ship.Member;
import com.ultron.member_ship.Member.Role;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersistanceOperationTest extends OperationTest {

	@Order(1)
	@ParameterizedTest
	@CsvSource("2,Aye Myat Mon,aye@112,aye124,GOLD_MEMBER")
	public void persist_of_transient_state(int id,String name, String userName, String password, Role role) {
		
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		var member  = new Member(name, userName, password, role);
		assertFalse(em.contains(member));
		
		em.persist(member);
		assertEquals(id,member.getId());
		
		assertTrue(em.contains(member));
		
		
		em.getTransaction().commit();
		
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(ints = 1)
	public void persist_of_managed_state(int id) {
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		 var member = em.find(Member.class,id);
		 assertTrue(em.contains(member));
		 
		 assertDoesNotThrow(()->em.persist(member));
		
		 assertTrue(em.contains(member));
		
		em.getTransaction().commit();
	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = 1)
	public  void persist_of_detach_state(int id) {
	  var em = emf.createEntityManager();
	  var member = em.find(Member.class,id);
	  assertTrue(em.contains(member));
	  
	  em.detach(member);
	  assertFalse(em.contains(member));
	  
	  assertThrows(PersistenceException.class,()->{
		  
		  em.getTransaction().begin();
		  em.persist(member);
		  em.getTransaction().commit();
		  
	  });
	  
	  
		
	}
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(ints = 1)
	public void persist_of_remove_state(int id) {
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		var member = em.find(Member.class,id);
		assertTrue(em.contains(member));
		em.remove(member);
		assertFalse(em.contains(member));
				
		assertDoesNotThrow(()->{

			em.persist(member);
			em.getTransaction().commit();
			
			assertTrue(em.contains(member));
		});
		
		
		  
	  }
}
