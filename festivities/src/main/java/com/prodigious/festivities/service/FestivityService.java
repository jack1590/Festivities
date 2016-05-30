package com.prodigious.festivities.service;

import java.util.List;

import com.prodigious.festivities.dto.FestivityDto;

/**
 * Interface created to be provided to the controller as entry point to the service.
 * @author Juan Joya
 *
 */
public interface FestivityService {
	
	void deleteAllFestivities();
	List<FestivityDto> finAll();
}
