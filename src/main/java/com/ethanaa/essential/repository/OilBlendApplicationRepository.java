package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilBlendApplication;
import com.ethanaa.essential.domain.OilBlendApplicationId;

public interface OilBlendApplicationRepository extends JpaRepository<OilBlendApplication, OilBlendApplicationId> {

	@Query("SELECT oba FROM OilBlendApplication oba INNER JOIN oba.id.oilBlend ob WHERE ob.id = :oilBlendId")
	List<OilBlendApplication> findByOilBlendId(@Param("oilBlendId") Long oilBlendId);	
	
}
