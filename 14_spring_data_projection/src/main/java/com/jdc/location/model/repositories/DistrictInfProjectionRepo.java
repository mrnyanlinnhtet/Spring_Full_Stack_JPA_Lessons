package com.jdc.location.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.location.model.dto.DistrictDto;
import com.jdc.location.model.entity.District;

public interface DistrictInfProjectionRepo extends JpaRepository<District, Integer> {

	List<DistrictDto> findByStateId(int stateId);
	
}
