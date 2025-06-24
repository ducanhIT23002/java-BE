package com.ducanh.repository.iml;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ducanh.repository.RentAreaRepository;
import com.ducanh.repository.entity.RentAreaEntity;
import com.ducanh.utils.ConnectionJBPCUtil;
@Repository
public class RentAreaRepositoryIml implements RentAreaRepository{

	@Override
	public List<RentAreaEntity> findValueByBuildingId(long id) {
		String sql = "SELECT d.value FROM rent_area d WHERE d.buildingId = " + id + ";";
		List<RentAreaEntity> result = new ArrayList<RentAreaEntity>();
        try (Connection conn = ConnectionJBPCUtil.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) { 
	            	RentAreaEntity rentAreaEntity = new RentAreaEntity();
	            	rentAreaEntity.setValue(rs.getLong("value"));
	            	result.add(rentAreaEntity);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
        return result;
	}
   
}
