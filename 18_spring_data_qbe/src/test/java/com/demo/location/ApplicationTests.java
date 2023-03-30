package com.demo.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.demo.location.entity.State;
import com.demo.location.repo.StateRepo;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private StateRepo repo;

	@Test
	void search_find_by_region_test() {

		var probe = new State();
		probe.setRegion("East");
		probe.setCapital("Taunggyi");
		var example = Example.of(probe,ExampleMatcher.matching().withIgnorePaths("id","porpulation"));
		var result = repo.findAll(example);

		assertThat(result, hasSize(1));

	}

}
