package com.demo.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.location.entity.District;

public interface DistrictRepo extends JpaRepository<District, Integer>{

}
