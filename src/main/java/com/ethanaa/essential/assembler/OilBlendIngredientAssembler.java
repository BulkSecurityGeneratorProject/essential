package com.ethanaa.essential.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.Ingredient;
import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.web.rest.OilBlendEndpoint;
import com.ethanaa.essential.web.rest.OilEndpoint;
import com.ethanaa.essential.web.rest.resource.OilBlendIngredientResource;

@Component
public class OilBlendIngredientAssembler extends ResourceAssemblerSupport<OilBlendIngredient, OilBlendIngredientResource> {
	
	public OilBlendIngredientAssembler() {
		
		super(OilBlendEndpoint.class, OilBlendIngredientResource.class);
	}

	@Override
	public OilBlendIngredientResource toResource(OilBlendIngredient entity) {

		OilBlendIngredientResource resource = instantiateResource(entity);
		
		Ingredient ingredient = entity.getId().getIngredient();
		String ingredientType = ingredient.getIngredientType();
		
		ControllerLinkBuilder ingredientlinkBuilder = null;
		if ("OIL".equals(ingredientType)) {
			ingredientlinkBuilder = linkTo(OilEndpoint.class);
		}
		
		if (ingredientlinkBuilder != null) {
			resource.add(ingredientlinkBuilder.slash(ingredient.getId()).withRel(ingredientType.toLowerCase()));	
		}
		
		BeanUtils.copyProperties(entity, resource);
		BeanUtils.copyProperties(entity.getId().getIngredient(), resource.getIngredient());
		
		return resource;
	}

}
