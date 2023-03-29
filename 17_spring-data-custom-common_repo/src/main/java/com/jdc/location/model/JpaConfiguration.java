package com.jdc.location.model;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.jdc.location.model.repo.custom.CommonBaseRepoImpl;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.jdc.location.model")
@EnableJpaRepositories(basePackages = "com.jdc.location.model.repo", repositoryBaseClass = CommonBaseRepoImpl.class)
public class JpaConfiguration {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws IOException {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setPackagesToScan("com.jdc.location.model.entity");
		bean.setJpaProperties(getProperties());
		return bean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Properties getProperties() throws IOException {
		var properties = new Properties();
		properties.load(getClass().getResourceAsStream("/jpa.properties"));
		return properties;
	}

}
