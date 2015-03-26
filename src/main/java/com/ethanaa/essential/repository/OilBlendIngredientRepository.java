package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.domain.OilBlendIngredientId;

public interface OilBlendIngredientRepository extends JpaRepository<OilBlendIngredient, OilBlendIngredientId> {

	@Query("SELECT obi FROM OilBlendIngredient obi INNER JOIN obi.id.oilBlend ob WHERE ob.id = :oilBlendId")
	List<OilBlendIngredient> findByOilBlendId(@Param("oilBlendId") Long oilBlendId);	
	
}
