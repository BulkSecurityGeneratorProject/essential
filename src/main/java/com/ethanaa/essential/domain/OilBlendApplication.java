package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_OIL_BLEND_APPLICATION")
public class OilBlendApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OilBlendApplicationId id = new OilBlendApplicationId();
	
	public OilBlendApplication(UseCase useCase, Application application) {
		
		this.setUseCase(useCase);
		this.setApplication(application);
	}
	
	public OilBlendApplication() {
		
	}

	public OilBlendApplicationId getId() {
		return id;
	}

	public void setId(OilBlendApplicationId id) {
		this.id = id;
	}

	public void setOilBlend(OilBlend oilBlend) {
		this.getId().setOilBlend(oilBlend);
	}
	
	public UseCase getUseCase() {
		return this.getId().getUseCase();
	}
	
	public void setUseCase(UseCase useCase) {
		this.getId().setUseCase(useCase);
	}
	
	public Application getApplication() {
		return this.getId().getApplication();
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
		OilBlendApplication other = (OilBlendApplication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
