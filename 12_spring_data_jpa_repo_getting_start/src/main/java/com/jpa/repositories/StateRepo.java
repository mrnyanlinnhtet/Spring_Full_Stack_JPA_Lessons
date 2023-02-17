package com.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.entities.State;

public interface StateRepo extends JpaRepository<State, Integer> {

}
