package com.jpa.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
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
import com.jpa.entities.State;
import com.jpa.entities.State.Type;
import com.jpa.repositories.StateRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class DeleteMethodTest {

	@Autowired
	private StateRepo repo;

	@Disabled
	@Order(1)
	@ParameterizedTest
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	@CsvSource(value = "1	Ayeyarwady	Region	Lower	Pathein	6184829	14", delimiter = '\t')
	void test_delete_by_entity(int id, String name, Type type, String region, String capital, int porpulation,
			long remain) {

		var state = new State(id, name, type, region, capital, porpulation);
		repo.delete(state);

		assertThat(repo.count(), is(remain));

	}

	@Disabled
	@Order(2)
	@ParameterizedTest
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	@CsvSource("1,14")
	void test_delete_by_id(int id, long remain) {
		repo.deleteById(id);
		assertThat(repo.count(), is(remain));

	}

	@Disabled
	@Order(3)
	@Test
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	void test_delete_all() {
		repo.deleteAll();
		assertThat(repo.count(), is(0L));
	}

	@Disabled
	@Order(4)
	@Test
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	void test_delete_all_batch() {
		repo.deleteAllInBatch();
		assertThat(repo.count(), is(0L));
	}

	@Disabled
	@Order(5)
	@ParameterizedTest
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	@CsvSource(value = "1,2,3,5	11", delimiter = '\t')
	void test_delete_all_by_ids(String ids, long remain) {
		var array = ids.split(",");
		var list = Arrays.stream(array).map(Integer::parseInt).toList();
		repo.deleteAllById(list);

		assertThat(repo.count(), is(remain));
	}

	@Disabled
	@Order(6)
	@ParameterizedTest
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	@CsvSource(value = "1,2,3,5	11", delimiter = '\t')
	void test_batch_delete_all_by_ids(String ids, long remain) {
		var array = ids.split(",");
		var list = Arrays.stream(array).map(Integer::parseInt).toList();
		repo.deleteAllByIdInBatch(list);

		assertThat(repo.count(), is(remain));
	}

	@Order(7)
	@ParameterizedTest
	@Sql(scripts = { "/init_state.sql", "/load_data.sql" })
	@MethodSource("entitiesForDelete")
	void test_delete_all_by_entities(List<State> state, long remain) {
		repo.deleteAll(state);
		assertThat(repo.count(), is(remain));
	}

	static Stream<Arguments> entitiesForDelete() {
		return Stream
				.of(Arguments.of(List.of(new State(1, "Ayeyarwady", Type.Region, "Lower", "Pathein", 6184829)), 14));
	}
}
