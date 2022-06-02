package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.infyz.smartTraxx.master.Resouces.IcRoutedeatisResource;
import com.infyz.smartTraxx.master.Resouces.IcStatusResource;
import com.infyz.smartTraxx.master.Resouces.ItBookikgRouteResource;
import com.infyz.smartTraxx.master.Resouces.ItBookingResource;
import com.infyz.smartTraxx.master.entities.IcRouteDetail;
import com.infyz.smartTraxx.master.entities.IcStatus;
import com.infyz.smartTraxx.master.entities.ItBookikgRouteDetail;
import com.infyz.smartTraxx.master.entities.ItBooking;

@Named("itBookikgRouteDetailSession")
@SessionScoped
public class ItBookingRouteDetailSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	@Named
	@Produces
	private ItBookikgRouteDetail ItBookikgRouteDetailActivity;
	@Inject
	ItBookikgRouteResource resource;
	@Inject
	IcStatusResource icstausResource;
    @Inject
    IcRoutedeatisResource icrouteresource;
    @Inject
    ItBookingResource itbookingresource;
    
	private List<ItBookikgRouteDetail> BookingRoute;
	private List<IcRouteDetail>  routedeatil;
	private List<IcStatus> status;
	private List<ItBooking> booking;
	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;

	public void getBookingrouteList() { 
		BookingRoute =resource.getRecords();
	}

	public List<ItBookikgRouteDetail> getBookingRoute() {
		BookingRoute =resource.getRecords();
		return BookingRoute;
	}
	public void add() {
		System.out.println("add method called");
		ItBookikgRouteDetailActivity = new ItBookikgRouteDetail();
		System.out.println(ItBookikgRouteDetailActivity.getIcStatus());
		System.out.println(ItBookikgRouteDetailActivity.getIcRouteDetail());
		System.out.println(ItBookikgRouteDetailActivity.getItBooking());
	}
	
	public void Edit(int id) {
		System.out.println("hi edit"+id);
		ItBookikgRouteDetailActivity=resource.getById(id);
	}
	public void Select(int id)
	{
		System.out.println("hi edit"+id);
		ItBookikgRouteDetailActivity=resource.getById(id);

	}
	@Transactional
	public String persist() {
		System.out.println("persist method executed");
		ItBookikgRouteDetailActivity. setCreatedBy(01);
		ItBookikgRouteDetailActivity. setModifiedBy(01);
		ItBookikgRouteDetailActivity. setCreatedDate(new Date());
		ItBookikgRouteDetailActivity. setModifiedDate(new Date());
		ItBookikgRouteDetailActivity. setActualStartTime(new Date());
		ItBookikgRouteDetailActivity. setActualCompleteTime(new Date());
		em.persist(ItBookikgRouteDetailActivity);
		getBookingRoute();
		return "/Test/bookingroutedeatils.xhtml?faces-redirect=true";
	}

	@Transactional
	public String update() {
		System.out.println("hi update");
		ItBookikgRouteDetailActivity. setCreatedBy(01);
		ItBookikgRouteDetailActivity. setModifiedBy(01);
		ItBookikgRouteDetailActivity. setCreatedDate(new Date());
		ItBookikgRouteDetailActivity. setModifiedDate(new Date());
		em.merge(ItBookikgRouteDetailActivity);
		getBookingRoute();
		return "/Test/bookingroutedeatils.xhtml?faces-redirect=true";
	}
	@Transactional
	public String delete()
	{
		System.out.println("hi delete");
		ItBookikgRouteDetailActivity= em.find(ItBookikgRouteDetail.class,ItBookikgRouteDetailActivity.getBookingRouteDetailId());
		em.remove(ItBookikgRouteDetailActivity);
		return "/Test/bookingroutedeatils.xhtml?faces-redirect=true";
	}
	public String cancel() {
		return"index.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "/Test/bookingroutedeatils.xhtml?faces-redirect=true";
	}
	public String navigate(){
		return "/Test/bookingroutedeatils.xhtml?faces-redirect=true";
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

	public List<IcRouteDetail> getRoutedeatil() {
		return routedeatil;
	}

	public void setRoutedeatil(List<IcRouteDetail> routedeatil) {
		this.routedeatil = routedeatil;
	}

	public List<IcStatus> getStatus() {
		return status;
	}

	public void setStatus(List<IcStatus> status) {
		this.status = status;
	}

	public List<ItBooking> getBooking() {
		return booking;
	}

	public void setBooking(List<ItBooking> booking) {
		this.booking = booking;
	}
	
	@PostConstruct
	public void getCountryList() {
		BookingRoute =resource.getRecords(); 
		status=new ArrayList<IcStatus>(0);
		status=icstausResource.getIcStatus_Records();
		routedeatil=new ArrayList<IcRouteDetail>(0);
		routedeatil=icrouteresource.findAllRouteDeatils();
		booking=new ArrayList<ItBooking>(0);
		booking=itbookingresource.getBooking_Records();
	}
	
}
