package com.ducanh.service.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducanh.converter.BuildingSearchConverter;
import com.ducanh.model.BuildingTypeDTO;
import com.ducanh.repository.BuildingRepository;
import com.ducanh.repository.DistrictRepository;
import com.ducanh.repository.RentAreaRepository;
import com.ducanh.repository.entity.BuildingEntity;
import com.ducanh.repository.entity.DistrictEntity;
import com.ducanh.repository.entity.RentAreaEntity;
import com.ducanh.service.BuildingService;

@Service
public class BuildingServiceIml implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingSearchConverter buildingSearchConverter;
	
	@Override
	public List<BuildingTypeDTO> findAll(Map<String, Object> param, List<String> typecode){
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(param,typecode);
		List<BuildingTypeDTO> result = new ArrayList<BuildingTypeDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingTypeDTO building = buildingSearchConverter.toBuildingTypeDTO(item);
			result.add(building);
		}
		return result;
}
}
