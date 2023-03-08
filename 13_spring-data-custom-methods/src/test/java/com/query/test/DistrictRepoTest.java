package com.query.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.JpaConfiguration;
import com.jdc.location.model.repo.DistrictRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "/init-tables.sql", "/load-data.sql" })
public class DistrictRepoTest {

	@Autowired
	private DistrictRepo repo;

	@Order(1)
	@ParameterizedTest
	@CsvSource(value = "West,9")
	void find_by_state_region_test(String region, int size) {
		var result = repo.findByStateRegion(region);
		assertThat(result, hasSize(size));
	}

	@Order(2)
	@ParameterizedTest
	@CsvSource(value = { "Hpa,2" })
	void find_by_state_name_test(String name, int size) {
		var result = repo.findByNameStartingWithIgnoreCaseOrderByName(name);
		assertThat(result, hasSize(size));
	}

	@Order(3)
	@ParameterizedTest
	@CsvSource(value = { "13,mon,3" })
	void find_by_stateId_and_name_test(int stateId, String name, int size) {
		var result = repo.findByStateIdAndNameStartingWithIgnoreCaseOrderByName(stateId, name);
		assertThat(result, hasSize(size));
	}

	@Order(4)
	@ParameterizedTest
	@CsvSource(value = { "13,mon,3" })
	void find_for_state_with_name_query_test(int stateId, String name, int size) {
		var result = repo.findForState(stateId, name.concat("%"));
		assertThat(result, hasSize(size));
	}

	@Order(5)
	@ParameterizedTest
	@CsvSource(value = { "13,mon,3" })
	void find_with_query_annotation(int stateId, String name, int size) {
		var result = repo.findWithQueryAnnotation(stateId, name.concat("%"));
		assertThat(result, hasSize(size));

	}
}
