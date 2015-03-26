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
import com.ethanaa.essential.assembler.OilBlendApplicationAssembler;
import com.ethanaa.essential.assembler.OilBlendAssembler;
import com.ethanaa.essential.assembler.OilBlendIngredientAssembler;
import com.ethanaa.essential.assembler.OilBlendReviewAssembler;
import com.ethanaa.essential.domain.OilBlend;
import com.ethanaa.essential.domain.OilBlendApplication;
import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.domain.OilBlendReview;
import com.ethanaa.essential.service.OilBlendApplicationService;
import com.ethanaa.essential.service.OilBlendIngredientService;
import com.ethanaa.essential.service.OilBlendReviewService;
import com.ethanaa.essential.service.OilBlendService;
import com.ethanaa.essential.service.exception.InvalidResourceException;
import com.ethanaa.essential.web.rest.resource.OilBlendApplicationResource;
import com.ethanaa.essential.web.rest.resource.OilBlendIngredientResource;
import com.ethanaa.essential.web.rest.resource.OilBlendResource;
import com.ethanaa.essential.web.rest.resource.OilBlendReviewResource;

@RestController
@RequestMapping("/api/oilBlends")
@ExposesResourceFor(OilBlend.class)
public class OilBlendEndpoint {

	private static final String INVALID_OIL_BLEND = "Invalid oil blend resource";
	
	private final Logger log = LoggerFactory.getLogger(OilBlendEndpoint.class);
	
	private OilBlendService oilBlendService;
	private OilBlendAssembler oilBlendAssembler;
	
	private OilBlendIngredientService oilBlendIngredientService;
	private OilBlendIngredientAssembler oilBlendIngredientAssembler;
	
	private OilBlendApplicationService oilBlendApplicationService;
	private OilBlendApplicationAssembler oilBlendApplicationAssembler;
	
	private OilBlendReviewService oilBlendReviewService;
	private OilBlendReviewAssembler oilBlendReviewAssembler;
	
	@Autowired
	public OilBlendEndpoint(OilBlendService oilBlendService, OilBlendAssembler oilBlendAssembler,
			OilBlendIngredientService oilBlendIngredientService, OilBlendIngredientAssembler oilBlendIngredientAssembler,
			OilBlendApplicationService oilBlendApplicationService, OilBlendApplicationAssembler oilBlendApplicationAssembler,
			OilBlendReviewService oilBlendReviewService, OilBlendReviewAssembler oilBlendReviewAssembler) {
		
		this.oilBlendService = oilBlendService;
		this.oilBlendAssembler = oilBlendAssembler;
		
		this.oilBlendIngredientService = oilBlendIngredientService;
		this.oilBlendIngredientAssembler = oilBlendIngredientAssembler;
		
		this.oilBlendApplicationService = oilBlendApplicationService;
		this.oilBlendApplicationAssembler = oilBlendApplicationAssembler;
		
		this.oilBlendReviewService = oilBlendReviewService;
		this.oilBlendReviewAssembler = oilBlendReviewAssembler;
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<PagedResources<OilBlendResource>> getOilBlends(Pageable pageable, PagedResourcesAssembler<OilBlend> pagedAssembler) {
		
		Page<OilBlend> page = oilBlendService.getOilBlends(pageable);
		
		PagedResources<OilBlendResource> pagedResources = pagedAssembler.toResource(page, oilBlendAssembler);
		pagedResources.add(linkTo(methodOn(this.getClass()).getAllOilBlends()).withRel("all"));
		pagedResources.add(linkTo(this.getClass()).slash("search").withRel("search"));
		
		return new ResponseEntity<>(pagedResources, HttpStatus.OK);		
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OilBlendResource>> getAllOilBlends() {
		
		List<OilBlend> oilBlends = oilBlendService.getOilBlends();
		
		return new ResponseEntity<>(oilBlendAssembler.toResources(oilBlends), HttpStatus.OK);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OilBlendResource> getOilBlend(@PathVariable Long id) {
		
		OilBlend oilBlend = oilBlendService.getOilBlend(id);
		
		return new ResponseEntity<>(oilBlendAssembler.toResource(oilBlend), HttpStatus.OK);
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<OilBlendResource> createOilBlend(@Valid @RequestBody OilBlendResource request, BindingResult br) {
		
		if (br.hasErrors()) {
			throw new InvalidResourceException(INVALID_OIL_BLEND, br);
		}
		
		OilBlend oilBlend = oilBlendService.createOilBlend(request);
		
		return new ResponseEntity<>(oilBlendAssembler.toResource(oilBlend), HttpStatus.CREATED);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OilBlendResource> updateOilBlend(@PathVariable Long id, @Valid @RequestBody OilBlendResource request, BindingResult br) {
		
		if (br.hasErrors()) {
			throw new InvalidResourceException(INVALID_OIL_BLEND, br);
		}
		
		OilBlend oilBlend = oilBlendService.updateOilBlend(id, request);
		
		return new ResponseEntity<>(oilBlendAssembler.toResource(oilBlend), HttpStatus.OK);
	}
	
	@Timed
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteOilBlend(@PathVariable Long id) {
		
		oilBlendService.deleteOilBlend(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<OilBlendIngredientResource>> getOilBlendIngredients(@PathVariable Long id) {
		
		List<OilBlendIngredient> oilBlendIngredients = oilBlendIngredientService.getOilBlendIngredientsByOilBlendId(id);
		
		List<OilBlendIngredientResource> resources = oilBlendIngredientAssembler.toResources(oilBlendIngredients);
		
		Resources<OilBlendIngredientResource> oilBlendIngredientResources = new Resources<>(resources, 
				linkTo(methodOn(this.getClass()).getOilBlend(id)).withRel("oilBlend"));
		
		return new ResponseEntity<>(oilBlendIngredientResources, HttpStatus.OK);
	}	
	
	@Timed
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/applications", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<OilBlendApplicationResource>> getOilBlendApplications(@PathVariable Long id) {
		
		List<OilBlendApplication> oilBlendApplications = oilBlendApplicationService.getOilBlendApplicationsByOilBlendId(id);
		
		List<OilBlendApplicationResource> resources = oilBlendApplicationAssembler.toResources(oilBlendApplications);
		
		Resources<OilBlendApplicationResource> oilBlendApplicationResources = new Resources<>(resources, 
				linkTo(methodOn(this.getClass()).getOilBlend(id)).withRel("oilBlend"));
		
		return new ResponseEntity<>(oilBlendApplicationResources, HttpStatus.OK);
	}
	
	@Timed
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<PagedResources<OilBlendReviewResource>> getOilBlendReviews(@PathVariable Long id, Pageable pageable, PagedResourcesAssembler<OilBlendReview> pagedAssembler) {
		
		Page<OilBlendReview> page = oilBlendReviewService.getOilBlendReviewsByOilBlendId(id, pageable);
		
		PagedResources<OilBlendReviewResource> pagedResources = pagedAssembler.toResource(page, oilBlendReviewAssembler);
		pagedResources.add(linkTo(methodOn(this.getClass()).getOilBlend(id)).withRel("oilBlend"));
		
		return new ResponseEntity<>(pagedResources, HttpStatus.OK);		
	}	
	
}
