package com.query.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.JpaConfiguration;
import com.jdc.location.model.repo.DistrictNativeRepo;

import jakarta.transaction.Transactional;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "/init-tables.sql", "/load-data.sql" })
public class DistrictNativeRepoTest {

	@Autowired
	private DistrictNativeRepo repo;

	@Transactional 
	@Order(1)
	@ParameterizedTest
	@CsvSource({ "1,8" })
	void find_district_by_native_query_test(int stateId, int size) {
		var result = repo.findDistrictByNativeQuery(stateId);
		assertThat(result, hasSize(size));

		for (var value : result) {
			System.out.println(value.getState().getName());
		}
	}

}
