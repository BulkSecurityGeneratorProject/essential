package com.ethanaa.essential.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilBlend;
import com.ethanaa.essential.web.rest.OilBlendEndpoint;
import com.ethanaa.essential.web.rest.resource.OilBlendResource;

@Component
public class OilBlendAssembler extends ResourceAssemblerSupport<OilBlend, OilBlendResource> {

	public OilBlendAssembler() {
		
		super(OilBlendEndpoint.class, OilBlendResource.class);
	}

	@Override
	public OilBlendResource toResource(OilBlend entity) {

		OilBlendResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);
		
		resource.add(linkTo(OilBlendEndpoint.class).slash(entity.getId()).withSelfRel());
		resource.add(linkTo(OilBlendEndpoint.class).slash(entity.getId()).slash("ingredients").withRel("ingredients"));
		resource.add(linkTo(OilBlendEndpoint.class).slash(entity.getId()).slash("applications").withRel("applications"));
		resource.add(linkTo(OilBlendEndpoint.class).slash(entity.getId()).slash("reviews").withRel("reviews"));						
		
		return resource;
	} 

}
