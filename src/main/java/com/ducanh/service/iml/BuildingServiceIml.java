package com.ducanh.service.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducanh.builder.BuildingSearchBuilder;
import com.ducanh.converter.BuildingDTOConverter;
import com.ducanh.converter.BuildingSearchBuilderConverter;
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
	private BuildingDTOConverter buildingSearchConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	@Override
	public List<BuildingTypeDTO> findAll(Map<String, Object> param, List<String> typecode){
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(param, typecode);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
		List<BuildingTypeDTO> result = new ArrayList<BuildingTypeDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingTypeDTO building = buildingSearchConverter.toBuildingTypeDTO(item);
			result.add(building);
		}
		return result;
}
}
