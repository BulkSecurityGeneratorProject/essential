package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OilReviewId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_id")	
	private Oil oil;

	public OilReviewId(Oil oil, User user) {
		
		this.oil = oil;
		this.user = user;
	}
	
	public OilReviewId() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Oil getOil() {
		return oil;
	}

	public void setOil(Oil oil) {
		this.oil = oil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oil == null) ? 0 : oil.hashCode());
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
		OilReviewId other = (OilReviewId) obj;
		if (oil == null) {
			if (other.oil != null)
				return false;
		} else if (!oil.equals(other.oil))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
