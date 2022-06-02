package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infyz.smartTraxx.master.entities.IcRegion;
import com.infyz.smartTraxx.master.entities.IcStatus;

@ApplicationScoped
public class IcRegionResource {
	@Inject
	EntityManager em;

	public IcRegion getRegionById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcRegion> criteria = cb.createQuery(IcRegion.class);
		Root<IcRegion> x = criteria.from(IcRegion.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("regionId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<IcRegion> getIcRegion_Records() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcRegion> criteria = cb.createQuery(IcRegion.class);
		Root<IcRegion> root = criteria.from(IcRegion.class);
		criteria.select(root).orderBy(cb.asc(root.get("regionId")));
		return em.createQuery(criteria).getResultList();
	}
	public List<IcRegion> getRegionById1(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcRegion> criteria = cb.createQuery(IcRegion.class);
		Root<IcRegion> x = criteria.from(IcRegion.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("regionId"), id));
		return em.createQuery(criteria).getResultList();
	}
}
