package com.demo.projection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.repositories.DistrictProjectionRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class DistrictInfProjectionTest {

	@Autowired
	private DistrictProjectionRepo repo;

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "1,8" })
	void find_by_state_id_test(int stateId, int districtDataCount) {

		var result = repo.findByStateId(stateId);
		assertThat(result, hasSize(districtDataCount));
	}
}
