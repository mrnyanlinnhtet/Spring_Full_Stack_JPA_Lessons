package com.demo.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.demo.location.entity.State;
import com.demo.location.repo.StateRepo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	private StateRepo repo;

	@Order(1)
	@Test
	void search_find_by_capital_region_test() {

		var probe = new State();
		probe.setRegion("East");
		probe.setCapital("Taunggyi");
		var example = Example.of(probe, ExampleMatcher.matching().withIgnorePaths("id", "porpulation"));
		var result = repo.findAll(example);

		assertThat(result, hasSize(1));

	}

	@Order(2)
	@Test
	void find_by_start_ignore_case_test() {
		var probe = new State();
		probe.setName("M");

		var example = Example.of(probe,
				ExampleMatcher.matching().withIgnorePaths("id", "porpulation").withIgnoreNullValues()
						.withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.STARTING));

		var result = repo.findAll(example);

		assertThat(result, hasSize(3));
	}

	@Order(3)
	@Test
	void find_by_region_and_name_ignore_like_test() {

		var probe = new State();
		probe.setRegion("East");
		probe.setName("Sha");

		var example = Example.of(probe,
				ExampleMatcher.matching().withIgnorePaths("id", "porpulation")
						.withMatcher("region", matcher -> matcher.exact())
						.withMatcher("name", matcher -> matcher.ignoreCase().startsWith()));

		var result = repo.findAll(example);

		assertThat(result, hasSize(1));

	}

}
