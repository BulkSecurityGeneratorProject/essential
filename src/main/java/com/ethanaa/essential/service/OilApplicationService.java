package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.repository.OilApplicationRepository;
import com.ethanaa.essential.repository.OilRepository;
import com.ethanaa.essential.service.exception.OilNotFoundException;

@Service
@Transactional
public class OilApplicationService {

	private final Logger log = LoggerFactory.getLogger(OilApplicationService.class);
	
	private OilApplicationRepository oilApplicationRepository;
	private OilRepository oilRepository;
	
	@Autowired
	public OilApplicationService(OilApplicationRepository oilApplicationRepository, OilRepository oilRepository) {

		this.oilApplicationRepository = oilApplicationRepository;
		this.oilRepository = oilRepository;
	}
	
	public List<OilApplication> getOilApplicationsByOilId(Long oilId) throws OilNotFoundException {
		
		if (!oilRepository.exists(oilId)) throw new OilNotFoundException(oilId);
		
		List<OilApplication> applications = oilApplicationRepository.findByOilId(oilId);
		
		return applications;
	}
	
}
