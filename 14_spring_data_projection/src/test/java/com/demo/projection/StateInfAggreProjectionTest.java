package com.demo.projection;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.repositories.StateProjectionRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class StateInfAggreProjectionTest {

	@Autowired
	private StateProjectionRepo repo;

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "East" })
	void find_by_region_to_district_count(String region) {
		var result = repo.findByRegionToDistrictCount(region);

		for (var dto : result) {
			dto.show();
		}
	}
}
