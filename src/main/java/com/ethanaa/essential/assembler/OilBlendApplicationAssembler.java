package com.ethanaa.essential.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilBlendApplication;
import com.ethanaa.essential.web.rest.OilBlendEndpoint;
import com.ethanaa.essential.web.rest.resource.OilBlendApplicationResource;

@Component
public class OilBlendApplicationAssembler extends ResourceAssemblerSupport<OilBlendApplication, OilBlendApplicationResource> {

	public OilBlendApplicationAssembler() {
		super(OilBlendEndpoint.class, OilBlendApplicationResource.class);
	}

	@Override
	public OilBlendApplicationResource toResource(OilBlendApplication entity) {

		OilBlendApplicationResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity.getUseCase(), resource.getUseCase());
		BeanUtils.copyProperties(entity.getApplication(), resource.getApplication());
		
		return resource;
	}

}
