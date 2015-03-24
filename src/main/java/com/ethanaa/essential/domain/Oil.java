package com.ethanaa.essential.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_OIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Oil extends Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	@OneToMany(mappedBy = "oil", cascade = CascadeType.ALL)
	private List<OilInfoItem> infoItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "oil", cascade = CascadeType.ALL)	
	private List<OilApplication> applications = new ArrayList<>();
	
	@OneToMany(mappedBy = "oil", cascade = CascadeType.ALL)
	private List<OilReview> reviews = new ArrayList<>();		

	public Oil(String name) {
		
		this.setName(name);
	}
	
	public Oil() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
