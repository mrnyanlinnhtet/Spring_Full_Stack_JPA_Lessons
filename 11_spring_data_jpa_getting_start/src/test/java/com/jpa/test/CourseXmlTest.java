package com.jpa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jpa.entities.Course;
import com.jpa.repositories.CourseRepositroy;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class CourseXmlTest {

	@Autowired
	private CourseRepositroy repo;

	@ParameterizedTest
	@CsvSource({ "Spring Online Course,6,400000,1" })
	void course_save_test(String name, int duration, int fees, long id) {

		var course = new Course(name, duration, fees);
		var result = repo.save(course);

		assertNotNull(course);
		assertEquals(id, result.getId());
	}

}
