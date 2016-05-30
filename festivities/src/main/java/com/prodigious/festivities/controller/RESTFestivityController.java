package com.prodigious.festivities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<FestivityDto> getProfile(){
		return festivityService.finAll();
	}
}
