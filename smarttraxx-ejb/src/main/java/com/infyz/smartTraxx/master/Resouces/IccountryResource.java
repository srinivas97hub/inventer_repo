package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infyz.smartTraxx.master.entities.IcCountry;

@ApplicationScoped
public class IccountryResource {
	@Inject
	EntityManager em;
	public IcCountry getCountryById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcCountry> criteria = cb.createQuery(IcCountry.class);
		Root<IcCountry> x = criteria.from(IcCountry.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("countryId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<IcCountry> findAllOrderedByCode() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcCountry> criteria = cb.createQuery(IcCountry.class);
		Root<IcCountry> root = criteria.from(IcCountry.class);
		criteria.select(root).orderBy(cb.asc(root.get("countryId")));
		return em.createQuery(criteria).getResultList();
	}
}
