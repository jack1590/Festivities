package com.prodigious.festivities.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodigious.festivities.dto.FestivityDto;
import com.prodigious.festivities.model.Festivity;

/**
 * This class provides a service for managing the festivity information.
 * 
 * @author Juan Joya
 *
 */
@Service
public class FestivityServiceImpl implements FestivityService {

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

	/**
	 * Find all festivities
	 * 
	 * @return List of {@link FestivityDto}
	 */
	@Override
	public List<FestivityDto> finAll() {
		return em.createNamedQuery("Festivity.findAll", FestivityDto.class)
				.getResultList();
	}

	/**
	 * Find festivity by name, place, start, end
	 * 
	 * @return List of {@link FestivityDto}
	 */
	@Override
	public List<FestivityDto> findByInfo(FestivityDto festivity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FestivityDto> query = cb.createQuery(FestivityDto.class);
		Root<Festivity> c = query.from(Festivity.class);
		query.select(cb.construct(FestivityDto.class, c.get("name"),
				c.get("place"), c.get("start"), c.get("end")));
		
		Predicate[] predicates = this.createPredicates(festivity,cb,c);
		
		query.where(predicates);
		
		return em.createQuery(query).getResultList();
	}

	/**
	 * Creates predicates to filter festivities
	 * @param festivity
	 * @param cb CriteriaBuilder
	 * @param c Root
	 * @return Predicates
	 */
	private Predicate[] createPredicates(FestivityDto festivity,
			CriteriaBuilder cb, Root<Festivity> c) {
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (Objects.nonNull(festivity.getName()) && !festivity.getName().isEmpty()) {
			Predicate aux = cb.like(
		        cb.upper(c.<String>get("name")), "%"+festivity.getName()+"%");
		    predicateList.add(aux);
		}
		
		if (Objects.nonNull(festivity.getPlace()) && !festivity.getPlace().isEmpty()) {
			Predicate aux = cb.like(
		        cb.upper(c.<String>get("place")), "%"+festivity.getPlace()+"%");
		    predicateList.add(aux);
		}
		
		if (Objects.nonNull(festivity.getStart()) && Objects.nonNull(festivity.getEnd())) {
			Predicate aux = cb.equal(cb.upper(c.<String> get("start")),
					festivity.getStart());
			predicateList.add(aux);
			
			aux = cb.equal(cb.upper(c.<String> get("end")), festivity.getEnd());
			predicateList.add(aux);
		}
		
		return predicateList.toArray(new Predicate[predicateList.size()]);
	}

}
