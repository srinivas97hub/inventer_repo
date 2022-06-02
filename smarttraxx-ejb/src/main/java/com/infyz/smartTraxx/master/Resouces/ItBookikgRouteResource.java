package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infyz.smartTraxx.master.entities.IcStatus;
import com.infyz.smartTraxx.master.entities.ItBookikgRouteDetail;

@ApplicationScoped
public class ItBookikgRouteResource {
	@Inject
	EntityManager em;

	public ItBookikgRouteDetail getById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItBookikgRouteDetail> criteria = cb.createQuery(ItBookikgRouteDetail.class);
		Root<ItBookikgRouteDetail> x = criteria.from(ItBookikgRouteDetail.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("bookingRouteDetailId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<ItBookikgRouteDetail> getRecords() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItBookikgRouteDetail> criteria = cb.createQuery(ItBookikgRouteDetail.class);
		Root<ItBookikgRouteDetail> root = criteria.from(ItBookikgRouteDetail.class);
		criteria.select(root).orderBy(cb.asc(root.get("bookingRouteDetailId")));
		return em.createQuery(criteria).getResultList();
	}
}
