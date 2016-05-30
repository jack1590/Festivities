package com.prodigious.festivities.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class provides a service for managing the festivity information.
 * @author Juan Joya
 *
 */
@Service
public class FestivityServiceImpl implements FestivityService{
	
	@PersistenceContext
	EntityManager em;

	/**
	 * This method delete all information related with Festivity
	 */
	@Transactional
	@Override
	public void deleteAllFestivities() {
		em.createNamedQuery("Festivity.deleteAll").executeUpdate();
	}

}
