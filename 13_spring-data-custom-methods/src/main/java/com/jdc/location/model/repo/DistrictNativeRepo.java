package com.jdc.location.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jdc.location.model.entity.District;

public interface DistrictNativeRepo extends JpaRepository<District, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM district WHERE state_id = :stateId")
	List<District> findDistrictByNativeQuery(@Param("stateId") int stateId);

}
