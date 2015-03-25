package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_OIL_APPLICATION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OilApplication implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private OilApplicationId id = new OilApplicationId();		
	
	public OilApplication(UseCase useCase, Application application) {
		
		this.setUseCase(useCase);
		this.setApplication(application);
	}
	
	public OilApplication() {

	}

	public OilApplicationId getId() {
		return id;
	}

	public void setId(OilApplicationId id) {
		this.id = id;
	}
	
	public void setOil(Oil oil) {
		this.getId().setOil(oil);
	}
	
	public void setUseCase(UseCase useCase) {
		this.getId().setUseCase(useCase);
	}
	
	public void setApplication(Application application) {
		this.getId().setApplication(application);
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
		OilApplication other = (OilApplication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
