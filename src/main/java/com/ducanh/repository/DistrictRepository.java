package com.ducanh.repository;

import com.ducanh.repository.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity findNameById(long id);
}
