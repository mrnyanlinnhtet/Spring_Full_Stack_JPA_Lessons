package com.query.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.JpaConfiguration;
import com.jdc.location.model.entity.State.Type;
import com.jdc.location.model.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@TestMethodOrder(OrderAnnotation.class)
public class StateRepoTest {

	@Autowired
	private StateRepo repo;

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "State,7", "Region,7", "Union,1" })
	void find_by_type_test(Type type, int size) {
		var result = repo.findByType(type);

		assertThat(result, hasSize(size));
	}

}
