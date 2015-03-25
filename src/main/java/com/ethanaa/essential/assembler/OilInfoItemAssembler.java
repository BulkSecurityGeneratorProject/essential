package com.ethanaa.essential.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.web.rest.OilEndpoint;
import com.ethanaa.essential.web.rest.resource.OilInfoItemResource;

@Component
public class OilInfoItemAssembler extends ResourceAssemblerSupport<OilInfoItem, OilInfoItemResource> {

	public OilInfoItemAssembler() {
		super(OilEndpoint.class, OilInfoItemResource.class);
	}

	@Override
	public OilInfoItemResource toResource(OilInfoItem entity) {

		OilInfoItemResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource);
		
//		resource.add(linkTo(OilEndpoint.class).slash(entity.getId().getOil().getId()).withRel("oil"));
		
		return resource;
	}

}
