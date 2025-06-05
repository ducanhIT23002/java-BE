package com.ducanh.service.iml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducanh.model.BuildingTypeDTO;
import com.ducanh.repository.BuildingRepository;
import com.ducanh.repository.entity.BuildingEntity;
import com.ducanh.service.BuildingService;

@Service
public class BuildingServiceIml implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;

	
	@Override
	public List<BuildingTypeDTO> findAll(String name){
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name);
		List<BuildingTypeDTO> result = new ArrayList<BuildingTypeDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingTypeDTO building = new BuildingTypeDTO();
			building.setName(item.getName());
			building.setAddress(item.getName() + ",--"+ item.getWard()+"," + item.getStreet());
			result.add(building);
		}
		return result;
}
}
