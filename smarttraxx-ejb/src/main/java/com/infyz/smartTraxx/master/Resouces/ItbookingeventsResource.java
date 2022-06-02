package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infyz.smartTraxx.master.entities.ItBookingEvent;

@ApplicationScoped
public class ItbookingeventsResource {
	@Inject
	EntityManager em;

	public ItBookingEvent getbookingId(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItBookingEvent> criteria = cb.createQuery(ItBookingEvent.class);
		Root<ItBookingEvent> x = criteria.from(ItBookingEvent.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("bookingEventsId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<ItBookingEvent> getBooking_Records() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItBookingEvent> criteria = cb.createQuery(ItBookingEvent.class);
		Root<ItBookingEvent> root = criteria.from(ItBookingEvent.class);
		criteria.select(root).orderBy(cb.asc(root.get("bookingEventsId")));
		return em.createQuery(criteria).getResultList();
	}
}
