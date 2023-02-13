package com.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.entities.Course;

public interface CourseRepositroy extends JpaRepository<Course, Long> {

}
