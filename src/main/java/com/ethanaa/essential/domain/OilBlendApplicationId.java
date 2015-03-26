package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OilBlendApplicationId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_blend_id")
	private OilBlend oilBlend;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "use_case_id")	
	private UseCase useCase;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "application_id")
	private Application application;

	public OilBlendApplicationId(OilBlend oilBlend, UseCase useCase, Application application) {
		
		this.oilBlend = oilBlend;
		this.useCase = useCase;
		this.application = application;
	}
	
	public OilBlendApplicationId() {

	}

	public OilBlend getOilBlend() {
		return oilBlend;
	}

	public void setOilBlend(OilBlend oilBlend) {
		this.oilBlend = oilBlend;
	}

	public UseCase getUseCase() {
		return useCase;
	}

	public void setUseCase(UseCase useCase) {
		this.useCase = useCase;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result
				+ ((oilBlend == null) ? 0 : oilBlend.hashCode());
		result = prime * result + ((useCase == null) ? 0 : useCase.hashCode());
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
		OilBlendApplicationId other = (OilBlendApplicationId) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (oilBlend == null) {
			if (other.oilBlend != null)
				return false;
		} else if (!oilBlend.equals(other.oilBlend))
			return false;
		if (useCase == null) {
			if (other.useCase != null)
				return false;
		} else if (!useCase.equals(other.useCase))
			return false;
		return true;
	}		
}
