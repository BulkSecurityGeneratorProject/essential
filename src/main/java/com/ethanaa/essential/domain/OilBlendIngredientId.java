package com.ethanaa.essential.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OilBlendIngredientId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "oil_blend_id")	
	private OilBlend oilBlend;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;	
	
	public OilBlendIngredientId(OilBlend oilBlend, Ingredient ingredient) {
		
		this.oilBlend = oilBlend;
		this.ingredient = ingredient;
	}	
	
	public OilBlendIngredientId() {
		
	}		

	public OilBlend getOilBlend() {
		return oilBlend;
	}

	public void setOilBlend(OilBlend oilBlend) {
		this.oilBlend = oilBlend;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result
				+ ((oilBlend == null) ? 0 : oilBlend.hashCode());
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
		OilBlendIngredientId other = (OilBlendIngredientId) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (oilBlend == null) {
			if (other.oilBlend != null)
				return false;
		} else if (!oilBlend.equals(other.oilBlend))
			return false;
		return true;
	}
}
