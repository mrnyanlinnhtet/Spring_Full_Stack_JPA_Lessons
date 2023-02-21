package com.jpa.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jpa.configuration.ApplicationConfig;
import com.jpa.entities.State.Type;
import com.jpa.repositories.StateRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
@TestMethodOrder(OrderAnnotation.class)
public class FindMethodTest {

	@Autowired
	private StateRepo repo;

	@Order(1)
	@Test
	void count_test() {
		var count = repo.count();
		assertThat(count, is(15L));
	}

	@Order(2)
	@ParameterizedTest
	@CsvSource({ "1,true", "15,true", "16,false" })
	void exist_test(int id, boolean expected) {
		var exist = repo.existsById(id);
		assertThat(exist, is(expected));
	}

	@Order(3)
	@Test
	void find_all_test() {
		var allResult = repo.findAll();
		assertThat(allResult, allOf(notNullValue(), hasSize(15)));
	}

	@Order(4)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = { "1	Ayeyarwady	Region	Lower	Pathein	6184829",
			"2	Bago	Region	Lower	Bago	4867373" })
	void find_all_by_id(int id, String name, Type type, String region, String capital, int porpulation) {
		var result = repo.findById(id).get();
		assertThat(result,
				allOf(hasProperty("id", is(id)), hasProperty("name", is(name)), hasProperty("type", is(type)),
						hasProperty("region", is(region)), hasProperty("capital", is(capital)),
						hasProperty("porpulation", is(porpulation))));
	}

	@Order(5)
	@ParameterizedTest
	@MethodSource("findAllIds")
	void find_all_by_ids(List<Integer> ids, int count) {
		var allResult = repo.findAllById(ids);

		assertThat(allResult, hasSize(count));
	}

	static Stream<Arguments> findAllIds() {
		return Stream.of(Arguments.of(List.of(1,2,3), 3));
	}

}
