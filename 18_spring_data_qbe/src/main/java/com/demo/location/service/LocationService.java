package com.demo.location.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.location.dto.DistrictDto;
import com.demo.location.dto.StateDto;
import com.demo.location.entity.District;
import com.demo.location.entity.State;
import com.demo.location.repo.DistrictRepo;
import com.demo.location.repo.StateRepo;

@Service
public class LocationService {

	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private DistrictRepo districtRepo;

	public Stream<State> findByRegionAsStream(String region) {
		return stateRepo.findBy(
				Example.of(new State(region), ExampleMatcher.matching().withIgnorePaths("id", "porpulation")),
				query -> query.stream());
	}

	public Optional<StateDto> findFirstRegion(String region) {
		return stateRepo.findBy(

				Example.of(new State(region), ExampleMatcher.matching().withIgnorePaths("id", "porpulation")),
				query -> query.project("id", "name", "region").as(StateDto.class).sortBy(Sort.by("name")).first());
	}

	public List<DistrictDto> findDistrictByStateId(int stateId) {
		var probe = new District();
		var state = new State();

		state.setId(stateId);
		probe.setState(state);

		var example = Example.of(probe, ExampleMatcher.matching().withIgnorePaths("id", "state.porpulation"));

		return districtRepo.findBy(example, query -> query.as(DistrictDto.class).all());
	}

}
