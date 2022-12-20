package com.ultron.test;

import java.time.LocalDate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ultron.entities.Company;
import com.ultron.entities.Member;

public class MemberTest {

	static EntityManagerFactory emf;

	@BeforeAll
	static void init() {

		emf = Persistence.createEntityManagerFactory("JPU");

	}

	@AfterAll
	static void terminate() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

	@Test
	void test_create_member() {

		var member1 = new Member("Nyan Linn Htet", "0944859894", "mr.nyanlinnhtet13@gmail.com", "USA",
				LocalDate.of(1998, 12, 8));

//		var member2 = new Member("Aye Myat Mon", "09890758076", "ms.ayemyatmon@gmail.com", "Yangon",
//				LocalDate.of(1997, 9, 14));
		
		
		var company = new Company("Stark Industry");
		
		

		var em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(member1);
		System.out.println("Memeber Id : >>>>>>>>>>>>>>>>>>>>>>" + member1.getId());
		
		em.persist(company);
		System.out.println("Company Id : >>>>>>>>>>>>>>>>>>>>>>" +  company.getId());
		
		
		

		em.getTransaction().commit();

	}

}
