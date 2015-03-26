package com.ethanaa.essential.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "T_OIL_IMAGE")
public class OilImage extends Image implements Serializable {

	private static final String IMAGE_PATH = "/assets/images";
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_id")	
	private Oil oil;

	public OilImage(ImageType imageType, String title, String altText, String description, String filepath) {
		
		this.setImageType(imageType);
		this.setTitle(title);
		this.setAltText(altText);
		this.setDescription(description);
		this.setFilepath(filepath);
		
		this.setCreatedBy("system");
		this.setCreatedDate(new DateTime());
	}
	
	public OilImage() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Oil getOil() {
		return oil;
	}

	public void setOil(Oil oil) {
		
		this.oil = oil;
		
		switch(getImageType()) {
		case OIL_ICON:
			this.setFilepath(IMAGE_PATH + "/oil-icons/" +  getFilepath());
			break;
		case OIL_EXTRA:
			this.setFilepath(IMAGE_PATH + "/oils/" + oil.getName().toLowerCase() + "/" + getFilepath());
			break;
		default:			
			break;		
		}		
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
		OilImage other = (OilImage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
