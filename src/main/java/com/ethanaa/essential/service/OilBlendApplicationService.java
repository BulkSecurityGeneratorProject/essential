package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilBlendApplication;
import com.ethanaa.essential.repository.OilBlendApplicationRepository;
import com.ethanaa.essential.repository.OilBlendRepository;
import com.ethanaa.essential.service.exception.OilBlendNotFoundException;

@Service
@Transactional
public class OilBlendApplicationService {

	private final Logger log = LoggerFactory.getLogger(OilBlendApplicationService.class);
	
	private OilBlendApplicationRepository oilBlendApplicationRepository;
	private OilBlendRepository oilBlendRepository;
	
	@Autowired
	public OilBlendApplicationService(OilBlendApplicationRepository oilBlendApplicationRepository,
			OilBlendRepository oilBlendRepository) {

		this.oilBlendApplicationRepository = oilBlendApplicationRepository;
		this.oilBlendRepository = oilBlendRepository;
	}
	
	public List<OilBlendApplication> getOilBlendApplicationsByOilBlendId(Long oilBlendId) throws OilBlendNotFoundException {
		
		if (!oilBlendRepository.exists(oilBlendId)) throw new OilBlendNotFoundException(oilBlendId);
		
		List<OilBlendApplication> applications = oilBlendApplicationRepository.findByOilBlendId(oilBlendId);
		
		return applications;
	}
}
