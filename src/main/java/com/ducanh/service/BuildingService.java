package com.ducanh.service;

import java.util.List;
import java.util.Map;

import com.ducanh.model.BuildingTypeDTO;

public interface BuildingService {
	   List<BuildingTypeDTO> findAll(Map<String, Object> param, List<String> typecode);
}
