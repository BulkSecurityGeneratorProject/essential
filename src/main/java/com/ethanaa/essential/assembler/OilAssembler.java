package com.ethanaa.essential.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.web.rest.OilEndpoint;
import com.ethanaa.essential.web.rest.resource.OilResource;

@Component
public class OilAssembler extends ResourceAssemblerSupport<Oil, OilResource> {

	public OilAssembler() {
		
		super(OilEndpoint.class, OilResource.class);
	}

	@Override
	public OilResource toResource(Oil entity) {

		OilResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);
		
		resource.add(linkTo(OilEndpoint.class).slash(entity.getId()).withSelfRel());
		resource.add(linkTo(OilEndpoint.class).slash(entity.getId()).slash("info").withRel("info"));
		resource.add(linkTo(OilEndpoint.class).slash(entity.getId()).slash("image").withRel("image"));				
		resource.add(linkTo(OilEndpoint.class).slash(entity.getId()).slash("applications").withRel("applications"));
		resource.add(linkTo(OilEndpoint.class).slash(entity.getId()).slash("reviews").withRel("reviews"));		
		
		return resource;
	}


	
}
