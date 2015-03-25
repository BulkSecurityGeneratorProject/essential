package com.ethanaa.essential.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rels")
public class RelEndpoint {

	@SuppressWarnings("serial")
	private static final Map<String, String> RELS = new HashMap<String, String>() {
		{
			put("all", "request the entities as a complete list rather than paginating the results");
			put("info", "get the info items associated with this entity");
			put("applications", "get the applications associated with the entity");
			put("reviews", "get the tags associated with this entity");
			put("image", "get the image associated with this entity");			
			put("images", "get the images associated with this entity");
			put("oil", "get the oil associated with this entity");
			put("reviewer", "get the user that authored this review");
		}
	};
	
    @RequestMapping(method = RequestMethod.GET, value = "/{rel}", produces = MediaType.TEXT_HTML_VALUE)	
	public ResponseEntity<String> getRel(@PathVariable String rel) {		
		
    	String relDescription = RELS.get(rel);
    	
    	if (relDescription == null || relDescription.isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
		return new ResponseEntity<>(relDescription, HttpStatus.OK);
	}	
	
}
