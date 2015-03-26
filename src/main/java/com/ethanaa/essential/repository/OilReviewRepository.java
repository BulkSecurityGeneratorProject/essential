package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.domain.OilReviewId;

public interface OilReviewRepository extends JpaRepository<OilReview, OilReviewId> {

	@Query("SELECT r FROM OilReview r INNER JOIN r.id.oil o WHERE o.id = :oilId ORDER BY r.lastModifiedDate DESC")
	List<OilReview> findByOilId(@Param("oilId") Long oilId);
	
	@Query("SELECT r FROM OilReview r INNER JOIN r.id.oil o WHERE o.id = :oilId ORDER BY r.lastModifiedDate DESC")
	Page<OilReview> findByOilId(@Param("oilId") Long oilId, Pageable pageable);
}
