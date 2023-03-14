package com.demo.dynamic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.dto.StateDto;
import com.jdc.location.model.entity.State;
import com.jdc.location.model.record_dto.StateRecordDto;
import com.jdc.location.model.repositories.StateDynamicProjectionRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class DynamicProjectionTest {
	
	@Autowired
	private StateDynamicProjectionRepo repo;

	@Order(1)
	@Test
	void test_entity() {
		var result = repo.findOneById(1, State.class);
		assertNotNull(result);
		assertEquals("Ayeyarwady", result.getName());
	}
	
	@Order(2)
	@Test
	void test_interface() {
		var result = repo.findOneById(1, StateDto.class);
		assertNotNull(result);
		assertEquals("Ayeyarwady", result.getName());
	}
	
	@Order(3)
	@Test
	void test_record() {
		var result = repo.findOneById(1, StateRecordDto.class);
		assertNotNull(result);
	}
}
