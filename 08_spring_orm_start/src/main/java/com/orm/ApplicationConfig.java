package com.orm;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.orm")
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
		var bean = new LocalEntityManagerFactoryBean();
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		return bean;
	}

	@Bean
	public TransactionManager transactionManager(EntityManagerFactory emf) {
		var tx = new JpaTransactionManager(emf);
		return tx;
	}

}
