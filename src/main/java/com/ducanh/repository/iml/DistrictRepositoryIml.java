package com.ducanh.repository.iml;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.ducanh.repository.DistrictRepository;
import com.ducanh.repository.entity.DistrictEntity;
import com.ducanh.utils.ConnectionJBPCUtil;

@Repository
public class DistrictRepositoryIml implements DistrictRepository {
    
	public DistrictEntity findNameById(long id) {
		String sql = "SELECT d.name FROM district d WHERE d.id = " + id + ";";
		DistrictEntity districtEntity = new DistrictEntity();
        try (Connection conn = ConnectionJBPCUtil.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) { 
	            	districtEntity.setName(rs.getString("name"));
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
        return districtEntity;
	}
}
