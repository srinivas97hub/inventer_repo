package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.infyz.smartTraxx.master.Resouces.IcRoutedeatisResource;
import com.infyz.smartTraxx.master.entities.IcRouteDetail;

@Named
@SessionScoped
public class IcRouteDetailSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	@Named
	@Produces
	private IcRouteDetail IcRouteDetailActivity;
	@Inject
	IcRoutedeatisResource resource;

	private List<IcRouteDetail> Routedeatils;

	public List<IcRouteDetail> getRoutedeatils() {
		return Routedeatils;
	}

	public void setRoutedeatils(List<IcRouteDetail> routedeatils) {
		Routedeatils = routedeatils;
	}

	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;

	public List<IcRouteDetail> getRoutes() {
		Routedeatils = resource.findAllRouteDeatils();
		return Routedeatils;
	}

	public void add() {
		System.out.println("add method called");
		IcRouteDetailActivity = new IcRouteDetail();
	}

	public void Edit(int id) {
		System.out.println("hi edit" + id);
		IcRouteDetailActivity = resource.getrouteId(id);
	}

	public void Select(int id) {
		System.out.println("hi edit" + id);
		IcRouteDetailActivity = resource.getrouteId(id);

	}

	@Transactional
	public String persist() {
		System.out.println("persist method executed");
		IcRouteDetailActivity.setCreatedBy(01);
		IcRouteDetailActivity.setModifiedBy(01);
		IcRouteDetailActivity.setCreatedDate(new Date());
		IcRouteDetailActivity.setModifiedDate(new Date());
		em.persist(IcRouteDetailActivity);
		getRoutes();
		return "Test/routedeatils.xhtml?faces-redirect=true";
	}

	@Transactional
	public String update() {
		System.out.println("hi update");
		IcRouteDetailActivity.setCreatedBy(01);
		IcRouteDetailActivity.setModifiedBy(01);
		IcRouteDetailActivity.setCreatedDate(new Date());
		IcRouteDetailActivity.setModifiedDate(new Date());
		em.merge(IcRouteDetailActivity);
		getRoutes();
		return "Test/routedeatils.xhtml?faces-redirect=true";
	}

	@Transactional
	public String delete() {
		System.out.println("hi delete");
		IcRouteDetailActivity = em.find(IcRouteDetail.class, IcRouteDetailActivity.getRouteDetailId());
		em.remove(IcRouteDetailActivity);
		return "Test/routedeatils.xhtml?faces-redirect=true";
	}

	public String cancel() {
		return "index.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "Test/routedeatils.xhtml?faces-redirect=true";
	}

	public String navigate() {
		return "Test/routedeatils.xhtml?faces-redirect=true";
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
