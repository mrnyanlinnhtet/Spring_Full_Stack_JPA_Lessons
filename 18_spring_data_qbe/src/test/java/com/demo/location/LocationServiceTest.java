package com.demo.location;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.demo.location.service.LocationService;

@SpringBootTest
public class LocationServiceTest {

	@Autowired
	private LocationService service;

	@Test
	@Transactional
	@Disabled
	void find_by_region_stream_test() {
		var result = service.findByRegionAsStream("East");
		assertNotNull(result);
		assertEquals(2L, result.count());

	}
	
	@Test
	@Disabled
	void find_first_test() {
		var result = service.findFirstRegion("East");
		assertThat(result.get(),allOf(hasProperty("id",is(5))));
	}
	
	@Test
	void projection_test() {
		var result = service.findDistrictByStateId(1);
		assertThat(result, hasSize(8));
	}
	
	

}
