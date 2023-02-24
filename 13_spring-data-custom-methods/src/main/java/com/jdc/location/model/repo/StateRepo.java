package com.jdc.location.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.entity.State.Type;

public interface StateRepo extends JpaRepository<State, Integer> {

	//Find by type
	List<State> findByType(Type type);

}
