package com.demo.record;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.repositories.ClassBaseAggregataionStateRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
public class StateWithDistrictCountTest {

	@Autowired
	private ClassBaseAggregataionStateRepo repo;

	@Test
	void state_with_district_count() {
		var result = repo.findOneById(1);
		assertNotNull(result);
		System.out.println(result);
	}
}
