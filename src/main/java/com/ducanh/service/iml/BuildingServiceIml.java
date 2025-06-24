package com.ducanh.service.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Override
	public List<BuildingTypeDTO> findAll(Map<String, Object> param, List<String> typecode){
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(param,typecode);
		List<BuildingTypeDTO> result = new ArrayList<BuildingTypeDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingTypeDTO building = new BuildingTypeDTO();
			building.setName(item.getName());
			DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictId());
			building.setAddress(item.getName() + ","+ item.getWard()+"," + item.getStreet() + " " + districtEntity.getName());
			List<RentAreaEntity> rentAreaEntity = rentAreaRepository.findValueByBuildingId(item.getId());
			
			//java7 : ko dung stream
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < rentAreaEntity.size(); i++) {
			    RentAreaEntity ra = rentAreaEntity.get(i);
			    sb.append( ra.getValue() );
			    if (i < rentAreaEntity.size() - 1) {
			        sb.append(",");
			    }
			}
			String rentvalue = sb.toString();
			//java7 : ko dung stream

			building.setRentValue(rentvalue);
			result.add(building);
		}
		return result;
}
}
