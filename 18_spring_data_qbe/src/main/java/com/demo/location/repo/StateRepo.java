package com.demo.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.location.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {

}
