package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ethanaa.essential.domain.OilBlendReview;
import com.ethanaa.essential.domain.OilBlendReviewId;

public interface OilBlendReviewRepository extends JpaRepository<OilBlendReview, OilBlendReviewId> {

	@Query("SELECT br FROM OilBlendReview br INNER JOIN br.id.oilBlend ob WHERE ob.id = :oilBlendId ORDER BY br.lastModifiedDate DESC")
	List<OilBlendReview> findByOilBlendId(@Param("oilBlendId") Long oilId);
	
	@Query("SELECT br FROM OilBlendReview br INNER JOIN br.id.oilBlend ob WHERE ob.id = :oilBlendId ORDER BY br.lastModifiedDate DESC")
	Page<OilBlendReview> findByOilBlendId(@Param("oilBlendId") Long oilId, Pageable pageable);	
}
