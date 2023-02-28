package com.query.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.JpaConfiguration;
import com.jdc.location.model.entity.State.Type;
import com.jdc.location.model.repo.StateRepo;

import jakarta.transaction.Transactional;

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

	@Order(2)
	@ParameterizedTest
	@CsvSource({ "State,7", "Region,7", "Union,1" })
	@Transactional
	void find_by_stream_test(Type type, long size) {
		var result = repo.streamByType(type);
		assertThat(result.count(), is(size));
	}

	@Order(3)
	@ParameterizedTest
	@CsvSource({ "Lower,3", "Center,0" })
	void count_by_region_test(String region, long count) {
		var result = repo.countByRegion(region);
		assertThat(result, is(count));
	}

	@Order(4)
	@ParameterizedTest
	@CsvSource({ "Lower,true", "Center,false" })
	void exists_by_region_test(String region, boolean exists) {
		var result = repo.existsByRegion(region);
		assertThat(result, is(exists));
	}

	@Order(5)
	@ParameterizedTest
	@ValueSource(strings = { "Yangon", "Bago" })
	void find_one_by_name_test(String name) {
		var result = repo.findOneByName(name);
		assertThat(result, notNullValue());
	}

	@Order(6)
	@ParameterizedTest
	@CsvSource({ "State,3", "Region,3", "Union,1" })
	void find_3_limit_by_type_test(Type type, int size) {
		var result = repo.findFirst3ByType(type);
		assertThat(result.size(), is(size));
	}

	@Order(7)
	@ParameterizedTest
	@CsvSource({ "State,7", "Region,7", "Union,1" })
	void find_distinct_by_type_test(Type type, int size) {
		var result = repo.findDistinctByType(type);
		assertThat(result.size(), is(size));
	}
	
	@Order(8)
	@ParameterizedTest
	@ValueSource(strings = {"State","Region","Union"})
	void remove_by_type_test(Type type) {
	   repo.removeByType(type);
	   var count = repo.countByType(type);
	   assertThat(count, is(0L));
	}

}
