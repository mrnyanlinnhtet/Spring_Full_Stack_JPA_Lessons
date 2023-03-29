package com.jdc.location.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.location.model.entity.District;
import com.jdc.location.model.repo.DistrictRepo;

@Service
public class CommonRepoService {

	@Autowired
	private DistrictRepo repo;

	public List<District> search(String region, Integer stateId, String name) {
		var sb = new StringBuffer("SELECT d FROM District d WHERE 1 = 1 ");
		var params = new HashMap<String, Object>();

		if (StringUtils.hasLength(region)) {
			sb.append(" AND d.state.region =: region");
			params.put("region", region);
		}

		if (null != stateId && stateId > 0) {
			sb.append(" AND d.state.id =: stateId");
			params.put("stateId", stateId);
		}

		if (StringUtils.hasLength(name)) {
			sb.append(" AND LOWER(d.name)  = LOWER(:name)");
			params.put("name", name.toLowerCase().concat("%"));

		}

		return repo.search(sb.toString(), params);
	}

}
