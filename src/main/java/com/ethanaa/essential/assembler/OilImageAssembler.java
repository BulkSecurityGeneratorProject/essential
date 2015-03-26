package com.ethanaa.essential.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilImage;
import com.ethanaa.essential.web.rest.OilEndpoint;
import com.ethanaa.essential.web.rest.resource.OilImageResource;

@Component
public class OilImageAssembler extends ResourceAssemblerSupport<OilImage, OilImageResource> {

	public OilImageAssembler() {
		super(OilEndpoint.class, OilImageResource.class);
	}

	@Override
	public OilImageResource toResource(OilImage entity) {

		OilImageResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);		
		
		ControllerLinkBuilder builder = linkTo(OilEndpoint.class);
		String imageUri = builder.toUri().toString().replace("/api/oils", entity.getFilepath());
		
		resource.add(new Link(imageUri, "image"));
		
		return resource;
	}

}
