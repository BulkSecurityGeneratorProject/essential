package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilBlendReview;
import com.ethanaa.essential.repository.OilBlendRepository;
import com.ethanaa.essential.repository.OilBlendReviewRepository;
import com.ethanaa.essential.service.exception.OilBlendNotFoundException;
import com.ethanaa.essential.service.exception.OilNotFoundException;

@Service
@Transactional
public class OilBlendReviewService {

	private final Logger log = LoggerFactory.getLogger(OilReviewService.class);
	
	private OilBlendReviewRepository oilBlendReviewRepository;
	private OilBlendRepository oilBlendRepository;
	
	@Autowired
	public OilBlendReviewService(OilBlendReviewRepository oilBlendReviewRepository, OilBlendRepository oilBlendRepository) {
		
		this.oilBlendReviewRepository = oilBlendReviewRepository;
		this.oilBlendRepository = oilBlendRepository;
	}
	
    @Transactional(readOnly = true)
	public List<OilBlendReview> getOilBlendReviewsByOilBlendId(Long oilBlendId) throws OilNotFoundException {
		
		if (!oilBlendRepository.exists(oilBlendId)) throw new OilBlendNotFoundException(oilBlendId);
		
		List<OilBlendReview> reviews = oilBlendReviewRepository.findByOilBlendId(oilBlendId);
		
		return reviews;
	}
	
    @Transactional(readOnly = true)
	public Page<OilBlendReview> getOilBlendReviewsByOilBlendId(Long oilBlendId, Pageable pageable) throws OilBlendNotFoundException {
		
		if (!oilBlendRepository.exists(oilBlendId)) throw new OilBlendNotFoundException(oilBlendId);
		
		Page<OilBlendReview> reviews = oilBlendReviewRepository.findByOilBlendId(oilBlendId, pageable);		
		
		return reviews;
	}	
	
}
