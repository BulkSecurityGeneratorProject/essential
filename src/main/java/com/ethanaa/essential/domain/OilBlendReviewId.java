package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OilBlendReviewId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_blend_id")	
	private OilBlend oilBlend;

	public OilBlendReviewId(OilBlend oilBlend, User user) {
		
		this.oilBlend = oilBlend;
		this.user = user;
	}
	
	public OilBlendReviewId() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OilBlend getOilBlend() {
		return oilBlend;
	}

	public void setOilBlend(OilBlend oilBlend) {
		this.oilBlend = oilBlend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((oilBlend == null) ? 0 : oilBlend.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		OilBlendReviewId other = (OilBlendReviewId) obj;
		if (oilBlend == null) {
			if (other.oilBlend != null)
				return false;
		} else if (!oilBlend.equals(other.oilBlend))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}		
}
