package com.demo.native_query;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.repositories.NativeWithProjectionRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
public class NativeQueryTest {
	
	@Autowired
	private NativeWithProjectionRepo repo;

	@Test
	 void test() {
		var result = repo.findOneById(1);
		assertNotNull(result);
		System.out.println(result);
	 }
}
