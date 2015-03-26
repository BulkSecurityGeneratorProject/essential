package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilImage;
import com.ethanaa.essential.repository.OilImageRepository;
import com.ethanaa.essential.repository.OilRepository;
import com.ethanaa.essential.service.exception.OilNotFoundException;

@Service
@Transactional
public class OilImageService {

	private final Logger log = LoggerFactory.getLogger(OilImageService.class);
	
	private OilImageRepository oilImageRepository;
	private OilRepository oilRepository;
	
	@Autowired
	public OilImageService(OilImageRepository oilImageRepository, OilRepository oilRepository) {
		
		this.oilImageRepository = oilImageRepository;
		this.oilRepository = oilRepository;
	}
	
	public List<OilImage> getOilImagesByOilId(Long oilId) {
		
		if (!oilRepository.exists(oilId)) throw new OilNotFoundException(oilId);
		
		List<OilImage> images = oilImageRepository.findByOilId(oilId);
		
		return images;
	}
	
}
