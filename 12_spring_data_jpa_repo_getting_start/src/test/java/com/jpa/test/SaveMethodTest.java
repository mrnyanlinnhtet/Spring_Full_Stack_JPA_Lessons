package com.jpa.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jpa.configuration.ApplicationConfig;
import com.jpa.entities.State;
import com.jpa.entities.State.Type;
import com.jpa.repositories.StateRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class SaveMethodTest {

	@Autowired
	private StateRepo repo;

	@Order(1)
	@ParameterizedTest
	@Sql(scripts = "/init_state.sql")
	@CsvFileSource(resources = "/save/insert_data.txt", delimiter = '\t')
	void test_insert(String name, Type type, String region, String capital, int porpulation) {

		var state = new State(name, type, region, capital, porpulation);
		var result = repo.save(state);

		assertThat(result, hasProperty("id", is(1)));
	}

	@Order(2)
	@ParameterizedTest
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	@CsvSource("1,Test Name,Region,East,Test Capital,10000")
	void test_update(int id, String name, Type type, String region, String capital, int porpulation) {

		var state = new State(id, name, type, region, capital, porpulation);
		repo.save(state);
		var result = repo.findById(id).get();

		assertThat(result,
				allOf(notNullValue(), hasProperty("id", is(id)), hasProperty("name", is(name)),
						hasProperty("type", is(type)), hasProperty("region", is(region)),
						hasProperty("capital", is(capital)), hasProperty("porpulation", is(porpulation))));
	}
	
}
