package com.ethanaa.essential.web.rest.resource;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OilBlendResource extends ResourceSupport {

	@NotNull
    @Size(min = 1, max = 50)
	private String name;
	
	@DecimalMin("0.0")
	@DecimalMax("10.0")
	@Column(name = "rating")
	private Float rating = 0.0f;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
		
}
