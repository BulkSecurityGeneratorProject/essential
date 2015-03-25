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

import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.repository.OilRepository;
import com.ethanaa.essential.service.exception.OilNotFoundException;
import com.ethanaa.essential.web.rest.resource.OilResource;

@Service
@Transactional
public class OilService {

	private final Logger log = LoggerFactory.getLogger(OilService.class);

	private OilRepository oilRepository;
	
	@Autowired
	public OilService(OilRepository oilRepository) {
		
		this.oilRepository = oilRepository;
	}
	
    @Transactional(readOnly = true)
	public List<Oil> getOils() {
		
		List<Oil> oils = oilRepository.findByOrderByNameAsc();
		
		return oils;
	}
	
    @Transactional(readOnly = true)
	public Page<Oil> getOils(Pageable pageable) {
		
		Page<Oil> oilPage = oilRepository.findByOrderByNameAsc(pageable);
		
		return oilPage;
	}
    
    @Transactional(readOnly = true)
    public Oil getOil(Long oilId) throws OilNotFoundException {
    	
    	Oil oil = oilRepository.findOne(oilId);
    	
    	if (oil == null) throw new OilNotFoundException(oilId);
    	
    	return oil;
    }
    
    @Transactional(readOnly = true)
    public List<OilInfoItem> getOilInfoItems(Long oilId) throws OilNotFoundException {
    	
    	Oil oil = getOil(oilId);
    	
    	return oil.getInfoItems();
    }
	
	public Oil createOil(String name) {
		
		Oil oil = new Oil(name);
		
		oil = oilRepository.save(oil);
		
		return oil;
	}
	
	public Oil createOil(OilResource oilResource) {
		
		Oil oil = new Oil(oilResource);
		
		oil = oilRepository.save(oil);
		
		return oil;
	}
	
	public Oil updateOil(Long oilId, OilResource oilResource) throws OilNotFoundException {
		
		Oil oil = getOil(oilId);
		
		BeanUtils.copyProperties(oilResource, oil);
		
		oil = oilRepository.save(oil);
		
		return oil;
	}
	
	public List<OilInfoItem> addOilInfoItems(Oil oil, OilInfoItem... infoItems) {
		
		for (OilInfoItem infoItem : infoItems) {
			infoItem.setOil(oil);
		}
		
		oil.addInfoItems(infoItems);
		
		oil = oilRepository.save(oil);
		
		return oil.getInfoItems();
	}
	
	public List<OilApplication> addOilApplications(Oil oil, OilApplication... applications) {
		
		for (OilApplication application : applications) {
			application.setOil(oil);
		}
		
		oil.addApplications(applications);
		
		oil = oilRepository.save(oil);
		
		return oil.getApplications();
	}
	
	public List<OilReview> addOilReviews(Oil oil, OilReview... reviews) {
		
		for (OilReview review : reviews) {
			review.setOil(oil);
		}
		
		oil.addReviews(reviews);
		
		oil = oilRepository.save(oil);
		
		return oil.getReviews();
	}
	
	public void deleteOil(Long oilId) {
		
		oilRepository.delete(oilId);
	}
}
