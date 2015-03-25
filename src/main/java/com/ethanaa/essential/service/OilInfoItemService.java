package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.repository.OilInfoItemRepository;
import com.ethanaa.essential.repository.OilRepository;
import com.ethanaa.essential.service.exception.OilNotFoundException;

@Service
@Transactional
public class OilInfoItemService {

	private final Logger log = LoggerFactory.getLogger(OilInfoItemService.class);

	private OilInfoItemRepository oilInfoItemRepository;
	private OilRepository oilRepository;
	
	@Autowired
	public OilInfoItemService(OilInfoItemRepository oilInfoItemRepository, OilRepository oilRepository) {

		this.oilInfoItemRepository = oilInfoItemRepository;
		this.oilRepository = oilRepository;
	}
	
	public List<OilInfoItem> getOilInfoItemsByOilId(Long oilId) throws OilNotFoundException {
		
		if (!oilRepository.exists(oilId)) throw new OilNotFoundException(oilId);
		
		List<OilInfoItem> infoItems = oilInfoItemRepository.findByOilId(oilId);
		
		return infoItems;
	}
	
}
