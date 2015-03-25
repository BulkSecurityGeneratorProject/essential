package com.ethanaa.essential.web.rest.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.ethanaa.essential.domain.UseCaseCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UseCaseResource extends ResourceSupport {

	@NotNull
	private UseCaseCategory category;
	
	@Size(min = 1, max = 50)
	private String name;
	
	@Size(max = 256)
	private String description;

	public UseCaseCategory getCategory() {
		return category;
	}

	public void setCategory(UseCaseCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}			
}
