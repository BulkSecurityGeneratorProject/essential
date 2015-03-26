package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.repository.OilBlendIngredientRepository;
import com.ethanaa.essential.repository.OilBlendRepository;
import com.ethanaa.essential.service.exception.OilBlendNotFoundException;

@Service
@Transactional
public class OilBlendIngredientService {

	private final Logger log = LoggerFactory.getLogger(OilBlendIngredientService.class);
	
	private OilBlendIngredientRepository oilBlendIngredientRepository;
	private OilBlendRepository oilBlendRepository;
	
	@Autowired
	public OilBlendIngredientService(OilBlendIngredientRepository oilBlendIngredientRepository,
			OilBlendRepository oilBlendRepository) {

		this.oilBlendIngredientRepository = oilBlendIngredientRepository;
		this.oilBlendRepository = oilBlendRepository;
	}
	
	public List<OilBlendIngredient> getOilBlendIngredientsByOilBlendId(Long oilBlendId) throws OilBlendNotFoundException {
		
		if (!oilBlendRepository.exists(oilBlendId)) throw new OilBlendNotFoundException(oilBlendId);
		
		List<OilBlendIngredient> ingredients = oilBlendIngredientRepository.findByOilBlendId(oilBlendId);
		
		return ingredients;		
	}
}
