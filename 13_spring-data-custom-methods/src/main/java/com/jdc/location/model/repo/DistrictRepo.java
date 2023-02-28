package com.jdc.location.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.model.entity.District;

@Transactional(readOnly = true)
public interface DistrictRepo extends JpaRepository<District, Integer> {

	// Find By State Region
	List<District> findByStateRegion(String region);

	// Find By State Name
	List<District> findByNameStartingWithIgnoreCaseOrderByName(String name);

	// Find By Sate Id and Name
	List<District> findByStateIdAndNameStartingWithIgnoreCaseOrderByName(int stateId, String name);

	// Find By Sate Id and Name with Name Query
	@Query(name = "District.findForState")
	List<District> findForState(@Param("stateId") int stateId, @Param("name") String name);
}
