package com.ducanh.repository.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ducanh.builder.BuildingSearchBuilder;
import com.ducanh.repository.BuildingRepository;
import com.ducanh.repository.entity.BuildingEntity;
import com.ducanh.utils.ConnectionJBPCUtil;
import com.ducanh.utils.NumberUtils;
import com.ducanh.utils.StringUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class BuildingRepositoryIml implements BuildingRepository{

	    
	    public void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql ) {
	    	    Long staffId = buildingSearchBuilder.getStaffId();
	    	    if (staffId != null) {
	    	        sql.append(" JOIN assignment_building ON assignment_building.buildingId = b.id  ");
	    	    }

	    	    List<String> typeCode = buildingSearchBuilder.getTypeCode();
	    	    if (typeCode != null && typeCode.size() != 0) {
		        	sql.append(" JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");
		        	sql.append(" JOIN rent_type ON buildingrenttype.rentTypeId  = rent_type.id ");
	    	    }
	    	
	    }
	    
	    //JAVA REFLECTION
	    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
	    	try {
	    	    Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
	    	    for (Field item : fields) {
	    	        item.setAccessible(true);
	    	        String fieldName = item.getName();
	    	        if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
	    	                && !fieldName.startsWith("rentPrice")) {
	    	            Object value = item.get(buildingSearchBuilder);
	    	            if (value != null) {
	    	                if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
	    	                    where.append(" AND b." + fieldName + " = " + value);
	    	                } else if (item.getType().getName().equals("java.lang.String")) {
	    	                    where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
	    	                }
	    	            }
	    	        }
	    	    }
	    	} catch (Exception ex) {
	    	    ex.printStackTrace();
	    	}
	    }
	    

	    
	    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
	    	Long staffId = buildingSearchBuilder.getStaffId();
	    	if (staffId != null) {
	    	    where.append(" AND assignment_building.staffId = " + staffId);
	    	}
	    	Long rentAreaTo = buildingSearchBuilder.getAreaTo();
	    	Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();

	    	if (rentAreaTo != null || rentAreaFrom != null) {
	    	    where.append(" AND EXISTS (SELECT * FROM rent_area r WHERE b.id = r.buildingId ");
	    	    if (rentAreaFrom != null) {
	    	        where.append(" AND r.value >= " + rentAreaFrom);
	    	    }
	    	    if (rentAreaTo != null) {
	    	        where.append(" AND r.value <= " + rentAreaTo);
	    	    }
	    	    where.append(") ");
	    	}
	        
	    	Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
	    	Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
	    	if (rentPriceTo != null || rentPriceFrom != null) {
	    	    if (rentPriceFrom != null) {
	    	        where.append(" AND b.rentprice >= " + rentPriceFrom);
	    	    }
	    	    if (rentPriceTo != null) {
	    	        where.append(" AND b.rentprice <= " + rentPriceTo);
	    	    }
	    	}

	    	List<String> typeCode = buildingSearchBuilder.getTypeCode();
	    	if (typeCode != null && typeCode.size() != 0) {
	    	    where.append(" AND(");
	    	    String sql = typeCode.stream().map(it -> "rent_type.code Like '" + "%" + it + "%'").collect(Collectors.joining(" OR "));
	    	    where.append(sql);
	    	    where.append(") ");
	    	}
	    }
	    @Override
	    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
	    	StringBuilder  sql = new StringBuilder("SELECT b.id, b.name, b.districtId, b.street, b.ward, b.numberofbasement, b.floorarea, \r\n"
	    			+ "       b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b  ");
	    	StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
	    	joinTable(buildingSearchBuilder,sql);
	    	queryNomal(buildingSearchBuilder, where);
	    	querySpecial(buildingSearchBuilder,where);
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
