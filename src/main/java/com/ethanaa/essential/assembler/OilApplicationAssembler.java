package com.ethanaa.essential.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.web.rest.OilEndpoint;
import com.ethanaa.essential.web.rest.resource.OilApplicationResource;

@Component
public class OilApplicationAssembler extends ResourceAssemblerSupport<OilApplication, OilApplicationResource> {

	public OilApplicationAssembler() {
		
		super(OilEndpoint.class, OilApplicationResource.class);
	}

	@Override
	public OilApplicationResource toResource(OilApplication entity) {

		OilApplicationResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity.getUseCase(), resource.getUseCase());
		BeanUtils.copyProperties(entity.getApplication(), resource.getApplication());
		
		return resource;
	}

}
