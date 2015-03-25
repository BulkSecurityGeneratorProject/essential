package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.OilInfoItemId;

public interface OilInfoItemRepository extends JpaRepository<OilInfoItem, OilInfoItemId> {

	@Query("SELECT oii FROM OilInfoItem oii INNER JOIN oii.id.oil o WHERE o.id = :oilId ORDER BY oii.id.section, oii.ordering ASC")
	List<OilInfoItem> findByOilId(@Param("oilId") Long oilId);
	
}
