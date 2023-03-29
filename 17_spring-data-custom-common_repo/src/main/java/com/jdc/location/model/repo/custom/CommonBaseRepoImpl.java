package com.jdc.location.model.repo.custom;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.model.entity.District;

import jakarta.persistence.EntityManager;

@Transactional(readOnly = true)
public class CommonBaseRepoImpl extends SimpleJpaRepository<District, Integer>
		implements CommonBaseRepo<District, Integer> {

	private EntityManager em;

	public CommonBaseRepoImpl(JpaEntityInformation<District, Object> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public List<District> search(String jpql, Map<String, Object> params) {
		var query = em.createQuery(jpql, District.class);

		for (var param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}

}
