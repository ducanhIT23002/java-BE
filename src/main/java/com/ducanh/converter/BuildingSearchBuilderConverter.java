package com.ducanh.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ducanh.builder.BuildingSearchBuilder;
import com.ducanh.utils.MapUtils;

@Component
public class BuildingSearchBuilderConverter {
   public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> typeCode) {
	   BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
			    .setName(MapUtils.getObject(params, "name", String.class))
			    .setFloorArea(MapUtils.getObject(params, "floorArea", Long.class))
			    .setWard(MapUtils.getObject(params, "ward", String.class))
			    .setStreet(MapUtils.getObject(params, "street", String.class))
			    .setDistrictId(MapUtils.getObject(params, "districtcode", Long.class))
			    .setNumberOfBasement(MapUtils.getObject(params, "numberofbasement", Integer.class))
			    .setTypeCode(typeCode)
			    .setManagerName(MapUtils.getObject(params, "managername", String.class))
			    .setManagerPhoneNumber(MapUtils.getObject(params, "managerphonenumber", String.class))
			    .setRentPriceTo(MapUtils.getObject(params, "rentPriceTo", Long.class))
			    .setRentPriceFrom(MapUtils.getObject(params, "rentPriceFrom", Long.class))
			    .setAreaFrom(MapUtils.getObject(params, "areaFrom", Long.class))
			    .setAreaTo(MapUtils.getObject(params, "areaTo", Long.class))
			    .setStaffId(MapUtils.getObject(params, "staffId", Long.class))
			    .build();

			return buildingSearchBuilder;
   }
}
