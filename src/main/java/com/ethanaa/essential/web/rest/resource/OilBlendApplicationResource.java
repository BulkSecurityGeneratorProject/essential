package com.ethanaa.essential.web.rest.resource;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OilBlendApplicationResource extends ResourceSupport {

	@NotNull
	UseCaseResource useCase = new UseCaseResource();
	
	@NotNull
	ApplicationResource application = new ApplicationResource();

	public UseCaseResource getUseCase() {
		return useCase;
	}

	public void setUseCase(UseCaseResource useCase) {
		this.useCase = useCase;
	}

	public ApplicationResource getApplication() {
		return application;
	}

	public void setApplication(ApplicationResource application) {
		this.application = application;
	}			
}
