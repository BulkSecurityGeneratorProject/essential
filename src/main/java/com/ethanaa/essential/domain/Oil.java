package com.ethanaa.essential.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.BeanUtils;

import com.ethanaa.essential.web.rest.resource.OilResource;

@Entity
@Table(name = "T_OIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DiscriminatorValue("OIL")
public class Oil extends Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;	
	    
	@DecimalMin("0.0")
	@DecimalMax("10.0")
	@Column(name = "rating")
	private Float rating = 0.0f;
	
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "id.oil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OilInfoItem> infoItems = new ArrayList<>();
	 
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "id.oil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private List<OilApplication> applications = new ArrayList<>();
	    
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "id.oil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OilReview> reviews = new ArrayList<>();
    
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "oil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)    
    private List<OilImage> images = new ArrayList<>();    
    
	public Oil(String name) {
		
		this.setName(name);
	}
	
	public Oil(OilResource oilResource) {
		
		BeanUtils.copyProperties(oilResource, this);
	}
	
	public Oil() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}		

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public List<OilInfoItem> getInfoItems() {
		return infoItems;
	}

	public void setInfoItems(List<OilInfoItem> infoItems) {
		this.infoItems = infoItems;
	}
	
	public void addInfoItems(OilInfoItem... infoItems) {
		
		this.infoItems.addAll(Arrays.asList(infoItems));
	}

	public List<OilApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<OilApplication> applications) {
		this.applications = applications;
	}
	
	public void addApplications(OilApplication... applications) {
		
		this.applications.addAll(Arrays.asList(applications));
	}

	public List<OilReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<OilReview> reviews) {
		this.reviews = reviews;
	}
	
	public void addReviews(OilReview... reviews) {
		
		this.reviews.addAll(Arrays.asList(reviews));
	}		

	public List<OilImage> getImages() {
		return images;
	}

	public void setImages(List<OilImage> images) {
		this.images = images;
	}
	
	public void addImages(OilImage... images) {
		
		this.images.addAll(Arrays.asList(images));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oil other = (Oil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
