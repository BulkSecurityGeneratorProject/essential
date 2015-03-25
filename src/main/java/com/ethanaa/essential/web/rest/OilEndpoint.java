package com.ethanaa.essential.web.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.ethanaa.essential.assembler.OilApplicationAssembler;
import com.ethanaa.essential.assembler.OilAssembler;
import com.ethanaa.essential.assembler.OilInfoItemAssembler;
import com.ethanaa.essential.assembler.OilReviewAssembler;
import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.service.OilApplicationService;
import com.ethanaa.essential.service.OilInfoItemService;
import com.ethanaa.essential.service.OilReviewService;
import com.ethanaa.essential.service.OilService;
import com.ethanaa.essential.service.exception.InvalidResourceException;
import com.ethanaa.essential.web.rest.resource.OilApplicationResource;
import com.ethanaa.essential.web.rest.resource.OilInfoItemResource;
import com.ethanaa.essential.web.rest.resource.OilResource;
import com.ethanaa.essential.web.rest.resource.OilReviewResource;

@RestController
@RequestMapping("/api/oils")
@ExposesResourceFor(Oil.class)
public class OilEndpoint {

	private static final String INVALID_OIL = "Invalid oil resource";
	
	private final Logger log = LoggerFactory.getLogger(OilEndpoint.class);
	
	private OilService oilService;
	private OilAssembler oilAssembler;
	
	private OilInfoItemService oilInfoItemService;
	private OilInfoItemAssembler oilInfoItemAssembler;
	
	private OilApplicationService oilApplicationService;
	private OilApplicationAssembler oilApplicationAssembler;
	
	private OilReviewService oilReviewService;
	private OilReviewAssembler oilReviewAssembler;
	
	@Autowired
	public OilEndpoint(OilService oilService, OilAssembler oilAssembler, 
			OilInfoItemService oilInfoItemService, OilInfoItemAssembler oilInfoItemAssembler,
			OilApplicationService oilApplicationService, OilApplicationAssembler oilApplicationAssembler,
			OilReviewService oilReviewService, OilReviewAssembler oilReviewAssembler) {

		this.oilService = oilService;
		this.oilAssembler = oilAssembler;
		
		this.oilInfoItemService = oilInfoItemService;
		this.oilInfoItemAssembler = oilInfoItemAssembler;
		
		this.oilApplicationService = oilApplicationService;
		this.oilApplicationAssembler = oilApplicationAssembler;
		
		this.oilReviewService = oilReviewService;
		this.oilReviewAssembler = oilReviewAssembler;
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedResources<OilResource>> getOils(Pageable pageable, PagedResourcesAssembler<Oil> pagedAssembler) {
		
		Page<Oil> page = oilService.getOils(pageable);
		
		PagedResources<OilResource> pagedResources = pagedAssembler.toResource(page, oilAssembler);
		pagedResources.add(linkTo(methodOn(this.getClass()).getAllOils()).withRel("all"));
		pagedResources.add(linkTo(this.getClass()).slash("search").withRel("search"));
		
		return new ResponseEntity<>(pagedResources, HttpStatus.OK);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OilResource>> getAllOils() {
		
		List<Oil> oils = oilService.getOils();
		
		return new ResponseEntity<>(oilAssembler.toResources(oils), HttpStatus.OK);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OilResource> getOil(@PathVariable Long id) {
		
		Oil oil = oilService.getOil(id);
		
		return new ResponseEntity<>(oilAssembler.toResource(oil), HttpStatus.OK);
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<OilResource> createOil(@Valid @RequestBody OilResource request, BindingResult br) {
		
		if (br.hasErrors()) {
			throw new InvalidResourceException(INVALID_OIL, br);
		}
		
		Oil oil = oilService.createOil(request);
		
		return new ResponseEntity<>(oilAssembler.toResource(oil), HttpStatus.CREATED);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OilResource> updateOil(@PathVariable Long id, @Valid @RequestBody OilResource request, BindingResult br) {
		
		if (br.hasErrors()) {
			throw new InvalidResourceException(INVALID_OIL, br);
		}
		
		Oil oil = oilService.updateOil(id, request);
		
		return new ResponseEntity<>(oilAssembler.toResource(oil), HttpStatus.OK);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteOil(@PathVariable Long id) {
		
		oilService.deleteOil(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<OilInfoItemResource>> getOilInfoItems(@PathVariable Long id) {
	
		List<OilInfoItem> oilInfoItems = oilInfoItemService.getOilInfoItemsByOilId(id);
		
		List<OilInfoItemResource> resources = oilInfoItemAssembler.toResources(oilInfoItems);
		
		Resources<OilInfoItemResource> oilInfoResources = new Resources<>(resources, 
				linkTo(methodOn(this.getClass()).getOil(id)).withRel("oil"));
		
		return new ResponseEntity<>(oilInfoResources, HttpStatus.OK);	
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/applications", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<OilApplicationResource>> getOilApplications(@PathVariable Long id) {
		
		List<OilApplication> oilApplications = oilApplicationService.getOilApplicationsByOilId(id);
		
		List<OilApplicationResource> resources = oilApplicationAssembler.toResources(oilApplications);
		
		Resources<OilApplicationResource> oilApplicationResources = new Resources<>(resources, 
				linkTo(methodOn(this.getClass()).getOil(id)).withRel("oil"));
		
		return new ResponseEntity<>(oilApplicationResources, HttpStatus.OK);
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Resources<OilReviewResource>> getOilReviews(@PathVariable Long id) {
		
		List<OilReview> oilReviews = oilReviewService.getOilReviewsByOilId(id);
		
		List<OilReviewResource> resources = oilReviewAssembler.toResources(oilReviews);
		
		Resources<OilReviewResource> oilReviewResources = new Resources<>(resources,
				linkTo(methodOn(this.getClass()).getOil(id)).withRel("oil"));
		
		return new ResponseEntity<>(oilReviewResources, HttpStatus.OK);
	}
	
}
