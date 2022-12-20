package com.ultron.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.ultron.member_ship.Contact;
import com.ultron.member_ship.Member;
import com.ultron.member_ship.Member.Role;

public class CascadeTest {
	
	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}
	
	@ParameterizedTest
	@CsvSource(value = "Aye Myat Mon,aye@123,aye123,MEMBER,0912345,aye@gmail.com")
	void test(String name,String username,String password,Role role,String phone,String email) {
		
		var em = emf.createEntityManager();
		var member = new Member(name, username, password, role);
		var contact = new Contact(phone, email);
		member.setContact(contact);
		
		em.getTransaction().begin();
		 em.persist(member);
		em.getTransaction().commit();
		
	}
	

	@AfterAll
	static void terminate() {
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}
}
