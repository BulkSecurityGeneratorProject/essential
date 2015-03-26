package com.ethanaa.essential.web.rest.resource;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.ethanaa.essential.domain.IngredientUnit;

public class OilBlendIngredientResource extends ResourceSupport {

	@NotNull
	private IngredientResource ingredient = new IngredientResource();
	
	@Size(max = 256)
	private String description;
	
	@NotNull
	@DecimalMin("0.0")
	private Float quantity;
	
	@NotNull
	private IngredientUnit units;

	public IngredientResource getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientResource ingredient) {
		this.ingredient = ingredient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public IngredientUnit getUnits() {
		return units;
	}

	public void setUnits(IngredientUnit units) {
		this.units = units;
	}
	
}
