package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Review extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@DecimalMin("0.0")
	@DecimalMax("10.0")
	@Column(name = "rating")
	private Float rating;
	
	@NotNull
	@Size(min = 1, max = 4096)
	@Lob
	@Column(name = "review", length = 4096, nullable = false)
	private String review;
	
	public Review() {

	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}		

}
