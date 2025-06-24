package com.ducanh.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ducanh.model.BuildingTypeDTO;
import com.ducanh.repository.DistrictRepository;
import com.ducanh.repository.RentAreaRepository;
import com.ducanh.repository.entity.BuildingEntity;
import com.ducanh.repository.entity.DistrictEntity;
import com.ducanh.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper; 
	
     public BuildingTypeDTO toBuildingTypeDTO(BuildingEntity item) {
    	 
			BuildingTypeDTO building = modelMapper.map(item,BuildingTypeDTO.class);
			
//			building.setName(item.getName());
			
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
			
			return building;
     }
}
