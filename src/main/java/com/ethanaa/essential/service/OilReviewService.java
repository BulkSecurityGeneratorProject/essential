package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.repository.OilRepository;
import com.ethanaa.essential.repository.OilReviewRepository;
import com.ethanaa.essential.service.exception.OilNotFoundException;

@Service
@Transactional
public class OilReviewService {

	private final Logger log = LoggerFactory.getLogger(OilReviewService.class);
	
	private OilReviewRepository oilReviewRepository;
	private OilRepository oilRepository;
	
	@Autowired
	public OilReviewService(OilReviewRepository oilReviewRepository, OilRepository oilRepository) {
		
		this.oilReviewRepository = oilReviewRepository;
		this.oilRepository = oilRepository;
	}
	
	public List<OilReview> getOilReviewsByOilId(Long oilId) throws OilNotFoundException {
		
		if (!oilRepository.exists(oilId)) throw new OilNotFoundException(oilId);
		
		List<OilReview> reviews = oilReviewRepository.findByOilId(oilId);
		
		return reviews;
	}
}
