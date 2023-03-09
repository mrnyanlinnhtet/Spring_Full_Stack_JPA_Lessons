package com.jdc.location.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.record_dto.StateRecordDto;

public interface StateRecordProjectionRepo extends JpaRepository<State, Integer> {

	StateRecordDto findOneById(int stateId);

}
