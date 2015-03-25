package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilApplicationId;

public interface OilApplicationRepository extends JpaRepository<OilApplication, OilApplicationId> {

	@Query("SELECT oa FROM OilApplication oa INNER JOIN oa.id.oil o WHERE o.id = :oilId")
	List<OilApplication> findByOilId(@Param("oilId") Long oilId);
}
