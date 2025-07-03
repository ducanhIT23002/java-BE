package com.ducanh.api;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ducanh.model.BuildingTypeDTO;
import com.ducanh.repository.BuildingRepositorySprDataJpa;
import com.ducanh.repository.entity.BuildingEntity;
import com.ducanh.service.BuildingService;

@RestController
public class NewAPI {

    @Autowired
    private BuildingRepositorySprDataJpa buildingRepositorySprDataJpa;
    
    @Autowired
    private BuildingService buildingService;

	@Autowired
	private ModelMapper modelMapper; 
	
//    @GetMapping("/building")
//    public Object testTour(@RequestParam Map<String, Object> param,@RequestParam(name = "typecode",required = false) List<String> typecode) {
//        List<BuildingTypeDTO> result = buildingService.findAll(param,typecode);
//        return result;
//    }
//    public void validate(BuildingTypeDTO tourType) throws ExceptionSetting {
//    	if (tourType.getName() == null || tourType.getName() == "")
//    	throw new ExceptionSetting("hãy kiểm tra lại các field, có field đang bị null hoặc chưa có giá trị");
//    }
    @GetMapping("/api/building")
    public List<BuildingTypeDTO> getBuilding() {
    	List<BuildingTypeDTO> result = new ArrayList<BuildingTypeDTO>();
    	List<BuildingEntity>  item = buildingRepositorySprDataJpa.findAll();
        for (BuildingEntity entity : item) {
			BuildingTypeDTO building = modelMapper.map(entity,BuildingTypeDTO.class);
			result.add(building);
        }
        return result;
    }
}
