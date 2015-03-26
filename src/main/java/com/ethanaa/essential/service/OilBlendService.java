package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.OilBlend;
import com.ethanaa.essential.domain.OilBlendApplication;
import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.domain.OilBlendReview;
import com.ethanaa.essential.domain.User;
import com.ethanaa.essential.repository.OilBlendRepository;
import com.ethanaa.essential.service.exception.OilBlendNotFoundException;
import com.ethanaa.essential.web.rest.resource.OilBlendResource;

@Service
@Transactional
public class OilBlendService {

	private final Logger log = LoggerFactory.getLogger(OilBlendService.class);

	private OilBlendRepository oilBlendRepository;
	
	@Autowired
	public OilBlendService(OilBlendRepository oilBlendRepository) {
		
		this.oilBlendRepository = oilBlendRepository;
	}
	
    @Transactional(readOnly = true)
	public List<OilBlend> getOilBlends() {
		
		List<OilBlend> oilBlends = oilBlendRepository.findByOrderByNameAsc();
		
		return oilBlends;
	}
	
    @Transactional(readOnly = true)
	public Page<OilBlend> getOilBlends(Pageable pageable) {
		
		Page<OilBlend> oilBlendPage = oilBlendRepository.findByOrderByNameAsc(pageable);
		
		return oilBlendPage;
	}
    
    @Transactional(readOnly = true)
    public OilBlend getOilBlend(Long oilBlendId) throws OilBlendNotFoundException {
    	
    	OilBlend oilBlend = oilBlendRepository.findOne(oilBlendId);
    	
    	if (oilBlend == null) throw new OilBlendNotFoundException(oilBlendId);
    	
    	return oilBlend;
    }
    
    public OilBlend createOilBlend(String name, User user) {
    	
    	OilBlend oilBlend = new OilBlend(name, user);
    	
    	oilBlend = oilBlendRepository.save(oilBlend);
    	
    	return oilBlend;
    }
    
    public OilBlend createOilBlend(OilBlendResource oilBlendResource) {
    	
    	OilBlend oilBlend = new OilBlend(oilBlendResource);
    	
    	oilBlend = oilBlendRepository.save(oilBlend);
    	
    	return oilBlend;
    }
    
    public OilBlend updateOilBlend(Long oilBlendId, OilBlendResource oilBlendResource) throws OilBlendNotFoundException {
    	
    	OilBlend oilBlend = getOilBlend(oilBlendId);
    	
    	BeanUtils.copyProperties(oilBlendResource, oilBlend);
    	
    	oilBlend = oilBlendRepository.save(oilBlend);
    	
    	return oilBlend;
    }
    
    public List<OilBlendIngredient> addOilBlendIngredients(OilBlend oilBlend, OilBlendIngredient... ingredients) {
    	
    	for (OilBlendIngredient ingredient : ingredients) {
    		ingredient.setOilBlend(oilBlend);
    	}
    	
    	oilBlend.addIngredients(ingredients);
    	
    	oilBlend = oilBlendRepository.save(oilBlend);
    	
    	return oilBlend.getIngredients();
    }
    
    public List<OilBlendApplication> addOilBlendApplications(OilBlend oilBlend, OilBlendApplication... applications) {
    	
    	for (OilBlendApplication application : applications) {
    		application.setOilBlend(oilBlend);
    	}
    	
    	oilBlend.addApplications(applications);
    	
    	oilBlend = oilBlendRepository.save(oilBlend);
    	
    	return oilBlend.getApplications();
    }
    
    public List<OilBlendReview> addOilBlendReviews(OilBlend oilBlend, OilBlendReview... reviews) {
    	
    	for (OilBlendReview review : reviews) {
    		review.setOilBlend(oilBlend);
    	}
    	
    	oilBlend.addReviews(reviews);
    	
    	oilBlend = oilBlendRepository.save(oilBlend);
    	
    	return oilBlend.getReviews();
    }
    
    public void deleteOilBlend(Long oilBlendId) {
    	
    	oilBlendRepository.delete(oilBlendId);
    }
}
