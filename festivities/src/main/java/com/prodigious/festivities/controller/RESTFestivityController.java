package com.prodigious.festivities.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prodigious.festivities.dto.FestivityDto;
import com.prodigious.festivities.service.FestivityService;

/**
 * Service exposed with the functionalities for handle festivities information.
 * @author Juan Joya
 *
 */
@RestController
@RequestMapping(value = "festivities-api/")
public class RESTFestivityController {

	@Autowired
	FestivityService festivityService;
	
	@RequestMapping(value = "/findAll", method=RequestMethod.GET)
	public ResponseEntity<List<FestivityDto>> findFestivities(){
		return new ResponseEntity<List<FestivityDto>>(
				festivityService.finAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findByInfo", method=RequestMethod.POST)
	public ResponseEntity<List<FestivityDto>> finByInfo(@RequestBody FestivityDto festivity){
		return new ResponseEntity<List<FestivityDto>>(
				festivityService.findByInfo(festivity), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody FestivityDto festivity){
		this.festivityService.create(festivity);
		return new ResponseEntity<List<?>>(HttpStatus.OK);
	}
	
	
}
