package com.infyz.smartTraxx.master.Resouces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infyz.smartTraxx.master.entities.IcStatus;
import com.infyz.smarttraxx.pojos.statusPojo;

@ApplicationScoped
public class IcStatusResource {
	@Inject
	EntityManager em;

	public IcStatus getStatusById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcStatus> criteria = cb.createQuery(IcStatus.class);
		Root<IcStatus> x = criteria.from(IcStatus.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("statusId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public IcStatus getStatusByCode(String code) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcStatus> criteria = cb.createQuery(IcStatus.class);
		Root<IcStatus> x = criteria.from(IcStatus.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("statusCode"), code));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<IcStatus> getIcStatus_Records() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcStatus> criteria = cb.createQuery(IcStatus.class);
		Root<IcStatus> root = criteria.from(IcStatus.class);
		criteria.select(root).orderBy(cb.asc(root.get("statusId")));
		return em.createQuery(criteria).getResultList();
	}
	public List<IcStatus> searchRecords() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IcStatus> cq = cb.createQuery(IcStatus.class);
        Root<IcStatus> root = cq.from(IcStatus.class);
        cq.select(root);
        TypedQuery<IcStatus> Query = em.createQuery(cq);
        List<IcStatus> status = Query.getResultList();
        System.out.println("success");
        return status;
    }
	public List<IcStatus> getsearchById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<IcStatus> criteria = cb.createQuery(IcStatus.class);
		Root<IcStatus> x = criteria.from(IcStatus.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("statusId"), id));
		return em.createQuery(criteria).getResultList();
	}
	public statusPojo getStatusById1(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<statusPojo> criteria = cb.createQuery(statusPojo.class);
		Root<statusPojo> x = criteria.from(statusPojo.class);
		criteria.select(x);
		criteria.where(cb.equal(x.get("statusId"), id));
		return em.createQuery(criteria).getSingleResult();
	}
	public List<statusPojo> getIcStatus_Records1() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<statusPojo> criteria = cb.createQuery(statusPojo.class);
		Root<statusPojo> root = criteria.from(statusPojo.class);
		criteria.select(root).orderBy(cb.asc(root.get("statusId")));
		return em.createQuery(criteria).getResultList();
	}
}
