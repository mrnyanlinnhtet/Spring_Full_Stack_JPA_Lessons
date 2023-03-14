package com.jdc.location.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.record_dto.StateWithDistrictCountDto;

public interface NativeWithProjectionRepo extends JpaRepository<State, Integer> {
	
	@Query(name = "State.stateWithNativeCount",nativeQuery = true)
	StateWithDistrictCountDto findOneById(@Param("id") int id);

}
