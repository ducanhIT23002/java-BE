package com.ducanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducanh.repository.entity.BuildingEntity;

public interface BuildingRepositorySprDataJpa extends  JpaRepository<BuildingEntity, Integer>{
  
}
