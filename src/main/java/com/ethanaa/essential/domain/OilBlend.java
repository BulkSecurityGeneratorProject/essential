package com.ethanaa.essential.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ethanaa.essential.web.rest.resource.OilBlendResource;

@Entity
@Table(name = "T_OIL_BLEND")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OilBlend extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@DecimalMin("0.0")
	@DecimalMax("10.0")
	@Column(name = "rating")
	private Float rating = 0.0f;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "user_id")
	private User user;
	
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "id.oilBlend", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 	
	List<OilBlendIngredient> ingredients = new ArrayList<OilBlendIngredient>();
	
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "id.oilBlend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)    
    List<OilBlendApplication> applications = new ArrayList<>();
    
    @Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "id.oilBlend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)     
    List<OilBlendReview> reviews = new ArrayList<>();
    
    public OilBlend(String name, User user) {

    	this.setName(name);
    	this.setUser(user);
	}
    
    public OilBlend(OilBlendResource resource) {
    	
    	this.setName(resource.getName());
    }
    
    public OilBlend(String name) {
    	
    	this(name, null);
    }
    
    public OilBlend() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OilBlendIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<OilBlendIngredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredients(OilBlendIngredient... ingredients) {
		
		this.ingredients.addAll(Arrays.asList(ingredients));
	}

	public List<OilBlendApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<OilBlendApplication> applications) {
		this.applications = applications;
	}
	
	public void addApplications(OilBlendApplication... applications) {
		
		this.applications.addAll(Arrays.asList(applications));
	}	

	public List<OilBlendReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<OilBlendReview> reviews) {
		this.reviews = reviews;
	}
	
	public void addReviews(OilBlendReview... reviews) {
		
		this.reviews.addAll(Arrays.asList(reviews));
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
		OilBlend other = (OilBlend) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}            
}
