package com.demo.projection;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.configuration.ApplicationConfig;
import com.jdc.location.model.repositories.StateInfProjectionRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class StateIdWithDisplayNameTest {

	@Autowired
	private StateInfProjectionRepo repo;

	@Order(1)
	@Test
	void state_id_with_display_name_test() {
		 var result = repo.findOneById(1);
		assertThat(result, allOf(notNullValue(), hasProperty("id", is(1)),hasProperty("displayName", is("Ayeyarwady Region"))));
	}

}
