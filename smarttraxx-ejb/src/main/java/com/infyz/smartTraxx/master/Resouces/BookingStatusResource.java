package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infyz.smartTraxx.master.entities.IcBookingStatus;
import com.infyz.smartTraxx.master.entities.IcStatus;


@ApplicationScoped
public class BookingStatusResource {
	@Inject
	EntityManager em;
	public IcBookingStatus getbookingStatusById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcBookingStatus> criteria = cb.createQuery(IcBookingStatus.class);
		Root<IcBookingStatus> x = criteria.from(IcBookingStatus.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("bookingStatusId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<IcBookingStatus> findAllOrderedByCode() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcBookingStatus> criteria = cb.createQuery(IcBookingStatus.class);
		Root<IcBookingStatus> root = criteria.from(IcBookingStatus.class);
		criteria.select(root).orderBy(cb.asc(root.get("bookingStatusId")));
		return em.createQuery(criteria).getResultList();
	}
	public List<IcBookingStatus> getsearchById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcBookingStatus> criteria = cb.createQuery(IcBookingStatus.class);
		Root<IcBookingStatus> x = criteria.from(IcBookingStatus.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("bookingStatusId"), id));
		return em.createQuery(criteria).getResultList();
	}
}
