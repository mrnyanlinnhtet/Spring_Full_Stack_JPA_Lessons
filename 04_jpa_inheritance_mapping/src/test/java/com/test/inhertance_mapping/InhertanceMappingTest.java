package com.test.inhertance_mapping;

import java.awt.Color;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.ultron.ineritance.discriminator.AdminAccount;
import com.ultron.ineritance.discriminator.OfficeAccounts.Roles;

public class InhertanceMappingTest {

	static EntityManagerFactory emf;

	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
	}

	@AfterAll
	public static void terminate() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

     @ParameterizedTest
     @CsvSource({"Nyan Linn Htet,root,admin,ADMIN,true","Aye Myat Mon,aye@1,aye123,PROJECT_MANAGER,false"})
	public void test(String name, String username, String password,Roles role, boolean isAdmin) {
		
    	var em = emf.createEntityManager();
    	
    	 em.getTransaction().begin();
    	 
    	 var result = new AdminAccount(name, username, password, role, isAdmin);
    	 result.setFavColor(Color.DARK_GRAY);
    	 em.persist(result);
    	 em.getTransaction().commit();
	}

}
