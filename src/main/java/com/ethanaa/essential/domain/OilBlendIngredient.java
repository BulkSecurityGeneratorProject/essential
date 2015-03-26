package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

@Entity
@Table(name = "T_OIL_BLEND_INGREDIENT")
public class OilBlendIngredient extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OilBlendIngredientId id = new OilBlendIngredientId();	
	
	@Size(max = 256)
	@Column(name = "description", length = 256)
	private String description;
	
	@NotNull
	@DecimalMin("0.0")
	@Column(name = "quantity")
	private Float quantity;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "units")
	private IngredientUnit units;		
	
	public OilBlendIngredient(Ingredient ingredient, Float quantity, IngredientUnit units, String description) {
		
		this.setIngredient(ingredient);
		this.setQuantity(quantity);
		this.setUnits(units);
		this.description = description;
		
		this.setCreatedBy("system");
		this.setCreatedDate(new DateTime());		
	}
	
	public OilBlendIngredient() {

	}

	public OilBlendIngredientId getId() {
		return id;
	}

	public void setId(OilBlendIngredientId id) {
		this.id = id;
	}
	
	public void setOilBlend(OilBlend oilBlend) {
		this.getId().setOilBlend(oilBlend);
	}

	public void setIngredient(Ingredient ingredient) {
		this.getId().setIngredient(ingredient);
	}
	
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public IngredientUnit getUnits() {
		return units;
	}

	public void setUnits(IngredientUnit units) {
		this.units = units;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		OilBlendIngredient other = (OilBlendIngredient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
