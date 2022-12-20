package com.ultron.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ultron.member_ship.Account;

public class EntityManagerSynTest {
	
	static EntityManagerFactory emf;
	
	@BeforeAll
   static void init() {
		emf = Persistence.createEntityManagerFactory("JPU");
   }
	
	
	@Test
	void test() {
		var operationOne = getThreadOne();
		var operationTwo = getThreadTwo();
		
		operationOne.start();
		operationTwo.start();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		
	}
	
	private Thread getThreadOne() {
		
		return new Thread(()->{
			var em = emf.createEntityManager();
			var account = em.find(Account.class, 1);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		   em.getTransaction().begin();
		   System.out.println("Before Operation ::=> Balace of %s is %d."
				   .formatted(account.getName(),account.getBalance()));
		   
		
		   account.setBalance(account.getBalance() + 100_000);
		   em.flush();
		   System.out.println("After Operation ::=> Balace of %s is %d."
				   .formatted(account.getName(),account.getBalance()));
		   
		   em.getTransaction().commit();
		});
	
	}
	
	
private Thread getThreadTwo() {
		
		return new Thread(()->{
			var em = emf.createEntityManager();
			var account = em.find(Account.class, 1);
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		   em.getTransaction().begin();
		   System.out.println("Before Operation ::=> Balace of %s is %d."
				   .formatted(account.getName(),account.getBalance()));
		   
		   em.refresh(account);
		   System.out.println("Before Operation(Refresh) ::=> Balace of %s is %d."
				   .formatted(account.getName(),account.getBalance()));
		   
		   account.setBalance(account.getBalance() - 500_00);
		   System.out.println("After Operation ::=> Balace of %s is %d."
				   .formatted(account.getName(),account.getBalance()));
		   
		   em.getTransaction().commit();
		});
	
	}

   
	@AfterAll
   static void terminate() {
	   if(emf != null & emf.isOpen()) {
		   emf.close();
	   }
   }
}
