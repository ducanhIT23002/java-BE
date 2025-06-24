package com.ducanh.repository;

import java.util.List;

import com.ducanh.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> findValueByBuildingId(long id);
}
