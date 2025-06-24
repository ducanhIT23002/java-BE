package com.ducanh.repository;

import java.util.List;
import java.util.Map;

import com.ducanh.repository.entity.BuildingEntity;

public interface BuildingRepository {
	 List<BuildingEntity> findAll(Map<String, Object> param, List<String> typecode);
}
