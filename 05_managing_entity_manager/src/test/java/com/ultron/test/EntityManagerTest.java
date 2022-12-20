package com.ultron.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.ultron.member_ship.Member;
import com.ultron.member_ship.Member.Role;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntityManagerTest {

	static EntityManagerFactory emf;

	@BeforeAll
	static void start() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}

	@Order(1)
	@ParameterizedTest
	@CsvSource("Aye Myat Mon,aye@124,p@ssw@rd,MEMBER")
	void creationTest(String name, String userName, String password, Role role) {
		var em = emf.createEntityManager();
		// Transient state
		var member = new Member(name, userName, password, role);

		em.getTransaction().begin();
		//To Be Managed State
		em.persist(member);
		
		//Check is entity contain in PC 
		assertTrue(em.contains(member));
		
		//To Be Detach State
		em.detach(member);

		//Check is not  entity contain in PC
	    assertFalse(em.contains(member));
	    
	    member.setPassword("123Update");
	    
	    
	    //To be managed state again
	    member =em.merge(member);
	  
	    assertTrue(em.contains(member));
	    
	    member.setPassword("updatePassword123");
	    
	    
		//Synchronize with database
		em.getTransaction().commit();
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(ints = 1)
	void find_test(int id) {
		
		 var em = emf.createEntityManager();
		 var member = em.find(Member.class, id);
		 
		 assertNotNull(member);
		 assertTrue(em.contains(member));
		 
		 em.detach(member);
		 
		 assertAll(
				 ()->assertEquals("Nyan Linn Htet", member.getName()),
				 ()->assertEquals("root", member.getUserName()),
				 ()->assertEquals(Role.ADMIN, member.getRole()),
				 ()->assertEquals("admin", member.getPassword())
				 );
	}
	
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = 1)
	void getReference_test(int id) {
		
		var em = emf.createEntityManager();
		var member = em.getReference(Member.class,id);
		
		assertNotNull(member);
		assertTrue(em.contains(member));
		
		em.detach(member);
		
		assertAll(
			()-> assertThrows(LazyInitializationException.class,()->member.getName()),
			()-> assertThrows(LazyInitializationException.class,()->member.getUserName()),
			()-> assertThrows(LazyInitializationException.class,()->member.getPassword()),
			()-> assertThrows(LazyInitializationException.class,()->member.getRole())
				);
	}
	
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(ints = 3)
	void find_not_found_test(int id) {
		
		var em = emf.createEntityManager();
		var member = em.find(Member.class,id);
		assertNull(member);
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(ints = 3)
	void getReference_not_fount_test(int id) {
		
		var em = emf.createEntityManager();
		var member = em.getReference(Member.class,id);
		assertNotNull(member);
		
		assertThrows(EntityNotFoundException.class,member::getName);
	}
	
	@Order(6)
	@ParameterizedTest
	@ValueSource(ints = 1)
	void test_for_lazy_fetch_error(int id){
		
		var em = emf.createEntityManager();
		 var member = em.find(Member.class,id);
		 em.detach(member);
		 
		 //assertThrows(LazyInitializationException.class,()->member.getTags().size());
		 
	}

	@AfterAll
	static void terminate() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

}
