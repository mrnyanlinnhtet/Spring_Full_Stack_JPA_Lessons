package com.jdc.location.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jdc.location.model.dto.StateValueDto;
import com.jdc.location.model.dto.StateWithDistrictCountDto;
import com.jdc.location.model.entity.State;

public interface StateInfProjectionRepo extends JpaRepository<State, Integer> {

	@Query("""
			SELECT s.id id,s.name name,SIZE(s.district) districtCount
			FROM State s WHERE s.region = ?1
			""")
	List<StateWithDistrictCountDto> findByRegionToDistrictCount(String region);

	//StateIdWithDisplayName findOneById(int StateId);

	StateValueDto findOneById(int stateId);
}
