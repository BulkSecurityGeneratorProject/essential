package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class OilInfoItemId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_id")
	private Oil oil;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "section", nullable = false)
	private Section section;
	
	@Size(max = 50)
	@Column(name = "subsection", length = 50)
	private String subsection;

	public OilInfoItemId(Oil oil, Section section, String subsection) {
		
		this.oil = oil;
		this.section = section;
		this.subsection = subsection;
	}
	
	public OilInfoItemId() {

	}

	public Oil getOil() {
		return oil;
	}

	public void setOil(Oil oil) {
		this.oil = oil;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getSubsection() {
		return subsection;
	}

	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oil == null) ? 0 : oil.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result
				+ ((subsection == null) ? 0 : subsection.hashCode());
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
		OilInfoItemId other = (OilInfoItemId) obj;
		if (oil == null) {
			if (other.oil != null)
				return false;
		} else if (!oil.equals(other.oil))
			return false;
		if (section != other.section)
			return false;
		if (subsection == null) {
			if (other.subsection != null)
				return false;
		} else if (!subsection.equals(other.subsection))
			return false;
		return true;
	}

}
