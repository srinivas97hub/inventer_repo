package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infyz.smartTraxx.master.entities.IcRoute;
import com.infyz.smartTraxx.master.entities.IcStatus;

@ApplicationScoped
public class RouteResorce {

		@Inject
		EntityManager em;

		public IcRoute getrouteId(Integer id) {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<IcRoute> criteria = cb.createQuery(IcRoute.class);
			Root<IcRoute> x = criteria.from(IcRoute.class);
			criteria.select(x);
			criteria.where(cb.equal(x.get("routeId"), id));
			return em.createQuery(criteria).getSingleResult();
		}
		public List<IcRoute> getroute_Records() {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<IcRoute> criteria = cb.createQuery(IcRoute.class);
			Root<IcRoute> root = criteria.from(IcRoute.class);
			criteria.select(root).orderBy(cb.asc(root.get("routeId")));
			return em.createQuery(criteria).getResultList();
		}
		public List<IcRoute> searchRecords() {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<IcRoute> cq = cb.createQuery(IcRoute.class);
	        Root<IcRoute> root = cq.from(IcRoute.class);
	        cq.select(root);
	        TypedQuery<IcRoute> Query = em.createQuery(cq);
	        List<IcRoute> status = Query.getResultList();
	        System.out.println("success");
	        return status;
	    }
		public List<IcRoute> getsearchById(Integer id) {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<IcRoute> criteria = cb.createQuery(IcRoute.class);
			Root<IcRoute> x = criteria.from(IcRoute.class);
			criteria.select(x);
			criteria.where(cb.equal(x.get("routeId"), id));
			return em.createQuery(criteria).getResultList();
		}
	}

