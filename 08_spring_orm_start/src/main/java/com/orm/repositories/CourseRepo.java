package com.orm.repositories;

import org.springframework.stereotype.Repository;

import com.orm.entities.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CourseRepo {

	@PersistenceContext(name = "JPU")
	private EntityManager em;

	@Transactional
	public Course createCourse(Course course) {
		em.persist(course);

		return course;
	}

}
