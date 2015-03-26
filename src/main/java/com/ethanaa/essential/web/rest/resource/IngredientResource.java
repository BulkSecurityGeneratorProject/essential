package com.ethanaa.essential.web.rest.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

public class IngredientResource extends ResourceSupport {

	@NotNull
	private String ingredientType;	
	
	@NotNull
	@Size(min = 1, max = 256)
	private String name;		
	
	public String getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(String ingredientType) {
		this.ingredientType = ingredientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}			
}
