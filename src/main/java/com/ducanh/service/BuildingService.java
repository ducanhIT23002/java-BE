package com.ducanh.service;

import java.util.List;

import com.ducanh.model.BuildingTypeDTO;

public interface BuildingService {
	   List<BuildingTypeDTO> findAll(String name);
}
