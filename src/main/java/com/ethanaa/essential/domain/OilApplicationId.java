package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OilApplicationId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_id")	
	private Oil oil;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "use_case_id")	
	private UseCase useCase;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "application_id")	
	private Application application;
	
	public OilApplicationId(Oil oil, UseCase useCase, Application application) {
		
		this.oil = oil;
		this.useCase = useCase;
		this.application = application;
	}
	
	public OilApplicationId() {
		
	}

	public Oil getOil() {
		return oil;
	}

	public void setOil(Oil oil) {
		this.oil = oil;
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
		result = prime * result + ((oil == null) ? 0 : oil.hashCode());
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
		OilApplicationId other = (OilApplicationId) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (oil == null) {
			if (other.oil != null)
				return false;
		} else if (!oil.equals(other.oil))
			return false;
		if (useCase == null) {
			if (other.useCase != null)
				return false;
		} else if (!useCase.equals(other.useCase))
			return false;
		return true;
	}
	
}
