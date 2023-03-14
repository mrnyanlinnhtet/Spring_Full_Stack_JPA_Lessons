package com.jdc.location.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.location.model.entity.State;

public interface StateDynamicProjectionRepo extends JpaRepository<State, Integer> {
	
	<T> T findOneById(int state_id,Class<T> type);

}
