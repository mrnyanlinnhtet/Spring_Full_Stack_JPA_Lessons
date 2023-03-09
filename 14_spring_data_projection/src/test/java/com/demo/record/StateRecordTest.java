package com.demo.record;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.repositories.StateRecordProjectionRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class StateRecordTest {

	@Autowired
	private StateRecordProjectionRepo repo;
	
	@Order(1)
	@Test
	void find_one_by_state_id() {
		var result = repo.findOneById(1);
		
		assertNotNull(result);
		System.out.println(result);
	}
}
