package com.orm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.orm.entities.Course;
import com.orm.repositories.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class WithoutPersistanceXmLConfigTest {

	@Autowired
	private CourseRepo repo;

	@ParameterizedTest
	@CsvSource({ "Java Basic,3,200000,Java SE Course,1" })
	void test1(String name, int duration, int fees, String description, long id) {

		var course = new Course(name, duration, fees, description);
		var result = repo.createCourse(course);

		assertNotNull(result);
		assertEquals(id, result.getId());
	}
}
