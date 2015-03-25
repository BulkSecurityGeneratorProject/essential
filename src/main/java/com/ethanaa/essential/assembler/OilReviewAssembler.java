package com.ethanaa.essential.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.web.rest.OilEndpoint;
import com.ethanaa.essential.web.rest.UserEndpoint;
import com.ethanaa.essential.web.rest.resource.OilReviewResource;

@Component
public class OilReviewAssembler extends ResourceAssemblerSupport<OilReview, OilReviewResource> {

	public OilReviewAssembler() {
		super(OilEndpoint.class, OilReviewResource.class);
	}

	@Override
	public OilReviewResource toResource(OilReview entity) {

		OilReviewResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);
		
		resource.add(linkTo(UserEndpoint.class).slash(entity.getId().getUser().getLogin()).withRel("reviewer"));
		
		return resource;
	}		
}
