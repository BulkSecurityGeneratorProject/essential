package com.ethanaa.essential.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.web.rest.OilBlendEndpoint;
import com.ethanaa.essential.web.rest.resource.OilBlendIngredientResource;

@Component
public class OilBlendIngredientAssembler extends ResourceAssemblerSupport<OilBlendIngredient, OilBlendIngredientResource> {
	
	public OilBlendIngredientAssembler() {
		
		super(OilBlendEndpoint.class, OilBlendIngredientResource.class);
	}

	@Override
	public OilBlendIngredientResource toResource(OilBlendIngredient entity) {

		OilBlendIngredientResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);
		BeanUtils.copyProperties(entity.getId().getIngredient(), resource.getIngredient());
		
		return resource;
	}

}
