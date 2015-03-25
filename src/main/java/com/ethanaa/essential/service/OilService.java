package com.ethanaa.essential.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.repository.OilRepository;

@Service
@Transactional
public class OilService {

	private final Logger log = LoggerFactory.getLogger(OilService.class);

	private OilRepository oilRepository;
	
	@Autowired
	public OilService(OilRepository oilRepository) {
		
		this.oilRepository = oilRepository;
	}
	
	public Oil createOil(String name) {
		
		Oil oil = new Oil(name);
		
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
}
