package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.DateTime;

@Entity
@Table(name = "T_OIL_INFO_ITEM")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OilInfoItem extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private OilInfoItemId id = new OilInfoItemId();
	
	@Lob
	@Column(name = "markdown")
	private String markdown;
	
	@Column(name = "ordering")
	private Integer ordering;

	public OilInfoItem(Section section, String subsection, String markdown) {
		
		this.setSection(section);
		this.setSubsection(subsection);
		this.setMarkdown(markdown);
		
		this.setCreatedBy("system");
		this.setCreatedDate(new DateTime());
	}
	
	public OilInfoItem() {

	}

	public OilInfoItemId getId() {
		return id;
	}

	public void setId(OilInfoItemId id) {
		this.id = id;
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	
	public void setOil(Oil oil) {
		this.getId().setOil(oil);
	}
	
	public void setSection(Section section) {
		this.getId().setSection(section);
	}
	
	public void setSubsection(String subsection) {
		this.getId().setSubsection(subsection);
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
		OilInfoItem other = (OilInfoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
