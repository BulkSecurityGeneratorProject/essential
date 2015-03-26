package com.ethanaa.essential.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilBlendReview;
import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.web.rest.OilBlendEndpoint;
import com.ethanaa.essential.web.rest.UserEndpoint;
import com.ethanaa.essential.web.rest.resource.OilBlendReviewResource;
import com.ethanaa.essential.web.rest.resource.OilReviewResource;

@Component
public class OilBlendReviewAssembler extends ResourceAssemblerSupport<OilBlendReview, OilBlendReviewResource> {

	public OilBlendReviewAssembler() {
		super(OilBlendEndpoint.class, OilBlendReviewResource.class);
	}

	@Override
	public OilBlendReviewResource toResource(OilBlendReview entity) {

		OilBlendReviewResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);
		
		resource.add(linkTo(UserEndpoint.class).slash(entity.getId().getUser().getLogin()).withRel("reviewer"));
		
		return resource;
	}		
}
