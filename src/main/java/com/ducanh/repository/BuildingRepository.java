package com.ducanh.repository;

import java.util.List;

import com.ducanh.repository.entity.BuildingEntity;

public interface BuildingRepository {
	 List<BuildingEntity> findAll(String name);
}
