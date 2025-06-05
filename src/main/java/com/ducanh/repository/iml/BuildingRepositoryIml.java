package com.ducanh.repository.iml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ducanh.repository.BuildingRepository;
import com.ducanh.repository.entity.BuildingEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class BuildingRepositoryIml implements BuildingRepository{
	    String DB_URL = "jdbc:mysql://localhost:3306/table";
	    String USER = "root";
	    String PASS = ""; 
	    @Override
	    public List<BuildingEntity> findAll(String name) {
	        String sql = "SELECT * FROM building b WHERE name like '%" + name + "%'";
	        List<BuildingEntity> result = new ArrayList<>();
	        
	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) { // tuong uong voi tung row
	                BuildingEntity building = new BuildingEntity();
	                building.setStreet(rs.getString("street"));
	                building.setWard(rs.getString("ward"));
	                building.setName(rs.getString("name"));
	                result.add(building);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return result;
	    }
       
}
