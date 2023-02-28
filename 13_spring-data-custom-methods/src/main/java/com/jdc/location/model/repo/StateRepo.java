package com.jdc.location.model.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.entity.State.Type;

@Transactional(readOnly = true)
public interface StateRepo extends JpaRepository<State, Integer> {

	// Find by type
	List<State> findByType(Type type);

	// Stream By Type
	Stream<State> streamByType(Type type);

	// Count By Region
	long countByRegion(String region);

	// Exists By Region
	boolean existsByRegion(String region);

	// Find One
	State findOneByName(String name);

	// Find First 3 By Type
	List<State> findFirst3ByType(Type type);

	// Find Limit By Type
	List<State> findDistinctByType(Type type);

	// Remove By Type
	@Transactional
	void removeByType(Type type);

	// Count By Type
	long countByType(Type type);
}
