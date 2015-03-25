package com.ethanaa.essential.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethanaa.essential.domain.UseCase;
import com.ethanaa.essential.domain.UseCaseCategory;
import com.ethanaa.essential.repository.UseCaseRepository;

@Service
@Transactional
public class UseCaseService {

	private final Logger log = LoggerFactory.getLogger(UseCaseService.class);

	private UseCaseRepository useCaseRepository;
	
	@Autowired
	public UseCaseService(UseCaseRepository useCaseRepository) {
		
		this.useCaseRepository = useCaseRepository;
	}
	
	public UseCase createUseCase(String name, String description, UseCaseCategory category) {
		
		UseCase useCase = new UseCase(name, description, category);
		
		useCase = useCaseRepository.save(useCase);
		
		return useCase;
	}
	
}
