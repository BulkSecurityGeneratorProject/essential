package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "T_OIL_BLEND_REVIEW")
public class OilBlendReview extends Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OilBlendReviewId id = new OilBlendReviewId();
	
	public OilBlendReview(User user, Float rating, String review) {
		
		this.setUser(user);
		this.setRating(rating);
		this.setReview(review);
		
		this.setCreatedBy(user.getLogin());
		this.setCreatedDate(new DateTime());		
	}
	
	public OilBlendReview() {
		
	}

	public OilBlendReviewId getId() {
		return id;
	}

	public void setId(OilBlendReviewId id) {
		this.id = id;
	}
	
	public void setOilBlend(OilBlend oilBlend) {
		this.getId().setOilBlend(oilBlend);
	}
	
	public void setUser(User user) {
		this.getId().setUser(user);
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
		OilBlendReview other = (OilBlendReview) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
