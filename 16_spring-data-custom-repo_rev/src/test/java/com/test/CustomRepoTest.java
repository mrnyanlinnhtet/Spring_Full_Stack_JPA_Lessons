package com.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.JpaConfiguration;
import com.jdc.location.model.entity.State.Type;
import com.jdc.location.model.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class CustomRepoTest {

	@Autowired
	private StateRepo repo;

	@ParameterizedTest
	@CsvSource({ ",,,15","State,,,7",",West,,2",",,ka,3" })
	void state_search_custom_repo_test(Type type, String region, String name, int size) {
		var result = repo.search(type, region, name);

		assertThat(result, hasSize(size));
	}

}
