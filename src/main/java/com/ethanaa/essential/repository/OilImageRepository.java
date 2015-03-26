package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilImage;

public interface OilImageRepository extends JpaRepository<OilImage, Long> {

	@Query("SELECT oi FROM OilImage oi INNER JOIN oi.oil o WHERE o.id = :oilId")
	public List<OilImage> findByOilId(@Param("oilId") Long oilId);
}
