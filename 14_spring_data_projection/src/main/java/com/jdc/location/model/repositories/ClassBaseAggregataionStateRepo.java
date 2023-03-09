package com.jdc.location.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.record_dto.StateWithDistrictCountDto;

public interface ClassBaseAggregataionStateRepo extends JpaRepository<State, Integer> {

	@Query("""
			SELECT NEW com.jdc.location.model.record_dto.StateWithDistrictCountDto(
			s.id,s.name,SIZE(s.district) ) FROM State s WHERE s.id = :id 
			""")
	StateWithDistrictCountDto findOneById(@Param("id") int StateId);
}
