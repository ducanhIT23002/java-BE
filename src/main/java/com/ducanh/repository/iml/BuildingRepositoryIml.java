package com.ducanh.repository.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ducanh.repository.BuildingRepository;
import com.ducanh.repository.entity.BuildingEntity;
import com.ducanh.utils.ConnectionJBPCUtil;
import com.ducanh.utils.NumberUtils;
import com.ducanh.utils.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class BuildingRepositoryIml implements BuildingRepository{

	    
	    public void joinTable(Map<String, Object> param, List<String> typecode, StringBuilder sql ) {
//	    	tìm building dựa tren staffId ta tìm table trung gian là assignment_building
	        String staffId = (String)param.get("staffId");
	        if(StringUtils.checkString(staffId) == true) {
	        	sql.append(" JOIN assignment_building ON assignment_building.buildingId = b.id ");
	        }
	        if (typecode != null && typecode.size() != 0) {
	        	sql.append(" JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");
	        	sql.append(" JOIN rent_type ON buildingrenttype.rentTypeId  = rent_type.id ");
	        }
	        String areaTo = (String)param.get("areaTo");
	        String areaFrom = (String)param.get("areaFrom");
	        if(StringUtils.checkString(areaFrom)== true || StringUtils.checkString(areaTo) == true) {
	        	sql.append(" JOIN rent_area ON rent_area.buildingId  = b.id ");
	        }
	    }
	    
	    public static void queryNomal(Map<String, Object> params, StringBuilder where) {
	        for (Map.Entry<String, Object> it : params.entrySet()) {
	            if (!it.getKey().equals("staffId") && !it.getKey().equals("typecode") &&
	                !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
	                
	                String value = it.getValue().toString();
	                if (StringUtils.checkString(value)) {
	                    if (NumberUtils.checkNumber(value) == true) {
	                        where.append(" AND b." + it.getKey() + " = " + value);
	                    }	 
	                    else {
		                	 where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%'"); 
		                }
	                }
	            }
	        }
	    }
	    

	    
	    public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
	        String staffId = (String) params.get("staffId");
	        if (StringUtils.checkString(staffId)) {
	            where.append(" AND assignmentbuilding.staffId = " + staffId);
	        }

	        String rentAreaTo = (String) params.get("areaTo");
	        String rentAreaFrom = (String) params.get("areaFrom");

	        if (StringUtils.checkString(rentAreaFrom) || StringUtils.checkString(rentAreaTo)) {
	            if (StringUtils.checkString(rentAreaFrom)) {
	                where.append(" AND rentarea.value >= " + rentAreaFrom);
	            }
	            if (StringUtils.checkString(rentAreaTo)) {
	                where.append(" AND rentarea.value <= " + rentAreaTo);
	            }
	        }
	        
	        String rentPriceTo = (String) params.get("rentPriceTo");
	        String rentPriceFrom = (String) params.get("rentPriceFrom");

	        if (StringUtils.checkString(rentPriceFrom) || StringUtils.checkString(rentPriceTo)) {
	            if (StringUtils.checkString(rentPriceFrom)) {
	                where.append(" AND b.rentprice >= " + rentPriceFrom);
	            }
	            if (StringUtils.checkString(rentPriceTo)) {
	                where.append(" AND b.rentprice <= " + rentPriceTo);
	            }
	        }
	        if (typeCode != null && typeCode.size() != 0) {
	        	List<String> quotedTypeCodes = new ArrayList<>();
	            for (String code : typeCode) {
	                quotedTypeCodes.add("'" + code + "'");
	            }
	            where.append(" AND rent_type.code IN (" + String.join(",", quotedTypeCodes) + ")");
	        }
	    }
	    @Override
	    public List<BuildingEntity> findAll(Map<String, Object> param, List<String> typecode) {
	    	StringBuilder  sql = new StringBuilder("SELECT b.id, b.name, b.districtId, b.street, b.ward, b.numberofbasement, b.floorarea, \r\n"
	    			+ "       b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b  ");
	    	StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
	    	joinTable(param,typecode,sql);
	    	queryNomal(param, where);
	    	querySpecial(param,typecode,where);
	    	where.append(" GROUP BY b.id");
	    	sql.append(where);
	    	List<BuildingEntity> result = new ArrayList<>();
	        
	        try (Connection conn = ConnectionJBPCUtil.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql.toString())) {
	            
	            while (rs.next()) { // tuong uong voi tung row
	                BuildingEntity building = new BuildingEntity();
	                building.setId(rs.getInt("id")); // Lấy Integer
	                building.setName(rs.getString("name"));
	                building.setDistrictId(rs.getInt("districtId"));
	                building.setStreet(rs.getString("street"));
	                building.setWard(rs.getString("ward"));
	                building.setNumberOfBasement(rs.getInt("numberOfBasement")); // An toàn hơn cho Integer/Long có thể null
	                building.setFloorArea(rs.getLong("floorArea"));
	                building.setRentPrice(rs.getLong("rentPrice"));
	                building.setManagerName(rs.getString("managerName"));
	                building.setManagerPhoneNumber(rs.getString("managerPhoneNumber"));
	                building.setServiceFee(rs.getLong("serviceFee"));
	                building.setBrokerageFee(rs.getLong("brokerageFee"));
	                result.add(building);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return result;
	    }
       
}
