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
import com.jdc.location.model.repo.StateNativeRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "/init-tables.sql", "/load-data.sql" })
public class StateNativeRepoTest {

	@Autowired
	private StateNativeRepo repo;

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "State,7", "Region,7", "Union,1" })
	void find_by_native_query_test(String type, int size) {
		var result = repo.findWithNativeSQL(type);
		assertThat(result, hasSize(size));
	}
}
