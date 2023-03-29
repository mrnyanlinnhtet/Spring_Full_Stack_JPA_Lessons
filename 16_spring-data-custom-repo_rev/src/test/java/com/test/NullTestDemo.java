package com.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.model.JpaConfiguration;
import com.jdc.location.model.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class NullTestDemo {

	@Autowired
	private StateRepo repo;

	@Test
	void find_one_by_name_null_test() {
		assertThrows(IllegalArgumentException.class, () -> {
			repo.findOneByName(null);
		});

	}

}
