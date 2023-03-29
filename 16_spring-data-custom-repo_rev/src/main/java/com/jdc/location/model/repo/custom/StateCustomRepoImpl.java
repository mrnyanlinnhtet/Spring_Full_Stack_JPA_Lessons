package com.jdc.location.model.repo.custom;

import java.util.HashMap;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.entity.State.Type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class StateCustomRepoImpl implements StateCustomRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<State> search(Type type, String region, String name) {

		var sb = new StringBuilder("SELECT s FROM State s WHERE 1=1 ");
		var params = new HashMap<String, Object>();

		if (null != type) {
			sb.append(" AND s.type =: type");
			params.put("type", type);
		}

		if (StringUtils.hasLength(region)) {
			sb.append(" AND s.region =: region");
			params.put("region", region);
		}

		if (StringUtils.hasLength(name)) {
			sb.append(" AND LOWER(s.name) LIKE LOWER(:name)");
			params.put("name", name.toLowerCase().concat("%"));
		}

		var query = em.createQuery(sb.toString(), State.class);

		for (var param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}

}
