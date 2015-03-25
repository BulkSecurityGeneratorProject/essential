package com.ethanaa.essential.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethanaa.essential.domain.Application;
import com.ethanaa.essential.domain.ApplicationCategory;
import com.ethanaa.essential.repository.ApplicationRepository;

@Service
@Transactional
public class ApplicationService {

	private final Logger log = LoggerFactory.getLogger(ApplicationService.class);

	private ApplicationRepository applicationRepository;
	
	@Autowired
	public ApplicationService(ApplicationRepository applicationRepository) {
		
		this.applicationRepository = applicationRepository;
	}
	
	public Application createApplication(String name, String description, ApplicationCategory category) {
		
		Application application = new Application(name, description, category);
		
		application = applicationRepository.save(application);
		
		return application;
	}
	
}
